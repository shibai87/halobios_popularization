<template>
  <div id="user_activity">
    <van-nav-bar title="我参与的活动" left-arrow fixed @click-left="pageBack" />
    <van-pull-refresh v-model="isLoading" @refresh="onRefresh">
      <div class="user_activity_content">
        <div v-if="activityList.length">
          <ul v-for="(item,index) in activityList" :key="index" class="activity_list">
            <li class="acitivity_item">
              <div class="activity_title">
                {{item.activity_title}}
              </div>
              <video controls :src="Video(item)" class="activity_video"></video>
              <div class="activity_operation">
                <van-button type="info" @click="edit_activity(item.activity_id)">查看详情</van-button>
              </div>
            </li>
          </ul>
        </div>
        <div v-else>
          <van-empty description="无内容！"></van-empty>
        </div>
      </div>
    </van-pull-refresh>
  </div>
</template>

<script>
  import {
    Toast
  } from 'vant';
  import request from '../../api/axios' 
  import UserService from "../../api/UserService"
  export default {
    inject: ["reload"],
    name: "activity",
    data() {
      return {
        // 刷新
        isLoading: false,
        judge_sign_up: false,
        activityList: [],
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
      // 获取知识列表+查询列表
      getLoadactivityList() {
        UserService.getUserActivityList(this.getUserId).then((res) => {
            this.activityList = res
          })
      },
      // 查看详情
      edit_activity(activity_id) {
        sessionStorage.setItem('operation_source_type', 2)
        sessionStorage.setItem('activity_id', activity_id);
        this.$store.commit("getActivity", activity_id)
        this.$router.push("/activity_details");
      },
      //视频路径
      Video(row) {
        console.log(request.service.baseURL)
        var url = "http://192.168.197.65:8083/activity/" + row.activity_file_name
        console.log(url)
        return url
      },
    },
    computed: {
      getJudgeLogin() {
        return JSON.parse(sessionStorage.getItem('judge_login'));
      },
      getUserId() {
        return JSON.parse(sessionStorage.getItem('user_id'))
      },
    },

    mounted() {
      this.getLoadactivityList();
    }
  }
</script>

<style lang="less">
  #user_activity {

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
    .van-button {
      display: block;
      width: 250px;
      margin: 5px auto 0;
    }
  }
</style>
<style scoped>
  ul,
  li {
    list-style: none;
  }

  .user_activity_content {
    margin-top: 80px;
    height: 770px;
  }

  .activity_list {
    display: inline-flex;
    flex-wrap: wrap;
  }

  .acitivity_item {
    width: 360px;
    border: 1px solid #000;
    padding: 10px;
  }

  .activity_video {
    width: 300px;
    height: 200px;
    margin-left: 40px;

  }

  .activity_title {
    height: 30px;
    font-size: 30px;
    text-align: center;
  }
</style>