<template>
  <div id="index">
    <!--index_head-->
   
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
            <Activity></Activity>
          </van-tab>
          <van-tab title="小课堂">
            <Classroom></Classroom>
          </van-tab>
        </van-tabs>
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
  /* index_head */
  /* 头部的高 */
 #index .van-nav-bar__content{
    height: 70px;
  }
  #index .van-nav-bar__title{
    height: 30px;
    line-height: 30px;
    font-size: 25px;
    
  }
  #index .van-nav-bar__right{
    right: 5px;
    font-size:20px ;
  }
  #index .van-icon {
    color: #000;
    font-size:30px;
    margin-right: 5px;
  }
/* index_main */

 #index .van-tabs__wrap{
  width: 100%;
   top: 70px;
  position: fixed;
  

}


.van-tabs__content{
top: 90px;
  position: fixed;
  height: 100px;
  right: 1px;
  overflow-y: scroll;
 }

/* #index .van-tab__pane{
  
  /* right: -17px;
  overflow-y: scroll;
}  */

</style>
<style scoped lang="less">
  //  index_head
 

 
  // index_main


</style>