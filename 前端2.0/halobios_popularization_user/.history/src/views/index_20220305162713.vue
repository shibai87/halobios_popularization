<template>
  <div id="index">
    <!--index_head-->
    <van-nav-bar title="海洋生物科普" fixed>
      <template #right>
        <!-- 搜索 -->
        <van-icon name="search" @click="search" />
        <!-- 已登录用户，可进入用户个人中心 -->
        <div v-if="judge_login">
          <van-icon class="icon_user" name="https://b.yzcdn.cn/vant/icon-demo-1126.png" @click="user" />
        </div>
        <!-- 未登录用户，可进入登录页面 -->
        <div v-else class="login" @click="login">
          登录
        </div>
      </template>
    </van-nav-bar>

    <!-- index_main-->
    <!-- 采用标签页 -->
    <van-tabs type="card">
      <van-tab title="首页">
        <Home></Home>
      </van-tab>
      <van-tab title="分类">
        <Sort></Sort>
      </van-tab>
      <van-tab title="活动">
        <!-- <Activity></Activity> -->
      </van-tab>
      <van-tab title="小课堂">
        <!-- <Classroom></Classroom> -->
      </van-tab>
    </van-tabs>
  
  </div>
</template>

<script>
  import Home from "../views/home.vue"
  import Sort from "../views/sort.vue"
  // import Activity from "../views/activity.vue"
  // import Classroom from "../views/classroom.vue"

  export default {
    name: 'index',
    components: {
      Home,
      Sort,
      // Activity,
      // Classroom
    },
    data() {
      return {

      }
    },
    methods: {

      search() {
        this.$router.push("/search");
      },
      login() {
        this.$router.push("/login")
      },
      user() {
        this.$router.push("/user")
      },


    },
    computed: {
      judge_login() {
        return this.$store.state.judge_login
      },
    },
    mounted() {},

  }
</script>
<style>
@import url("../assets/css/head.css");
  /* index_head */
  /* 头部的高 */
 

  /* index_main */

  #index .van-tabs__wrap {
    width: 100%;
    top: 70px;
    position: fixed;
  }

  /* 需要改 */
  #index .van-tabs__content {
    top: 110px;
    position: fixed;
    /* 这个定位影响了轮播图，待改 */
    height: 100%;
    overflow: auto;
  }

  #index .van-tabs__content::-webkit-scrollbar {
    display: none !important;
    width: 0px;
    height: 0px;
  }
</style>
<style scoped lang="less">
</style>