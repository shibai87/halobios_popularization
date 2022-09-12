<template>
    <div id="activity_details">
        <van-nav-bar title="活动详情" fixed left-arrow @click-left="pageBack" />
        <div class="activity_details_main">
            <h1>{{ruleForm.activity_title}}</h1>
            <video controls :src="Video()" width="400" height="250" class="activity_video"></video>
            <div class="activity_details_content">
                <div class="activity_other_title">活动主题简介：</div>
                <div class="activity_other_info">{{ruleForm.activity_info}}</div>

            </div>
            <div class="activity_details_time">
                <div class="activity_other_title">活动时间:</div>
                <div class="activity_other_info"> {{ruleForm.activity_begin_time}}至{{ruleForm.activiy_end_time}}</div>
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
                    <van-button type="primary" @click="addSignUp(ruleForm.activity_id)">参与报名</van-button>
                </div>
            </div>
            <div v-else>
                <van-button type="primary" disabled>参与报名</van-button>
                <van-notice-bar left-icon="info-o" speed=60 text="登录后才能报名" />
            </div>
        </div>
         <Operaion></Operaion>
            <Comment></Comment>
    </div>
</template>

<script>
    import {
        Dialog
    } from 'vant';
    import Operaion from "../../components/operation.vue"
    import Comment from "../../components/comment.vue"
    import ActivityService from "../../api/ActivityService.js"
    import Constant from "../../assets/constant.js"
    export default {
        name: 'activity_details',
        components: {
            Operaion,
            Comment
        },
        data() {
            return {
                activity_time: [],
                // 判断是否有视频上传
                activity_video_name: "",
                ruleForm: {
                    activity_title: '',
                    activity_info: '',
                    activity_file_name: "",
                    activity_form: "",
                    activity_place: "",
                    activity_begin_time: "",
                    activiy_end_time: ""
                },
                judge_sign_up: "",
            }
        },
        methods: {
            pageBack() {
                this.$router.push("/")
            },
            // 获取需编辑的知识详情
            getLoadactivity() {
                    ActivityService.get_activity(this.getActivityId).then((res) => {
                        this.ruleForm = res
                        if (this.ruleForm.activity_form == 0 || this.ruleForm.activity_form == 1) {
                            this.ruleForm.activity_form = this.ruleForm.activity_form == 0 ? "线上参与" : "线下参与";
                        }
                    });
                if (this.getJudgeLogin) {
                    ActivityService.get_SignUp(this.getUserId,this.getActivityId).then((res) => {this.judge_sign_up = res.data})
                }
            },
            Video() {
                var url = Constant.URL+"/activity/" + this.ruleForm.activity_file_name
                console.log(url)
                return url
            },
            addSignUp(activity_id) {
                    ActivityService.add_SignUp(this.getUserId,activity_id).then((res) => {
                        if (res.msg == '添加成功') {
                            Dialog.alert({
                                message: '报名成功',
                            }).then(() => {
                                this.getLoadactivity()
                            });
                        } else {
                            Dialog.alert({
                                message: '报名失败!',
                            })
                        }
                    })
            },
            updataSignUp() {
                    ActivityService.updata_SignUp(this.judge_sign_up).then((res) => {
                        console.log(res.data)
                        if (res.data.msg == '修改成功') {
                            Dialog.alert({
                                message: '取消报名成功!',
                            }).then(() => {
                                this.getLoadactivity()
                            });
                        } else {
                            Dialog.alert({
                                message: '取消报名失败!',
                            })
                        }
                    })
            },
        },
        computed: {
            getJudgeLogin() {
                return JSON.parse(sessionStorage.getItem('judge_login'));
            },
            getUserId() {
                return JSON.parse(sessionStorage.getItem('user_id'))
            },
            getActivityId() {
                return JSON.parse(sessionStorage.getItem('source_id'));
            },
        },
        mounted() {
            this.getLoadactivity()
        }
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
        #activity_details .van-notice-bar{
            padding-left: 20px;
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