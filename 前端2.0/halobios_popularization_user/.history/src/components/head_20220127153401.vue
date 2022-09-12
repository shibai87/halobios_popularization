<template>
    <div id="head">
        <van-nav-bar :title="get_title" fixed @click-left="pageBack">
            <template #left>
                <div v-if="judge_no_home">
                    <van-icon name="arrow-left" />
                </div>
            </template>
            <template #right>
                <van-icon name="search" @click="search" />
                <div v-if="judge_login">
                    <van-icon name="https://b.yzcdn.cn/vant/icon-demo-1126.png" @click="user" class="icon_user" />
                </div>
                <div v-else @click="login" class="login">
                    登录
                </div>
            </template>
        </van-nav-bar>
    </div>
</template>

<script>
    export default {
        name: 'head',
        data() {
            return {

            }
        },
        computed: {
            get_title() {
                return this.$store.state.title
            },
            judge_login() {
                return this.$store.state.judge_login
            },
            judge_no_home() {
                return this.$store.state.judge_no_home
            }

        },
        methods: {
            pageBack() {
                window.history.back()
            },
            search() {
                this.$router.push("/search");
            },
            login() {
                this.$router.push("/login")
            },
            user() {
                this.$router.push("/user")
            }
        },
        mounted() {

        },
    }
</script>
<style>
    #head .van-icon {
        color: #000;
        font-size: 25px;
    }
</style>
<style scoped>
    .icon_user,
    .user {
        padding: 0 5px;
    }
</style>