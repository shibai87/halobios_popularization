<template>
    <div id="user_other_operation">
        <van-nav-bar :title="this.getUserType" left-arrow fixed @click-left="pageBack" />
        <van-pull-refresh v-model="isLoading" @refresh="onRefresh">
            <van-tabs v-model="active" type="card" @click="getloadOperation()">
                <van-tab title="科普知识区"></van-tab>
                <van-tab title="科普活动区"></van-tab>
                <van-tab title="科普小课堂区"></van-tab>
            </van-tabs>
            <div class="comment_other" v-if="List.length">
                <div v-for="(item,index) in List" :key="index">
                    <van-row class="user_other_operation_main">
                        <van-col span="13" offset="2" class="comment_other_content">
                            <div v-show="active==0"> 标题：{{item.knowledge_title}}</div>
                            <div v-show="active==1"> 标题：{{item.activity_title}}</div>
                            <div v-show="active==2"> 标题：{{item.video_title}}</div>
                        </van-col>
                        <van-col span="5" offset="2" class="comment_other_operation">
                            <div  v-show="active==0">
                                <van-button size="small" type="primary"  @click="user_operation_detail(item.knowledge_id)">查看详情</van-button>
                            </div>
                              <div  v-show="active==1">
                                <van-button size="small" type="primary"  @click="user_operation_detail(item.activity_id)">查看详情</van-button>
                            </div>
                              <div  v-show="active==2">
                                <van-button size="small" type="primary"  @click="user_operation_detail(item.video_id)">查看详情</van-button>
                            </div>
                           
                        </van-col>
                    </van-row>
                </div>
            </div>
            <div v-else>
                <van-empty image="error" description="无数据" />
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
    import UserService from "../../api/UserService.js"
    export default {
        name: '',
        inject: ["reload"],
        data() {
            return {
                // 刷新
                isLoading: false,
                active: 0,
                List: [],
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
            getloadOperation() {
                let operation_type = this.getUserType == "我的点赞" ? 0 : 1;
                // this.axios.get(
                //         "http://192.168.1.105:8083/operation/getUserOperationList?operation_user_id=" + this.getUserId +
                //         "&operation_source_type=" + this.active + "&operation_type=" + operation_type)
                    UserService.getloadOperation(this.getUserId,this.active,operation_type).then((res) => {
                        console.log(res.data.data)
                        this.List = res.data.data
                        console.log(this.List.length)
                    })
            },
             user_operation_detail(operation_source_id) {
                  console.log( operation_source_id)
                if (this.active== 0) {
                    // 科普知识
                    this.axios.get("http://192.168.1.105:8083/knowledge/get_knowledge?knowledge_id=" +operation_source_id)
                        .then((res) => {
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
                if (this.active== 1) {
                    this.axios.get("http://192.168.1.105:8083/activity/get_activity?activity_id=" + operation_source_id)
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
                if (this.active== 2) {
                    // 科普小课堂
                    this.axios.get("http://192.168.1.105:8083/video/get_video?video_id=" + operation_source_id)
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
            },

        mounted() {
            this.getloadOperation()
        },
        computed: {
            getJudgeLogin() {
                return JSON.parse(sessionStorage.getItem('judge_login'));
            },
            getUserId() {
                return JSON.parse(sessionStorage.getItem('user_id'))
            },
            getUserType() {
                return sessionStorage.getItem('user_type');
            }
        },
    }
</script>

<style lang="less">
    #user_other_operation {
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
        .van-tabs {
            top: 80px;
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
    }
</style>

<style scoped>
    .user_other_operation_head .van-row {
        margin-top: 100px;
    }

    .comment_other {
        margin-top: 90px;
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
        height: 80px;
        line-height: 80px;
    }
</style>