<template>
    <div id="comment">
        <div class="comment_title">
            <h1>评论</h1>
        </div>
        <div class="comment_my">
            <div v-if="!this.getJudgeLogin">
                <van-notice-bar :scrollable="false" text="您未登录，无法管理个人评论！" />
                <router-link to="/login">
                    <van-button type="primary" class="login">去登录</van-button>
                </router-link>
            </div>
            <div v-else>
                <div v-if="my_judge_comment">
                    <div v-if="!my_report">
                        <van-field v-model="my_message" rows="1" center clearable autosize label="我的留言" type="textarea"
                            placeholder="请输入留言">
                            <template #button>
                                <van-row gutter="10">
                                    <van-col span="12">
                                        <van-button size="small" type="primary" @click="updateComment">修改</van-button>
                                    </van-col>
                                    <van-col span="12">
                                        <van-button size="small" type="primary" @click="deleteComment">删除</van-button>
                                    </van-col>
                                </van-row>
                            </template>
                        </van-field>
                    </div>
                    <div v-else>
                        <van-notice-bar v-if="my_report==1" :scrollable="false" text="您的评论已被举报，正在审核中，您可选择删除评论！" />
                        <van-notice-bar v-else-if="my_report==2" scrollable
                            text="您的评论已被举报，审核未通过，已设置该评论对其他用户不可见！您可选择删除评论！" />
                        <van-field v-model="my_message" rows="1" center clearable autosize label="我的留言" type="textarea"
                            placeholder="请输入留言" readonly>
                            <template #button>
                                <van-button size="small" type="primary" @click="deleteComment">删除</van-button>
                            </template>
                        </van-field>
                    </div>
                </div>
                <div v-else>
                    <van-field v-model="my_message" rows="1" center clearable autosize label="我的留言" type="textarea"
                        placeholder="未留言，可输入留言">
                        <template #button>
                            <van-button size="small" type="primary" @click="addComment">提交</van-button>
                        </template>
                    </van-field>
                </div>
            </div>
        </div>
        <div class="comment_other" v-if="commentList.length">
            <div v-for="(item,index) in commentList" :key="index">
                <van-row>
                    <van-col span="15" offset="2" class="comment_other_content">
                        <div class="comment_other_title">
                            <div v-if="item.user_name">用户名：{{item.user_name}}</div>
                            <div v-else>该用户未取用户名</div>
                        </div>

                        <div class="comment_other_info">留言：{{item.comment_content}}</div>
                    </van-col>
                    <van-col span="3" offset="2" class="comment_other_operation">
                        <van-button size="small" type="primary" @click="report(item.comment_id,item.user_id,)">举报</van-button>
                    </van-col>
                </van-row>
            </div>
        </div>
        <div v-else>
             <van-empty image="error" description="未发现有人评论" />
        </div>


    </div>
</template>

<script>
    import {
        Dialog
    } from 'vant';
    export default {
        name: 'cmmment',
        data() {
            return {
                // 用户个人
                // 用户是否已评论
                my_judge_comment: "",
                // 用户的评论是否被举报  0为通过，1为待审核，2为不通过
                my_report: "",
                my_message: "",
                operation_id: "",
                // 
                commentList: [],
            }
        },
        methods: {
            getloadComment() {
                this.axios.get("http://192.168.1.103:8083/operation/getCommentList")
                    .then((res) => {
                        console.log(res.data)
                        this.commentList = res.data
                    })
            },
            // 判断该登录用户是否登录，是否已评论
            judgeComment() {
                if (this.getJudgeLogin) {
                    console.log(this.getUserId,this.getKnowledgeId)
                    let  operation_user_id=this.getUserId
                    console.log(typeof operation_user_id,operation_user_id)
                    this.axios.get("http://192.168.1.103:8083/operation/judgeOperationId?operation_user_id="+this.getUserId
                                        +"&operation_source_type="++"&operation_source_id="+2)
                        .then((res) => {
                            console.log("查询用户是否有该操作和评论列表")
                            console.log(res.data, res.data.msg, res.data.data.comment_content,
                                res.data.data.comment_state, res.data.data.operation_id)
                            if (res.data.msg == '用户已有过该操作') {
                                this.my_judge_comment = true
                                this.my_message = res.data.data.comment_content
                                this.my_report = res.data.data.comment_state
                                this.operation_id = res.data.data.operation_id;
                            } else {
                                this.my_judge_comment = false
                            }
                        })
                }
                // console.log(this.getUserId,this.getKnowledgeId)
            },
            // 添加我的评论
            addComment() {
                this.axios.post(
                        "http://192.168.1.103:8083/operation/addComment?operation_user_id="+ this.getUserId +
                        "&operation_source_type="+2 + "&operation_source_id=" + this.getKnowledgeId +
                        "&operation_type="+2 + "&comment_content=" + this.my_message
                    )
                    .then((res) => {
                        console.log("添加评论")
                        console.log(res, res.data.msg)
                        if (res.data.msg == '添加评论成功') {
                            Dialog.alert({
                                message: '添加评论成功!',
                            }).then(() => {
                                this.judgeComment()
                            });
                        } else {
                            Dialog.alert({
                                message: '添加评论失败!',
                            }).then(() => {
                                // on close
                            });
                        }
                    })
            },
            // 修改我的评论内容
            updateComment() {
                this.axios.get("http://192.168.1.103:8083/operation/updateComment?operation_id=" + this.operation_id +
                        "&comment_content=" + this.my_message)
                    .then((res) => {
                        console.log(res.data, res.data.msg)
                        if (res.data.msg == '修改评论内容成功') {
                            Dialog.alert({
                                message: '修改评论内容成功！',
                            }).then(() => {

                            });
                        } else {
                            Dialog.alert({
                                message: '修改评论内容失败！',
                            }).then(() => {

                            });
                        }
                    })
            },
            deleteComment() {
                this.axios.get("http://192.168.1.103:8083/operation/deleteComment?operation_id=" + this.operation_id)
                    .then((res) => {
                        console.log(res, res.data.msg)
                        if (res.data.msg == '删除评论成功') {
                            Dialog.alert({
                                message: '删除评论成功',
                            }).then(() => {
                                this.my_message = null
                                this.judgeComment()
                            });
                        } else {
                            Dialog.alert({
                                message: '删除评论失败',
                            }).then(() => {

                            });
                        }
                    })
            },
            report(){
                    
            }
        },
        mounted() {
            this.judgeComment()
            this.getloadComment()
        },
        computed: {

            getJudgeLogin() {
                return JSON.parse(localStorage.getItem('judge_login'));
            },
            // get_user_id(){
            //         return 
            // },
            getUserId() {
                // return localStorage.getItem('user_id');
                return this.$store.state.user_id;
            },
            getKnowledgeId() {
                return JSON.parse(localStorage.getItem('knowledge_id'));
            },
        },
    }
</script>
<style>
#comment .comment_other .van-row{
    border: 1px solid rgb(102, 102, 102);
    border-radius: 6px;
     height: 100px;
     margin: 5px;
     padding: 5px;
}

 #comment .comment_other .van-button {
    display: inline-block;
    height: 30px;
    width: 60px;
    font-size: 20px;
    vertical-align: middle;
  }
</style>
<style scoped>
    .comment_title h1 {
        font-size: 30px;
    }
    /*  */
    /*  */
    .comment_other_content{
       padding-top: 10px;
       padding-left: 10px;

    }
    .comment_other_title{
        text-align: left;
        font-size: 25px;
         overflow: scroll;
    white-space: nowrap;    
    }
    .comment_other_info{
        text-align: left;
        font-size: 20px;
         display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 2;
    overflow: scroll;
    }
    .comment_other_operation{
         height: 100px;
    line-height: 100px;
    }
</style>