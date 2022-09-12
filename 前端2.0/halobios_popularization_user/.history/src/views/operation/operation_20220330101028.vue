<template>
    <div>
        <van-nav-bar title="个人信息" left-arrow fixed @click-left="pageBack" />
            <van-pull-refresh v-model="isLoading" @refresh="onRefresh">
                  <div class="comment_other" v-if="commentList.length">
            <div v-for="(item,index) in commentList" :key="index">
                <van-row>
                    <van-col span="15" offset="2" class="comment_other_content">
                        <div class="comment_other_title">
                            <div v-if="item.user_name">用户名：{{item.user_name}}</div>
                            <div v-else>该用户未取用户名</div>
                        </div>

                        <div class="comment_other_info">留言：{{item.comment_content}}</div>
                    </van-col>
                    <van-col span="3" offset="2" class="comment_other_operation">
                        <van-button size="small" type="primary" @click="report">举报</van-button>
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
            // get_user_id(){
            //         return 
            // },
            getUserId() {
                // return localStorage.getItem('user_id');
                return this.$store.state.user_id;
            },
        },
    }
</script>

<style scoped>

</style>