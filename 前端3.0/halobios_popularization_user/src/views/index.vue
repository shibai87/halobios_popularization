<template>
  <div id="index">
    <!--index_head-->
    <header>
       <van-nav-bar title="海洋生物科普" fixed>
        <template #right>
          <div v-if="!judge_login">
            <router-link to="/login">
              <div class="login">登录</div>
            </router-link>
          </div>
          <div v-else>
            <router-link to="/user_center">
              <van-icon class="icon_user" name="https://b.yzcdn.cn/vant/icon-demo-1126.png" />
            </router-link>
          </div>
        </template>
      </van-nav-bar>
      <!-- 搜索 -->
      <Search></Search>
    </header>

    <!-- index_main-->
     <van-tabs type="card">
      <van-tab title="首页">
        <Home></Home>
      </van-tab>
      <van-tab title="知识分类">
        <Knowledge></Knowledge>
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

<script lang="ts" setup>
  import {ref,reactive} from "vue"
  import Search from "../components/search.vue"
  import Home from "../views/home.vue"
  import Knowledge from "../views/knowledge/knowledge.vue"
  import Activity from "../views/activity/activity.vue"
  import Classroom from "../views/class/classroom.vue"
  const judge_login=ref(JSON.parse(sessionStorage.getItem('judge_login')||"0"))
</script>

<style >
    /* index_head */
    #index .van-nav-bar__content {
      height: 70px;
    }

    #index .van-nav-bar__title {
      height: 30px;
      line-height: 30px;
      font-size: 25px;
      font-weight: 700;
    }

    #index .van-nav-bar__right {
      right: 5px;
      font-size: 20px;
    }

    #index .van-icon {
      color: #000;
      font-size: 30px;
      margin-right: 5px;
    }

    /* index_main */
    #index .van-tabs__wrap {
      width: 100%;
      top: 130px;
      position: fixed;
      z-index: -4;
    }

    #index .van-tab {
      font-size: 20px;
      height: 30px;

    }

    #index .van-tabs__content {
      position: fixed;
       z-index: -10;
      top: 165px;
      bottom: 6px;
      overflow: auto;
     
    }

    #index .van-tabs__content::-webkit-scrollbar {
      display: none !important;
      width: 0px;
      height: 0px;
    }
</style>