<template>
    <div id="activity_details">
        <van-nav-bar title="活动详情" fixed left-arrow @click-left="onClickLeft" />
        <van-pull-refresh v-model="isLoading" @refresh="onRefresh">
            <div class="activity_details_main">
                <h1>{{ruleForm.activity_title}}</h1>
                <video controls :src="Video()" width="400" height="250" class="activity_video"></video>
                <div class="activity_details_content">
                    <div class="activity_other_title">活动主题简介：</div>
                    <div class="activity_other_info">{{ruleForm.activity_info}}</div>

                </div>
                <div class="activity_details_time">
                    <div class="activity_other_title">活动时间:</div>
                    <div class="activity_other_info"> {{ruleForm.activity_begin_time}}至{{ruleForm.activiy_end_time}}
                    </div>
                </div>
                <div class="activity_details_form">
                    <div class="activity_other_title">活动形式:</div>
                    <div class="activity_other_info">{{ruleForm.activity_form}}</div>
                </div>


                <div v-if="ruleForm.activity_form == `线下参与`" class="activity_details_place">
                    <div class="activity_other_title">活动地点:</div>
                    <div class="activity_other_info">{{ruleForm.activity_place}}</div>
                </div>
                <div span="8" v-if="getJudgeLogin">
                    <div v-if="judge_sign_up">
                        <van-button type="success" @click="updataSignUp()">已报名</van-button>
                    </div>
                    <div v-else>
                        <van-button type="primary" @click="addSignUp()">参与报名</van-button>
                    </div>
                </div>
                <div v-else>
                    <van-button type="primary" disabled>参与报名</van-button>
                    <van-notice-bar left-icon="info-o" speed=60 text="登录后才能报名" />
                </div>
            </div>
            <Operaion></Operaion>
            <Comment></Comment>
        </van-pull-refresh>
    </div>
</template>

<script lang="ts" setup>
import { ref, reactive } from "vue"
import { onMounted } from "vue";
import { useStore } from "vuex"
import { useRouter } from 'vue-router'

import { Toast } from 'vant';
import { Dialog } from 'vant';
import Operaion from "../../components/operation.vue"
import Comment from "../../components/comment.vue"
import ActivityService from "../../api/ActivityService.js"
import Constant from "../../assets/constant.js"
const store = useStore()
const router = useRouter()
const isLoading = ref<boolean>(false)
const activity_time = ref([])
// 判断是否有视频上传
const activity_video_name = ref([])
const ruleForm = ref<any>({
    activity_title: '',
    activity_info: '',
    activity_file_name: "",
    activity_form: "",
    activity_place: "",
    activity_begin_time: "",
    activiy_end_time: ""
})
const judge_sign_up = ref<number>()

const getJudgeLogin = ref(JSON.parse(sessionStorage.getItem('judge_login') || ""))
const getUserId = ref(JSON.parse(sessionStorage.getItem('user_id') || ""))
const getActivityId = ref(JSON.parse(sessionStorage.getItem('source_id') || ""))

onMounted(() => {
    getLoadactivity()
})

const onClickLeft = () => { router.push("/") }
// 刷新
const onRefresh = () => {
    setTimeout(() => {
        Toast('刷新成功');
        isLoading.value = false;
        getLoadactivity()
    }, 1000);
}
// 获取需编辑的知识详情
const getLoadactivity = () => {
    ActivityService.get_activity(getActivityId.value)
        .then((res) => {
            ruleForm.value = res
            if (ruleForm.value.activity_form == 0 || ruleForm.value.activity_form == 1) {
                ruleForm.value.activity_form = ruleForm.value.activity_form == 0 ? "线上参与" : "线下参与";
            }
        });
    if (getJudgeLogin.value) {
        ActivityService.get_SignUp(getUserId.value, getActivityId.value)
            .then((res) => {
                judge_sign_up.value = res.data
                console.log(judge_sign_up.value)
            })
    }
}
const Video = () => Constant.URL + "/activity/" + ruleForm.value.activity_file_name

const addSignUp = () => {
    ActivityService.add_SignUp(getUserId.value, ruleForm.value.activity_id).then((res) => {
        if (res.msg == '添加成功') {
            Dialog.alert({
                message: '报名成功',
            }).then(() => {
                getLoadactivity()
            });
        } else {
            Dialog.alert({
                message: '报名失败!',
            })
        }
    })
}
const updataSignUp = () => {
    ActivityService.updata_SignUp(judge_sign_up.value).then((res) => {
        if (res.msg == '修改成功') {
            Dialog.alert({
                message: '取消报名成功!',
            }).then(() => {
                getLoadactivity()
            });
        } else {
            Dialog.alert({
                message: '取消报名失败!',
            })
        }
    })
}
</script>

<style lang="less">
/* 头部 */
#activity_details .van-nav-bar__title {
    height: 30px;
    line-height: 30px;
    font-size: 25px;
    text-align: center;
}

#activity_details .van-nav-bar__content {
    height: 70px;
}

#activity_details .van-icon {
    color: #000;
    font-size: 35px;
    margin-right: 5px;
}

#activity_details .van-notice-bar {
    padding-left: 120px;
    margin: 10px 0;
}
</style>

<style scoped>
/*  */
.activity_details_main {
    text-align: center;
    margin: 60px auto 0;
}

.activity_details_main h1 {
    margin-top: 80px;
    font-size: 30px;
}

.activity_other_title {
    text-align: left;
    margin-top: 10px;
    font-size: 25px;
}

.activity_other_info {
    text-indent: 2em;
    font-size: 22px;
    margin: 10px;
    text-align: left;
}
</style>