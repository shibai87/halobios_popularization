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

<script lang="ts" setup>
import { ref, reactive } from "vue"
import { onMounted } from "vue";
import { useStore } from "vuex"
import { useRouter } from 'vue-router'
import { inject } from "vue"
import { Toast } from 'vant';
import { Dialog } from 'vant';
 
import OperationService from "../../api/OperationService.js"
import KnowledgeService from"../../api/KnowledgeService.js"
import ActivityService from "../../api/ActivityService.js"
import VideoService from "../../api/VideoService.js"
import CommentService from"../../api/CommentService.js"
const store = useStore()
const router = useRouter()
const refresh = inject<any>("reload")
   //              // 刷新
const isLoading = ref<boolean>(false)
const commentList= ref<any>([])
    const getJudgeLogin= ref<any>(JSON.parse(sessionStorage.getItem('judge_login') || ""))
        const getUserId= ref<any>(JSON.parse(sessionStorage.getItem('user_id') || ""))
const pageBack = () => {
  router.push("/user_center")
}
   // 刷新
const onRefresh = () => {
  setTimeout(() => {
    Toast('刷新成功');
    isLoading.value = false;
    refresh()
  }, 1000);
}
onMounted(() => {
    getloadComment()
})
const  getloadComment=()=> {
        OperationService.getUserCommentList(getUserId.value)
        .then((res) => {
            commentList.value = res
        })
    }
const   user_operation_detail=(val:any, operation_source_id:any)=> {
    // 科普知识
    if (val == 0) {
        KnowledgeService.get_knowledge(operation_source_id)
        .then((res) => {
        if (res[0].knowledge_state == 0) {
            sessionStorage.setItem('source_id', operation_source_id);
            router.push("/knowledge_details")
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
                ActivityService.get_activity(operation_source_id)
                .then((res) => {
                if (res.activity_state == 0) {
                    sessionStorage.setItem('source_id', operation_source_id);
                    router.push("/activity_details")
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
        VideoService.get_video(operation_source_id)
        .then((res) => {
                if (res.video_state == 0) {
                    sessionStorage.setItem('source_id', operation_source_id);
                    router.push("/classroom_details")
                } else {
                    Dialog.alert({
                        title: '查看失败',
                        message: '该资源已被删除，无法查看详情!',
                    })
                }
            });
    }

}
const   deleteComment=(operation_id:any)=> {
    Dialog.alert({
        title: '删除个人评论',
        message: '确认删除个人评论？',
    }).then(() => {
        CommentService.deleteComment(operation_id).then((res) => {
            if (res.msg == '删除评论成功') {
                Dialog.alert({
                    message: '删除评论成功',
                }).then(() => {
                    getloadComment()
                });
            } else {
                Dialog.alert({
                    message: '删除评论失败',
                })
            }
        })
})
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