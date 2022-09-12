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
                operationId:"",
            }
        },
        methods: {
           
            addComment() {
                 let id;
                 this.axios.get("http://192.168.1.103:8083/operation/addOperation?operation_user_id=2&operation_source_type=2&operation_source_id=2&operation_type=2")
                    .then((res) => {
                        console.log(res.data.msg)
                        if (!res.data.msg == '添加成功') {
                            Dialog.alert({
                                title: '添加失败',
                                message: '添加失败!',
                            }).then(() => {
                                // on close
                            });
                        }
                    })
                      this.axios.get("http://192.168.1.103:8083/operation/getOperationId?operation_user_id=2&operation_source_type=2&operation_source_id=2&operation_type=2")
                    .then((res) => {
                        console.log("2.5")
                        console.log(res.data,res.data.data)
                         this.operationId = res.data.data
                           id=res.data.data
                         this.aa(id,this.operationId)
                        // if (res.data.msg == '查询失败'&&res.data.code==0) {
                        //     Dialog.alert({
                        //         title: '查询失败',
                        //         message: '查询失败!',
                        //     }).then(() => {
                        //         // on close
                        //     });
                        // }
                    })
              
            },
            aa(val1,val2){
                        console.log(val1,val2)
                     this.axios.get("http://192.168.1.103:8083/operation/addComment?operation_id=2&comment_content=2dfghj").then((res) => {
                          console.log(res.data)
                    if (res.data.msg == '添加成功') {
                        Dialog.alert({
                            title: '添加成功',
                            message: '恭喜你，添加评论成功!',
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
            addOpration() {
                  console.log("1")
                console.log(this.getUserId,this.getKnowledge )
                this.axios.get("http://192.168.1.103:8083/operation/addOperation?operation_user_id=" + this.getUserId +
                        "&operation_source_type=" + 0 +
                        "&operation_source_id=" + this.getKnowledge + "&operation_type=" + 2)
                    .then((res) => {
                        if (!res.data.msg == '添加成功') {
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
                 console.log("2")
                console.log(this.getUserId,this.getKnowledge )
                this.axios.get("http://192.168.1.103:8083/operation/getOperationId?operation_user_id=1&operation_source_type=0&operation_source_id=1&operation_type=2")
                    .then((res) => {
                        console.log("2.5")
                        console.log(res.data,res.data.data)
                         this.operationId = res.data.data
                         console.log(this.operationId)
                        if (res.data.msg == '查询失败'&&res.data.code==0) {
                            Dialog.alert({
                                title: '查询失败',
                                message: '查询失败!',
                            }).then(() => {
                                // on close
                            });
                        }
                    })

            },
            getComment() {
                console.log("3")
                console.log(this.operationId)
                this.axios.get("http://192.168.1.103:8083/operation/addComment?operation_id=" + this.operationId +
                    "&comment_content=" + this.my_message).then((res) => {
                    if (res.data.msg == '添加成功') {
                        Dialog.alert({
                            title: '添加成功',
                            message: '恭喜你，添加评论成功!',
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