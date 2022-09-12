<template>
    <div id="comment">
        <div class="comment_title">
            <h1>评论</h1>
        </div>
        <div class="comment_content">
            <van-field v-model="my_message" rows="1" center clearable autosize label="我的留言" type="textarea"
                placeholder="请输入留言">
                <template #button>
                    <div v-if="my_judge_comment">
                        <van-button size="small" type="primary" @click="deleteComment">删除</van-button>
                    </div>
                    <div v-else>
                        <van-button size="small" type="primary" @click="addComment">提交</van-button>
                    </div>
                </template>
            </van-field>
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
                my_judge_comment: "",
                my_message: "",
                operation_id:"",
            }
        },
        methods: {
            getloadComment(){
                this.axios.get("http://192.168.1.103:8083/operation/getCommentList")
            },
            judgeComment(){
                // console.log(this.getUserId,this.getKnowledgeId)
                   this.axios.get(
                        "http://192.168.1.103:8083/operation/judgeOperationId?operation_user_id="+this.getUserId
                        +"&operation_source_type=2"+"&operation_source_id="+this.getKnowledgeId
                        +"&operation_type=2"
                        )
                    .then((res) => {
                        console.log("查询用户是否有该操作和评论列表")
                        console.log(res,res.data.msg,res.data.data.operation_id,res.data.data.comment_content)
                        if (res.data.msg == '用户已有过该操作') {
                            this.my_judge_comment=true
                            this.operation_id=res.data.data.operation_id
                            this.my_message=res.data.data.operation_id
                        } else {
                           this.my_judge_comment=false
                        }
                    })
                    

            },
            addComment() {
                this.axios.post(
                        "http://192.168.1.103:8083/operation/addComment?operation_user_id="+this.getUserId
                        +"&operation_source_type=2"+"&operation_source_id="+this.getKnowledgeId
                        +"&operation_type=2"+"&comment_content="+this.my_message
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
            deleteComment(){
                this.axios.get("http://192.168.1.103:8083/operation/deleteComment?operation_id="+this.operation_id)
                .then((res)=>{
                    console.log(res,res.data.msg)
                    if(res.data.msg=='删除评论成功'){
                         Dialog.alert({
                                message: '删除评论成功',
                            }).then(() => {
                                this.judgeComment()
                            });
                    }else{
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