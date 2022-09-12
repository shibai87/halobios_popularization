<template>
  <div id="activity">
    <van-pull-refresh v-model="isLoading" @refresh="onRefresh">
    <!-- 分类知识详情 -->
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
    <div v-else  class="activity_list">
      <van-empty description="无内容！"></van-empty>
    </div>
    </van-pull-refresh>
  </div>
</template>

<script>
 import {
    Toast
  } from 'vant';
import Constant from "../../assets/constant.js"
import ActivityService from "../../api/ActivityService.js"
  export default {
    inject: ["reload"],
    name: "activity",
    data() {
      return {
        judge_sign_up: false,
        activityList: [],
        isLoading: false,
      }
    },
    methods: {
        onRefresh() {
        setTimeout(() => {
          Toast('刷新成功');
          this.isLoading = false;
          this.getLoadactivityList()
        }, 1000);
      },
      // 获取知识列表+查询列表
      getLoadactivityList() {
          ActivityService.get_activity_list(1).then((res) => {
            console.log(res)
            this.activityList = res})
      },
      // 查看详情
      edit_activity(activity_id) {
        sessionStorage.setItem('operation_source_type', 1)
        sessionStorage.setItem('source_id', activity_id);
        this.$router.push("/activity_details");
      },
      //视频路径
      Video(row) {
        var url = Constant.URL+"/activity/" + row.activity_file_name
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

<style>
  /*  */
  #activity .van-button {
    display: block;
    width: 250px;
    margin: 5px auto 0;
  }
  #activity .van-empty{
    margin-left: 120px;
  }
</style>

<style scoped>
#activity{
  height: 700px;
}
  ul,
  li {
    list-style: none;
  }

  .activity_list {
    display: inline-flex;
    flex-wrap: wrap;
  }

  .acitivity_item {
    width: 370px;
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