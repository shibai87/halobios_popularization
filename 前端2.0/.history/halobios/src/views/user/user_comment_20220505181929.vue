<template>
    <div id="user_comment">
        <van-nav-bar title="我的评论" left-arrow fixed @click-left="pageBack" />
        <van-pull-refresh v-model="isLoading" @refresh="onRefresh">
            <div class="comment_other" v-if="commentList.length">
                <div v-for="(item,index) in commentList" :key="index">
                    <van-row>
                        <van-notice-bar v-if="item.comment_state==1" :scrollable="false"
                            text="您的评论已被举报，正在审核中，您可选择删除评论！" />
                        <van-notice-bar v-else-if="item.comment_state==2" scrollable
                            text="您的评论已被举报，审核未通过，已设置该评论对其他用户不可见！您可选择删除评论！" />
                        <van-col span="13" offset="2" class="comment_other_content">
                            我的留言：
                            <div>{{item.comment_content}}</div>
                        </van-col>
                        <van-col span="5" offset="2" class="comment_other_operation">
                            <van-button size="small" type="primary"
                                @click="user_operation_detail(item.operation_source_type,item.operation_source_id)">查看详情
                            </van-button>
                            <van-button size="small" type="warning" @click="deleteComment(item.operation_id)">
                                删除</van-button>
                        </van-col>
                    </van-row>
                </div>
            </div>
            <div v-else class="comment_other">
                <van-empty image="error" description="未发现有人评论" />
            </div>
        </van-pull-refresh>

    </div>
</template>

<script>
    import {
        Toast
    } from 'vant';
    import {
        Dialog
    } from 'vant';
import UserService from '../../api/UserService';
    import KnowledgeService from"../../api/KnowledgeService.js"
    export default {
        name: '',
        inject: ["reload"],
        data() {
            return {
                // 刷新
                isLoading: false,

                commentList: [],
            }
        },
        methods: {
            pageBack() {
                this.$router.push("/user")
            },
            // 刷新
            onRefresh() {
                setTimeout(() => {
                    Toast('刷新成功');
                    this.isLoading = false;
                    this.reload()
                }, 1000);
            },
            getloadComment() {
                    UserService.getloadComment(this.getUserId).then((res) => {
                        console.log(res)
                        this.commentList = res
                    })
            },
            user_operation_detail(val, operation_source_id) {
                if (val == 0) {
                    // 科普知识
                         KnowledgeService.getLoadKnowledge(operation_source_id).then((res) => {
                            console.log(res.data[0])
                            if (res.data[0].knowledge_state == 0) {
                                sessionStorage.setItem('source_id', operation_source_id);
                                this.$router.push("/knowledge_details")
                            } else {
                                Dialog.alert({
                                    title: '查看失败',
                                    message: '该资源已被删除，无法查看详情!',
                                })
                            }
                        });
                }
                // 科普活动
                if (val == 1) {
                    this.axios.get("http://192.168.197.65:8083/activity/get_activity?activity_id=" + operation_source_id)
                        .then((res) => {
                            console.log(res.data)
                            if (res.data.activity_state == 0) {
                                sessionStorage.setItem('source_id', operation_source_id);
                                this.$router.push("/activity_details")
                            } else {
                                Dialog.alert({
                                    title: '查看失败',
                                    message: '该资源已被删除，无法查看详情!',
                                })
                            }
                        });
                }
                if (val == 2) {
                    // 科普小课堂
                    this.axios.get("http://192.168.197.65:8083/video/get_video?video_id=" + operation_source_id)
                        .then((res) => {
                            console.log(res.data)
                            if (res.data.video_state == 0) {
                                sessionStorage.setItem('source_id', operation_source_id);
                                this.$router.push("/classroom_details")
                            } else {
                                Dialog.alert({
                                    title: '查看失败',
                                    message: '该资源已被删除，无法查看详情!',
                                })
                            }
                        });
                }

            },
            deleteComment(operation_id) {
                Dialog.alert({
                    title: '删除个人评论',
                    message: '确认删除个人评论？',
                }).then(() => {
                    this.axios.get("http://192.168.197.65:8083/operation/deleteComment?operation_id=" +
                            operation_id)
                        .then((res) => {
                            if (res.data.msg == '删除评论成功') {
                                Dialog.alert({
                                    message: '删除评论成功',
                                }).then(() => {
                                    this.my_message = null
                                    this.getloadComment()
                                });
                            } else {
                                Dialog.alert({
                                    message: '删除评论失败',
                                })
                            }
                        })
                })

            }
        },
        mounted() {
            this.getloadComment()
        },
        computed: {

            getJudgeLogin() {
                return JSON.parse(sessionStorage.getItem('judge_login'));
            },
            getUserId() {
                return JSON.parse(sessionStorage.getItem('user_id'));
            },
        },
    }
</script>

<style lang="less">
    #user_comment {

        /* user_infos_head */
        .van-nav-bar__content {
            height: 70px;
        }

        .van-nav-bar__title {
            height: 30px;
            line-height: 30px;
            font-size: 25px;
        }

        .van-icon:before {
            color: #000;
            font-size: 35px;
        }

        /*  */
        .van-row {
            border: 1px solid rgb(102, 102, 102);
            border-radius: 6px;
            margin: 5px;
            padding: 5px;
        }

        .van-button {
            height: 30px;
            width: 100px;
            font-size: 20px;
            vertical-align: middle;
        }

        .van-empty {
            height: 750px;
        }
    }
</style>

<style scoped>
    .comment_other {
        margin-top: 80px;
        height: 750px;
    }

    .comment_other_content {
        text-align: left;
        font-size: 20px;
        display: -webkit-box;
        -webkit-box-orient: vertical;
        -webkit-line-clamp: 2;
        overflow: scroll;
        vertical-align: middle;

    }

    .comment_other_operation {
        height: 100px;

    }
</style>