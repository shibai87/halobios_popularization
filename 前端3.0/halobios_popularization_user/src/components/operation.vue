<template>
    <div id="knowledge_details_other">
        <van-row>
            <van-col span="6" offset="4">
                <van-badge :content="GoodNum" color="#ff5000">
                    <van-icon name="good-job-o" :color="good_color" size="40" @click="change(0)" />
                </van-badge>
            </van-col>
            <van-col span="6">
                <van-badge :content="StarNum" color="#ff5000">
                    <van-icon name="star-o" :color="star_color" size="40" @click="change(1)" />
                </van-badge>
            </van-col>
            <van-col span="6">
                <van-badge :content="CommentNum" color="#ff5000">
                    <van-icon name="comment-o" size="40" />
                </van-badge>
            </van-col>
        </van-row>
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

import OperationService from "../api/OperationService.js"
const refresh = inject<any>("reload")

const GoodNum = ref("")
const StarNum = ref("")
const CommentNum = ref("")
const good_color = ref("")
const star_color = ref("")
const good_id = ref("")
const star_id = ref("")
// 
const getJudgeLogin = ref(JSON.parse(sessionStorage.getItem('judge_login') || ""))
const getOperationSourceType = ref(JSON.parse(sessionStorage.getItem('operation_source_type') || ""))
const getUserId = ref(JSON.parse(sessionStorage.getItem('user_id') || ""))
const getSourceId = ref(JSON.parse(sessionStorage.getItem('source_id') || ""))
onMounted(() => {
    getOperadionNumber()
})

const getOperadionNumber = () => {
    OperationService.getOperationNumber(getOperationSourceType.value, getSourceId.value, 0)
        .then((res) => {
            GoodNum.value = res
        })
    OperationService.getOperationNumber(getOperationSourceType.value, getSourceId.value, 1)
        .then((res) => {
            StarNum.value = res
        })
    OperationService.getOperationNumber(getOperationSourceType.value, getSourceId.value, 2)
        .then((res) => {
            CommentNum.value = res
        })
    if (getJudgeLogin.value) {
        getUserOperation()
    }
}
const getUserOperation = () => {
    OperationService.getUserOperation(getUserId.value, getOperationSourceType.value, getSourceId.value, 0)
        .then((res) => {
            if (res.msg == '用户已有操作') {
                good_color.value = "#ff5000"
                good_id.value = res.data
            } else {
                good_color.value = "#000"
            }
        })
    OperationService.getUserOperation(getUserId.value, getOperationSourceType.value, getSourceId.value, 1)
        .then((res) => {
            if (res.msg == '用户已有操作') {
                star_color.value = "#ff5000"
                star_id.value = res.data
            } else {
                star_color.value = "#000"
            }
        })
}
const change = (val: any) => {
    if (getJudgeLogin.value) {
        if (val == 0) {
            // 取消操作
            if (good_color.value == "#ff5000") {
                OperationService.deleteOtherOperation(good_id.value).then((res) => {
                    if (res.msg == '删除操作成功') {
                        Dialog.alert({
                            message: '取消成功',
                        }).then(() => {
                            good_color.value = "#000"
                            getOperadionNumber()
                            refresh()
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
                OperationService.addUserOperation(getUserId.value, getOperationSourceType.value,
                    getSourceId.value, 0)
                    .then((res) => {
                        if (res.msg == '添加成功') {
                            Dialog.alert({
                                message: '点赞成功!',
                            }).then(() => {
                                good_color.value = "#ff5000"
                                getOperadionNumber()
                                refresh()
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
            if (star_color.value == "#ff5000") {
                OperationService.deleteOtherOperation(star_id.value)
                    .then((res) => {
                        if (res.msg == '删除操作成功') {
                            Dialog.alert({
                                message: '取消成功',
                            }).then(() => {
                                star_color.value = "#000"
                                getOperadionNumber()
                                refresh()
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
                OperationService.addUserOperation(getUserId.value, getOperationSourceType.value,
                    getSourceId.value, 1)
                    .then((res) => {
                        if (res.msg == '添加成功') {
                            Dialog.alert({
                                message: '收藏成功!',
                            }).then(() => {
                                star_color.value = "#ff5000"
                                getOperadionNumber()
                                refresh()
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
}
</script>

<style scoped>
#knowledge_details_other {
    margin: 20px 0;
}
</style>