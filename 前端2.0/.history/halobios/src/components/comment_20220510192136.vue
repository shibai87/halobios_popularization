<template>
    <div id="comment">
        <header>
            <h1 class="comment_title">评论</h1>
        </header>
        <main>
            <!-- 用户个人评论显示 -->
            <div class="comment_my">
                <!-- 用户未登录 -->
                <div v-if="!this.getJudgeLogin">
                    <van-notice-bar text="您未登录，无法管理个人评论！" />
                    <router-link to="/login">
                        <van-button type="primary" class="login" block size="small">去登录</van-button>
                    </router-link>
                </div>
                <!-- 用户已登录 -->
                <div v-else>
                    <!-- 用户已评论 -->
                    <div v-if="my_judge_comment">
                        <!-- 用户未被举报或举报通过 -->
                        <div v-if="my_report==0||my_report==1">
                            <van-field v-model="my_message" rows="1" center clearable autosize label="我的留言"
                                type="textarea" placeholder="请输入留言">
                                <template #button>
                                    <van-row gutter="10">
                                        <van-col span="12">
                                            <van-button size="small" type="primary" @click="updateComment">修改
                                            </van-button>
                                        </van-col>
                                        <van-col span="12">
                                            <van-button size="small" type="warning" @click="deleteComment">删除
                                            </van-button>
                                        </van-col>
                                    </van-row>
                                </template>
                            </van-field>
                        </div>
                        <!-- 用户已被举报 -->
                        <div v-else>
                            <van-notice-bar v-if="my_report==2" :scrollable="false" text="您的评论已被举报，正在审核中，您可选择删除评论！" />
                            <van-notice-bar v-else-if="my_report==3" scrollable
                                text="您的评论已被举报，审核未通过，已设置该评论对其他用户不可见！您可选择删除评论！" />
                            <van-field v-model="my_message" rows="1" center clearable autosize label="我的留言"
                                type="textarea" placeholder="请输入留言" readonly>
                                <template #button>
                                    <van-button size="small" type="primary" @click="deleteComment">删除</van-button>
                                </template>
                            </van-field>
                        </div>
                    </div>
                    <!-- 用户未评论 -->
                    <div v-else>
                        <van-field v-model="my_message" rows="1" center clearable autosize label="我的留言" type="textarea"
                            placeholder="未留言，可输入留言">
                            <template #button>
                                <van-button size="small" type="primary" @click="addComment">提交评论</van-button>
                            </template>
                        </van-field>
                    </div>
                </div>
            </div>
            <!-- 其他用户评论显示 -->
            <!-- <h2>其他用户评论</h2> -->
            <div class="comment_other">
                <div v-if="commentList.length" class="comment_other">
                    <div v-for="(item,index) in commentList" :key="index">
                        <van-row v-if="item.comment_state==0||item.cmmment_state==1">
                            <van-col span="15" offset="2" class="comment_other_content">
                                <div class="comment_other_title">
                                    <div v-if="item.user_name">用户名：{{item.user_name}}</div>
                                    <div v-else>该用户未取用户名</div>
                                </div>

                                <div class="comment_other_info">留言：{{item.comment_content}}</div>
                            </van-col>
                            <van-col span="3" offset="2" class="comment_other_operation">
                                <van-button size="small" type="primary" @click="report(item.comment_id)">举报</van-button>
                            </van-col>
                        </van-row>
                    </div>
                </div>
                <div v-else>
                    <van-empty image="error" description="未发现有人评论" />
                </div>
            </div>
        </main>
    </div>
</template>

<script>
    import {
        Dialog
    } from 'vant';
    import OperationService from "../api/OperationService"
    import ReportService from "../api/ReportService.js"
    import CommentService from "../api/CommentService"
    export default {
        name: 'cmmment',
        inject: ["reload"],
        data() {
            return {
                // 用户个人
                // 用户是否已评论
                my_judge_comment: "",
                // 用户的评论是否被举报  0为通过，1为待审核，2为不通过
                my_report: "",
                my_old_message: "",
                my_message: "",
                operation_id: "",
                commentList: [],
            }
        },
        methods: {
            // 判断该登录用户是否已评论(查询该用户评论信息)
            judgeComment() {
                OperationService.judgeOperationId(this.getUserId, this.getOperationSourceType, this.getSourceId).then((
                    res) => {
                    if (res.msg == '用户已有过该操作') {
                        this.my_judge_comment = true
                        this.my_message = res.data.comment_content
                        this.my_old_message = this.my_message
                        this.my_report = res.data.comment_state
                        this.operation_id = res.data.operation_id;
                    } else {
                        this.my_judge_comment = false
                    }
                })
            },
            // 添加我的评论
            addComment() {
                if (this.my_message) {
                    CommentService.addComment(this.getUserId, this.getOperationSourceType, this.getSourceId, this
                        .my_message).then((res) => {
                        if (res.msg == '添加评论成功') {
                            Dialog.alert({
                                message: '添加评论成功!',
                            }).then(() => {
                                this.reload()
                                this.judgeComment()
                            });
                        } else if (res.msg == '评论内容违禁') {
                            Dialog.alert({
                                message: '评论内容违禁,请重新输入!',
                            }).then(() => {
                                this.reload()
                            });
                        } else {
                            Dialog.alert({
                                message: '添加评论失败!',
                            });
                        }
                    })
                } else {
                    Dialog.alert({
                        title: '评论失败',
                        message: '评论内容为空，请继续输入!',
                    })
                }

            },
            // 修改我的评论内容
            updateComment() {
                // 如果用户清空评论内容，则认为用户删除自己的评论
                if (this.my_message) {
                    if (this.my_old_message == this.my_message) {
                        Dialog.alert({
                            message: '您未修改内容！',
                        })
                    } else {
                        CommentService.updateComment(this.operation_id, this.my_message).then((res) => {
                            if (res.msg == '修改评论内容成功') {
                                Dialog.alert({
                                    message: '修改评论内容成功！',
                                })
                            } else {
                                Dialog.alert({
                                    message: '修改评论内容失败！',
                                })
                            }
                        })
                    }
                } else {
                    this.deleteComment()
                }

            },
            // 删除我的评论
            deleteComment() {
                Dialog.confirm({
                        title: '标题',
                        message: '弹窗内容',
                    })
                    .then(() => {
                        OperationService.deleteComment(this.operation_id).then((res) => {
                            if (res.msg == '删除评论成功') {
                                Dialog.alert({
                                    message: '删除评论成功',
                                }).then(() => {
                                    this.my_message = null
                                    this.judgeComment()
                                });
                            } else {
                                Dialog.alert({
                                    message: '删除评论失败',
                                })
                            }
                        })
                    })
                    .catch(() => {
                        // on cancel
                        alert("1")
                    });

            },

            //查询评论列表
            getloadComment() {
                if (this.getJudgeLogin) {
                    this.judgeComment()
                    OperationService.getCommentList(this.getOperationSourceType, this.getSourceId, this.getUserId).then(
                        (res) => {
                            this.commentList = res
                        })
                } else {
                    OperationService.getCommentList(this.getOperationSourceType, this.getSourceId).then((res) => {
                        this.commentList = res
                    })
                }
            },
            // 举报
            report(comment_id) {
                ReportService.add(this.operation_id, comment_id, this.getUserId).then((res) => {
                    if (res.msg == '举报成功') {
                        Dialog.alert({
                            message: '举报成功!',
                        }).then(() => {
                            this.reload()
                            this.getloadComment()
                        });
                    } else {
                        Dialog.alert({
                            message: '举报失败!',
                        })
                    }
                })
            },
        },
        mounted() {
            this.getloadComment()
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
    }
</script>

<style lang="less">
    #comment {
        .comment_other .van-row {
            border: 1px solid rgb(102, 102, 102);
            border-radius: 6px;
            height: 100px;
            margin: 5px;
            padding: 5px;
        }

        .comment_other .van-button {
            display: inline-block;
            height: 30px;
            width: 60px;
            font-size: 20px;
            vertical-align: middle;
        }
    }
</style>

<style scoped>
    .comment_title {
        font-size: 30px;
    }

    .comment_other_content {
        padding-top: 10px;
        padding-left: 10px;

    }

    .comment_other_title {
        text-align: left;
        font-size: 25px;
        overflow: scroll;
        white-space: nowrap;
    }

    .comment_other_info {
        text-align: left;
        font-size: 20px;
        display: -webkit-box;
        -webkit-box-orient: vertical;
        -webkit-line-clamp: 2;
        overflow: scroll;
    }

    .comment_other_operation {
        height: 100px;
        line-height: 100px;
    }
</style>