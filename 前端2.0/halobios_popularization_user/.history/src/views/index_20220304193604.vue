<template>
  <div id="index">
    <!--index_head-->
    <div id="index_head">
      <van-nav-bar title="海洋生物科普" fixed>
        <template #right>
          <van-icon name="search" @click="search" />
          <div v-if="judge_login">
            <van-icon class="icon_user" name="https://b.yzcdn.cn/vant/icon-demo-1126.png" @click="user" />
          </div>
          <div v-else class="login" @click="login">
            登录
          </div>
        </template>
      </van-nav-bar>
    </div>

    <!-- index_main-->
    <!-- 采用标签页 -->
    <div id="index_main">
      <!--index_nav-->
      <div id="index_nav">
        <van-tabs type="card">
          <van-tab title="首页">
            <Home></Home>
          </van-tab>
          <van-tab title="分类">
            <Sort></Sort>
          </van-tab>
          <van-tab title="活动">
            <Activity></Activity>
          </van-tab>
          <van-tab title="小课堂">
            <Classroom></Classroom>
          </van-tab>
        </van-tabs>
      </div>
    </div>
  </div>
</template>

<script>
  import Home from "../views/home.vue"
  import Sort from "../views/sort.vue"
  import Activity from "../views/activity.vue"
  import Classroom from "../views/classroom.vue"

  export default {
    name: 'index',
    components: {
      Home,
      Sort,
      Activity,
      Classroom
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
      change_login() {
        this.$store.commit("judgeLogin", false)
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
  #index_head .van-icon {
    color: #000;
    font-size: 25px;
  }
</style>
<style scoped lang="less">
  //  index_head
  #index_head {
    height: 50px;
  }

  .user {
    padding: 0 5px;
  }
  // index_main
#index_main{
  height: 80%;
}

</style>