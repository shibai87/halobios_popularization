// index页问题
// 1、搜索功能
<template>
  <div id="index">
    
    <!--index_head-->
    <van-nav-bar title="海洋生物科普" fixed>
      <template #right>
        <div v-if="!judge_login">
          <router-link to="/login">
            <div class="login">登录</div>
          </router-link>
        </div>
        <div v-else>
          <router-link to="/user">
            <van-icon class="icon_user" name="https://b.yzcdn.cn/vant/icon-demo-1126.png" />
          </router-link>
        </div>
      </template>
    </van-nav-bar>

    <!-- 搜索 -->
    <van-search v-model="value" shape="round" background="#fff" placeholder="请输入搜索关键词" />
    <!-- index_main-->
    <!-- 采用标签页 -->
    <van-tabs type="card">
       <van-pull-refresh v-model="isLoading" @refresh="onRefresh">
      <!-- <van-tab title="首页">
        <Home></Home>
      </van-tab> -->
      <van-tab title="分类">
        <Sort></Sort>
      </van-tab>
      <van-tab title="活动">
        <Activity></Activity>
      </van-tab>
      <van-tab title="小课堂">
        <Classroom></Classroom>
      </van-tab>
        </van-pull-refresh>
    </van-tabs>


  </div>
</template>

<script>
 import {
    Toast
  } from 'vant';
  // import Home from "../views/home.vue"
  import Sort from "../views/knowledge/knowledge.vue"
  import Activity from "../views/activity.vue"
  import Classroom from "../views/classroom.vue"
  export default {
    name: 'index',
     inject: ["reload"],
    components: {
      // Home,
      Sort,
      Activity,
      Classroom
    },
    data() {
      return {
           // 刷新
        isLoading: false,
        value: ""
      }
    },
    methods: {
  // 刷新
      onRefresh() {
        setTimeout(() => {
          Toast('刷新成功');
          this.isLoading = false;
         this.reload()
        }, 1000);
      },
    },
    computed: {
      judge_login() {
        return JSON.parse(localStorage.getItem('judge_login'));
      },
    },
  }
</script>
<style>
  /* index_head */
  /* 头部的高 */
  #index .van-nav-bar__content {
    height: 70px;
  }

  #index .van-nav-bar__title {
    height: 30px;
    line-height: 30px;
    font-size: 25px;

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

  /* van-search */
  #index .van-search {
    top: 70px;
    width: 100%;
    position: fixed;
  }

  /* index_main */

  #index .van-tabs__wrap {
    width: 100%;
    top: 140px;
    position: fixed;
  }

  /* 需要改 */
  #index .van-tabs__content {
    top: 180px;
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