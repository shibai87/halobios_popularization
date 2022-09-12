<template>
    <div id="comment">
        <div class="comment_title">
            <h1>评论</h1>
        </div>
        <div class="comment_content">
            <!-- <div v-if="this.getUserId">
                  <div v-if="my_judge_comment">
                <div v-if="!my_report">
                    <van-field v-model="my_message" rows="1" center clearable autosize label="我的留言" type="textarea"
                        placeholder="请输入留言">
                        <template #button>
                            <van-button size="small" type="primary" @click="updateComment">提交修改</van-button>
                        </template>
                    </van-field>
                </div>
                <div v-else>
                    <van-notice-bar v-if="my_report==1" :scrollable="false" text="您的评论已被举报，正在审核中，您可选择删除评论！" />
                    <van-notice-bar v-else-if="my_report==2" scrollable text="您的评论已被举报，审核未通过，已设置该评论对其他用户不可见！您可选择删除评论！" />
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
            <div v-else>
                 <van-notice-bar v-if="my_report==1" :scrollable="false" text="您未登录，无法管理个人评论" />
            </div> -->
          

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
                // 用户是否已评论
                my_judge_comment: "",
                // 用户的评论是否被举报  0为通过，1为待审核，2为不通过
                my_report: "",
                my_message: "",
                operation_id: "",
            }
        },
        methods: {
            getloadComment() {
                this.axios.get("http://192.168.1.103:8083/operation/getCommentList")
            },
            // 判断该登录用户是否已评论
            judgeComment() {
                if(this.getUserId){
                      // console.log(this.getUserId,this.getKnowledgeId)
                this.axios.get(
                        "http://192.168.1.103:8083/operation/judgeOperationId?operation_user_id=" + this.getUserId +
                        "&operation_source_type=2" + "&operation_source_id=" + this.getKnowledgeId +
                        "&operation_type=2"
                    )
                    .then((res) => {
                        console.log("查询用户是否有该操作和评论列表")
                        console.log(res.data,res.data.msg, res.data.data.operation_id, res.data.data.comment_content,
                            res.data.data.comment_state)
                        if (res.data.msg == '用户已有过该操作') {
                            this.my_judge_comment = true
                            this.my_message = res.data.data.comment_content
                            this.my_report = res.data.data.comment_state
                        } else {
                            this.my_judge_comment = false
                        }
                    })
                }else{
                    // console.log(this.getUserId)
                }
              


            },
            // 添加我的评论
            addComment() {
                this.axios.post(
                        "http://192.168.1.103:8083/operation/addComment?operation_user_id=" + this.getUserId +
                        "&operation_source_type=2" + "&operation_source_id=" + this.getKnowledgeId +
                        "&operation_type=2" + "&comment_content=" + this.my_message
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
            updateComment(){
                    this.axios.get("http://192.168.1.103:8083/operation/updateComment?operation_id=188&comment_content=123")
                    .then((res)=>{
                        console.log(res.data,res.data.msg)
                        if(res.data.msg=='修改评论内容成功'){
                             Dialog.alert({
                                message: '修改评论内容成功！',
                            }).then(() => {
                               
                            });
                        }else{
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
                                this.judgeComment()
                            });
                        } else {
                            Dialog.alert({
                                message: '删除评论失败',
                            }).then(() => {

                            });
                        }
                    })
            }
        },
        mounted() {
            this.judgeComment()
        },
        computed: {
            getUserId() {
                return JSON.parse(localStorage.getItem('user_id'));
            },
            getKnowledgeId() {
                return JSON.parse(localStorage.getItem('knowledge_id'));
            },
        },
    }
</script>

<style scoped>
    .comment_title h1 {
        font-size: 30px;
    }
</style>