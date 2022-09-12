<template>
    <div id="comment">
        <header>
            <h1 class="comment_title">评论</h1>
        </header>
        <main>
            <!-- 用户个人评论显示 -->
            <div class="comment_my">
                <!-- 用户未登录 -->
                <div v-if="!getJudgeLogin">
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

<script lang="ts" setup>
import { ref, reactive } from "vue"
import { inject } from "vue"
import { onMounted } from "vue";
import { useStore } from "vuex"
import { useRouter } from 'vue-router'

import { Toast } from 'vant';
import { Dialog } from 'vant';

import OperationService from "../api/OperationService"
import ReportService from "../api/ReportService.js"
import CommentService from "../api/CommentService"
const refresh = inject<any>("reload")
// 用户个人
// 用户是否已评论
const my_judge_comment = ref<boolean>()
// 用户的评论是否被举报  0为通过，1为待审核，2为不通过
const my_report = ref<number>()
const my_old_message = ref<string>("")
const my_message = ref<string>("")
const operation_id = ref<string>("")
const commentList = ref<any>([{
    comment_state: "",
}

])
// 
const getJudgeLogin = ref<any>(JSON.parse(sessionStorage.getItem('judge_login') || ""))
const getOperationSourceType = ref<any>(JSON.parse(sessionStorage.getItem('operation_source_type') || ""))
const getUserId = ref<any>(JSON.parse(sessionStorage.getItem('user_id') || ""))
const getSourceId = ref<any>(JSON.parse(sessionStorage.getItem('source_id') || ""))
onMounted(() => {
    getloadComment()
})
//查询评论列表
const getloadComment = () => {
    if (getJudgeLogin.value) {
        judgeComment()
        OperationService.getCommentList(getOperationSourceType.value, getSourceId.value, getUserId.value)
            .then(
                (res) => {
                    commentList.value = res
                })
    } else {
        OperationService.getCommentList(getOperationSourceType.value, getSourceId.value).then((res) => {
            commentList.value = res
        })
    }
}
// 判断该登录用户是否已评论(查询该用户评论信息)
const judgeComment = () => {
    OperationService.judgeOperationId(getUserId.value, getOperationSourceType.value, getSourceId.value)
        .then((res) => {
            if (res.msg == '用户已有过该操作') {
                my_judge_comment.value = true
                my_message.value = res.data.comment_content
                my_old_message.value = my_message.value
                my_report.value = res.data.comment_state
                operation_id.value = res.data.operation_id;
            } else {
                my_judge_comment.value = false
            }
        })
}
// 添加我的评论
const addComment = () => {
    if (my_message.value) {
        CommentService.addComment(getUserId.value, getOperationSourceType.value, getSourceId.value,
            my_message.value)
            .then((res) => {
                if (res.msg == '添加评论成功') {
                    Dialog.alert({
                        message: '添加评论成功!',
                    }).then(() => {
                        refresh()
                        judgeComment()
                    });
                } else if (res.msg == '评论内容违禁') {
                    Dialog.alert({
                        message: '评论内容违禁,请重新输入!',
                    }).then(() => {
                        refresh()
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

}
// 修改我的评论内容
const updateComment = () => {
    // 如果用户清空评论内容，则认为用户删除自己的评论
    if (my_message.value) {
        if (my_old_message.value == my_message.value) {
            Dialog.alert({
                message: '您未修改内容！',
            })
        } else {
            CommentService.updateComment(operation_id.value, my_message.value)
                .then((res) => {
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
        deleteComment()
    }
}
// 删除我的评论
const deleteComment = () => {
    Dialog.confirm({
        message: '确认删除评论？',
    })
        .then(() => {
            OperationService.deleteComment(operation_id.value)
                .then((res) => {
                    if (res.msg == '删除评论成功') {
                        Dialog.alert({
                            message: '删除评论成功',
                        }).then(() => {
                            my_message.value = ""
                            judgeComment()
                        });
                    } else {
                        Dialog.alert({
                            message: '删除评论失败',
                        })
                    }
                })
        })
        .catch(() => {
        });
}
// 举报
const report = (comment_id: any) => {
    Dialog.confirm({
        message: '确认举报评论？',
    })
        .then(() => {
            ReportService.add(operation_id.value, comment_id, getUserId.value)
                .then((res) => {
                    if (res.msg == '举报成功') {
                        Dialog.alert({
                            message: '举报成功!',
                        }).then(() => {
                            refresh()
                            getloadComment()
                        });
                    } else {
                        Dialog.alert({
                            message: '举报失败!',
                        })
                    }
                })
        })
        .catch(() => {
        });
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