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
          <video controls :src="Video(item.activity_file_name)" class="activity_video"></video>
          <div class="activity_operation">
                <van-button type="primary" @click="edit_activity(item.activity_id)">查看详情</van-button>
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

<script lang="ts" setup>
  import { ref, reactive } from "vue"
import { onMounted } from "vue";
import { useStore } from "vuex"
import { useRouter } from 'vue-router'
import { inject } from "vue"

import { Toast } from 'vant';
import { Dialog } from 'vant';

import Constant from "../../assets/constant.js"
import ActivityService from "../../api/ActivityService.js"
const router = useRouter()
const refresh = inject<any>("reload")
const judge_sign_up=ref<boolean>(false)
const activityList=ref<any>([])
const isLoading=ref<boolean>(false)

const getJudgeLogin=ref(JSON.parse(sessionStorage.getItem('judge_login')||""))
const getUserId=ref(JSON.parse(sessionStorage.getItem('user_id')||""))
const onRefresh = () => {
  setTimeout(() => {
      Toast('刷新成功');
      isLoading.value = false;
      getLoadactivityList()
  }, 1000);
}
 //     //视频路径
 const Video = (activity_file_name: any) => Constant.URL + "/activity/" + activity_file_name
 onMounted(() => {
  getLoadactivityList()
})
// 获取知识列表+查询列表
const getLoadactivityList=()=> {
    ActivityService.get_activity_list(1)
    .then((res) => {
      activityList.value = res
    })
}
// 查看详情
const  edit_activity=(activity_id:any)=> {
  sessionStorage.setItem('operation_source_type', "1")
  sessionStorage.setItem('source_id', activity_id);
  router.push("/activity_details");
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