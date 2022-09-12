<template>
    <div id="user_comment">
        <van-nav-bar title="个人信息" left-arrow fixed @click-left="pageBack" />
        <van-pull-refresh v-model="isLoading" @refresh="onRefresh">
            <div class="comment_other" v-if="commentList.length">
                <div v-for="(item,index) in commentList" :key="index">
                    <van-row>
                        <van-col span="13" offset="2" class="comment_other_content">
                           留言：{{item.comment_content}}
                        </van-col>
                        <van-col span="5" offset="2" class="comment_other_operation">
                            <van-button size="small" type="primary">查看详情</van-button>
                        </van-col>
                    </van-row>
                </div>
            </div>
            <div v-else>
                <van-empty image="error" description="未发现有人评论" />
            </div>
        </van-pull-refresh>

    </div>
</template>

<script>
    import {
        Toast
    } from 'vant';
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
                this.axios.get("http://192.168.1.103:8083/operation/getCommentList")
                    .then((res) => {
                        console.log(res.data)
                        this.commentList = res.data
                    })

            },
        },
        mounted() {
            this.getloadComment()
        },
        computed: {

            getJudgeLogin() {
                return JSON.parse(localStorage.getItem('judge_login'));
            },
            getUserId() {
                return this.$store.state.user_id;
            },
        },
    }
</script>
<style>
    #user_comment .van-row {
        border: 1px solid rgb(102, 102, 102);
        border-radius: 6px;
        height: 100px;
        margin: 5px;
        padding: 5px;
    }

    #user_comment .van-button {
        display: inline-block;
        height: 30px;
        width: 100px;
        font-size: 20px;
        vertical-align: middle;
    }
</style>
<style scoped>
    /*  */
    .comment_other{
          margin-top: 100px;
    }
    .comment_other_content {
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