<template>
    <div id="comment">
        <div class="comment_title">
            <h1>评论</h1>
        </div>
        <div class="comment_content">
            <van-field v-model="my_message" rows="1" center clearable autosize label="我的留言" type="textarea"
                placeholder="请输入留言">
                <template #button>
                    <div v-if="judge_comment">
                        <van-button size="small" type="primary">删除</van-button>
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
                judge_comment: "",
                my_message: ""
            }
        },
        methods: {
            judgeComment(){
                console.log(this.getUserId,this.getKnowledgeId)
            },
            addComment() {
                this.axios.post(
                        "http://192.168.1.103:8083/operation//addSort?operation_user_id="+this.getUserId
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
                                // on close
                            });
                        } else {
                            Dialog.alert({
                                message: '添加评论失败!',
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