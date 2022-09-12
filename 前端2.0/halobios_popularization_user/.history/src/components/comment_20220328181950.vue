<template>
    <div id="comment">
        <div class="comment_title">
            <h1>评论</h1>
        </div>
        <div class="comment_content">
            <van-field v-model="my_message" rows="1" center clearable autosize label="我的留言" type="textarea"
                placeholder="请输入留言"><template #button>
                    <van-button size="small" type="primary" @click="addComment">提交</van-button>
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
        data() {
            return {
                my_message: '',
                operationId: "",
            }
        },
        methods: {
            addOpration() {
                this.axios.get("http://192.168.1.103:8083/operation/addOperation?operation_user_id=" + this.getUserId +
                        "&operation_source_type=" + 0 +
                        "&operation_source_id=" + this.getKnowledge + "&operation_type=" + 2)
                    .then((res) => {
                      
                        if (res.data.msg=='添加成功') {
                            Dialog.alert({
                                title: '添加成功',
                                message: '恭喜你，添加成功!',
                            }).then(() => {

                            });
                        } else {
                            Dialog.alert({
                                title: '添加失败',
                                message: '添加失败!',
                            }).then(() => {
                                // on close
                            });
                        }
                    })
            },
            getOperation() {
                let id
                this.axios.get("http://192.168.1.103:8083/operation/getOperationId?operation_user_id=" + this
                        .getUserId + "&operation_source_type=" + 0 +
                        "&operation_source_id=" + this.getKnowledge + "&operation_type=" + 2)
                    .then((res) => {
                         console.log("res.data")
                         console.log(res.data)
                         id=res.data.operation_id
                        // if (res.data.meg== "查询成功") {
                        //     Dialog.alert({
                        //         title: '查询成功',
                        //         message: '恭喜你，查询成功!',
                        //     }).then(() => {
                        //         this.operationId = res.data.data
                        //     });
                        // } else {
                        //     Dialog.alert({
                        //         title: '查询失败',
                        //         message: '查询失败!',
                        //     }).then(() => {
                        //         // on close
                        //     });
                        // }
                    })
                    return id;
            },
            addComment() {
                this.addOpration()
               
                let id = this.getOperation()
                this.axios.get("http://192.168.1.103:8083/operation/addComment?operation_id=" + id +
                    "&comment_content=" + this.my_message).then((res) => {
                   console.log(res.data,res.data.msg)
                    if (res.data.meg== "添加成功") {
                        Dialog.alert({
                            title: '添加成功',
                            message: '恭喜你，添加成功!',
                        }).then(() => {

                        });
                    } else {
                        Dialog.alert({
                            title: '添加失败',
                            message: '添加失败!',
                        }).then(() => {
                            // on close
                        });
                    }
                })
            },

        },
        mounted() {

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

</style>