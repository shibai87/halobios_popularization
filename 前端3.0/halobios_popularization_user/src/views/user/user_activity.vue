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
              <video controls :src="Video(item.activity_file_name)" class="activity_video"></video>
              <div class="activity_operation">
                <van-button type="primary" @click="edit_activity(item.activity_id)">查看详情</van-button>
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

<script lang="ts" setup>
     import { ref, reactive } from "vue"
import { onMounted } from "vue";
import { useStore } from "vuex"
import { useRouter } from 'vue-router'
import { inject } from "vue"
import { Toast } from 'vant';
import { Dialog } from 'vant';
  import ActivityService from "../../api/ActivityService.js"
  import Constant from "../../assets/constant"
  const store = useStore()
const router = useRouter()
const refresh = inject<any>("reload")
  const isLoading=ref(false)
   const judge_sign_up=ref(false)
   const activityList=ref([
    {
      activity_title:"",
      activity_file_name:"",
      activity_id:""
    }
   ])
   const getJudgeLogin= ref<any>(JSON.parse(sessionStorage.getItem('judge_login') || ""))
const getUserId= ref<any>(JSON.parse(sessionStorage.getItem('user_id') || ""))
   const pageBack = () => {
  router.push("/user_center")
}
           // 刷新
const onRefresh = () => {
  setTimeout(() => {
    Toast('刷新成功');
    isLoading.value = false;
    refresh()
  }, 1000);
}
onMounted(() => {
  getLoadactivityList()
})

      // 获取知识列表+查询列表
    const  getLoadactivityList=()=> {
        ActivityService.get_activity_listByUserId(getUserId.value)
        .then((res) => {
          activityList.value = res
        })
      }
      // 查看详情
    const  edit_activity=(activity_id:any)=> {
        sessionStorage.setItem('operation_source_type', "2")
        sessionStorage.setItem('activity_id', activity_id);
        store.commit("getActivity", activity_id)
        router.push("/activity_details");
      }
      //视频路径
    const Video = (activity_file_name:any) => Constant.URL + "/activity/" + activity_file_name
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