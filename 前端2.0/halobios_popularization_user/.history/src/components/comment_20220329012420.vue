<template>
    <div id="comment">
        <div class="comment_title">
            <h1>评论</h1>
        </div>
        <div class="comment_content">
            <van-field v-model="my_message" rows="1" center clearable autosize label="我的留言" type="textarea"
                placeholder="请输入留言"><template #button>
                    <van-button size="small" type="primary" @click="addComment" :v-if="this.judge_comment">提交</van-button>
                    <van-button size="small" type="primary"  :v-else>删除</van-button>
                </template></van-field>
        </div>
    </div>
</template>

<script>
    import {
        Dialog
    } from 'vant';
    export default {
        name: 'comment',
        inject: ["reload"],
        data() {
            return {
                judge_comment:false,
                my_message: '',
                operationId: null,
            }
        },
        methods: {
            judgeComment(){
                this.axios.get("http://192.168.1.103:8083/operation/getCommentId?operation_user_id=2&operation_source_type=2&operation_source_id=2&operation_type=2")
                .then((res)=>{
                    console.log(res.data)
                    if(res.data.code!=0){
                        this.judge_comment=true
                    }
                    console.log(this.judge_comment)
                   
                })
            },
            addComment() {
                if(this.operationId){
                      Dialog.alert({
                           title: '添加评论',
                                message: '您已评论过了!!',
                            }).then(() => {
                                // on close
                            });
                }else{
                      this.axios.get("http://192.168.1.103:8083/operation/getOperationId?operation_user_id=" + this
                        .getUserId +
                        "&operation_source_type=" + 0 + "&operation_source_id=" + this.getKnowledge +
                        "&operation_type=" + 2)
                    .then((res) => {
                        console.log("1",res.data)
                        if (res.data.msg == '查询成功') {
                                   this.operationId = res.data.data
                        this.getComment(this.operationId)
                        } else {
                            Dialog.alert({
                                message: '查询成功!',
                            }).then(() => {
                                // on close
                            });
                        }
                    })
                }
              
            },
            getComment(val) {
                console.log(val)
                this.axios.get("http://192.168.1.103:8083/operation/addComment?operation_id=" + val +
                        "&comment_content=" + this.my_message)
                    .then((res) => {
                         console.log("2",res.data)
                        if (res.data.msg == '添加成功') {
                            Dialog.alert({
                                title: '添加评论',
                                message: '添加成功!',
                            }).then(() => {
                               this.judge_comment=true
                               this.judgeComment()
                               this.reload()
                            });
                        } else {
                            Dialog.alert({
                                title: '添加评论',
                                message: '添加失败!',
                            }).then(() => {
                                // on close
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
            getKnowledge() {
                return JSON.parse(localStorage.getItem('knowledge_id'));
            },
        },
    }
</script>

<style scoped>
.comment_title h1{
    font-size: 30px;
}
</style>