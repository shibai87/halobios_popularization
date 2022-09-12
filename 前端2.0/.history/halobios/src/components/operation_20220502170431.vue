<template>
    <div id="knowledge_details_other">
        <van-row>
            <van-col span="8">
                <van-badge :content="GoodNum" color="#ff5000">
                    <van-icon name="good-job-o" :color="good_color" size="40" @click="change(0)" />
                </van-badge>
            </van-col>
            <van-col span="8">
                <van-badge :content="StarNum" color="#ff5000">
                    <van-icon name="star-o" :color="star_color" size="40" @click="change(1)" />
                </van-badge>
            </van-col>
            <van-col span="8">
                <van-badge :content="CommentNum" color="#ff5000">
                    <van-icon name="comment-o" size="40" />
                </van-badge>
            </van-col>
        </van-row>
    </div>
</template>

<script>
    import {
        Dialog
    } from 'vant';
    export default {
        name: 'knowledge_details_other',
        inject: ["reload"],
        data() {
            return {
                GoodNum: "",
                StarNum: "",
                CommentNum: "",
                good_color: "#000",
                star_color: "#000",
                good_id: "",
                star_id: "",
            }
        },
        methods: {
            getOperadionNumber() {
                this.$axios.get("http://192.168.1.105:8083/operation/getOperationNumber?operation_source_type=" + this
                        .getOperationSourceType +
                        "&operation_source_id=" + this.getSourceId + "&operation_type=" + 0)
                    .then((res) => {
                        this.GoodNum = res.data
                    })
                this.$axios.get("http://192.168.1.105:8083/operation/getOperationNumber?operation_source_type=" + this
                        .getOperationSourceType +
                        "&operation_source_id=" + this.getSourceId + "&operation_type=" + 1)
                    .then((res) => {
                        this.StarNum = res.data
                    })
                this.$axios.get("http://192.168.1.105:8083/operation/getOperationNumber?operation_source_type=" + this
                        .getOperationSourceType +
                        "&operation_source_id=" + this.getSourceId + "&operation_type=" + 2)
                    .then((res) => {
                        this.CommentNum = res.data
                    })
                if (this.getJudgeLogin) {
                    this.getUserOperation()
                }
            },
            getUserOperation() {
                this.$axios.get("http://192.168.1.105:8083/operation/getUserOperation?user_id=" + this.getUserId +
                        "&operation_source_type=" + this.getOperationSourceType + "&operation_source_id=" + this
                        .getSourceId + "&operation_type=" + 0)
                    .then((res) => {
                        if (res.data.msg == "用户已有操作") {
                            this.good_color = "#ff5000"
                            this.good_id = res.data.data
                        } else {
                            this.good_color = "#000"
                        }
                    })
                this.$axios.get("http://192.168.1.105:8083/operation/getUserOperation?user_id=" + this.getUserId +
                        "&operation_source_type=" + this.getOperationSourceType + "&operation_source_id=" + this
                        .getSourceId + "&operation_type=" + 1)
                    .then((res) => {
                          if (res.data.msg == "用户已有操作") {
                            this.star_color= "#ff5000"
                            this.star_id = res.data.data
                        } else {
                            this.star_color= "#000"
                        }
                    })
            },
            change(val) {
                if (this.getJudgeLogin) {
                    if (val == 0) {
                        // 取消操作
                        if (this.good_color == "#ff5000") {
                            this.axios.get("http://192.168.1.105:8083/operation/deleteOtherOperation?operation_id=" + this.good_id)
                                .then((res) => {
                                    if (res.data.msg == '删除操作成功') {
                                        Dialog.alert({
                                            message: '取消成功',
                                        }).then(() => {
                                            this.good_color = "#000"
                                            
                                            this.getOperadionNumber()
                                            this.reload()
                                            
                                        });
                                    } else {
                                        Dialog.alert({
                                            message: '取消失败',
                                        })
                                    }
                                })
                        }
                        // 添加操作
                        else {
                            this.$axios.get("http://192.168.1.105:8083/operation/addUserOperation?user_id=" + this
                                    .getUserId +
                                    "&operation_source_type=" + this.getOperationSourceType + "&operation_source_id=" +
                                    this
                                    .getSourceId + "&operation_type=" + 0)
                                .then((res) => {
                                    console.log(res.data)
                                    if (res.data.msg == '添加成功') {
                                        Dialog.alert({
                                            message: '点赞成功!',
                                        }).then(() => {
                                            this.good_color = "#ff5000"
                                            this.getOperadionNumber()
                                            this.reload()
                                        });
                                    } else {
                                        Dialog.alert({
                                            message: '点赞失败!',
                                        })
                                    }

                                })

                        }

                    } else {
                        // 取消操作
                        if (this.star_color == "#ff5000") {
                            this.axios.get("http://192.168.1.105:8083/operation/deleteOtherOperation?operation_id=" + this
                                    .star_id)
                                .then((res) => {
                                    if (res.data.msg == '删除操作成功') {
                                        Dialog.alert({
                                            message: '取消成功',
                                        }).then(() => {
                                            this.star_color = "#000"
                                            this.getOperadionNumber()
                                            this.reload()
                                        });
                                    } else {
                                        Dialog.alert({
                                            message: '取消失败',
                                        })
                                    }
                                })
                        }
                        // 添加操作
                        else {
                            this.$axios.get("http://192.168.1.105:8083/operation/addUserOperation?user_id=" + this
                                    .getUserId +
                                    "&operation_source_type=" + this.getOperationSourceType + "&operation_source_id=" +
                                    this
                                    .getSourceId + "&operation_type=" + 1)
                                .then((res) => {
                                    console.log(res.data)
                                    if (res.data.msg == '添加成功') {
                                        Dialog.alert({
                                            message: '收藏成功!',
                                        }).then(() => {
                                            this.star_color = "#ff5000"
                                            this.getOperadionNumber()
                                            this.reload()
                                        });
                                    } else {
                                        Dialog.alert({
                                            message: '收藏失败!',
                                        })
                                    }

                                })

                        }
                    }
                } else {
                    Dialog.alert({
                        message: '您未登录，无法进行该操作!',
                    })
                }

            },
        },
        computed: {
            getJudgeLogin() {
                return JSON.parse(sessionStorage.getItem('judge_login'))
            },
            getOperationSourceType() {
                return JSON.parse(sessionStorage.getItem('operation_source_type'))
            },
            getUserId() {
                return JSON.parse(sessionStorage.getItem('user_id'))
            },
            getSourceId() {
                return JSON.parse(sessionStorage.getItem('source_id'))
            }
        },
        mounted() {
            this.getOperadionNumber()
        },
    }
</script>

<style scoped>
    #knowledge_details_other {
        margin: 0 20px;
    }
</style>