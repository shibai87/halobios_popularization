<template>
  <div id="classroom">
    <van-pull-refresh v-model="isLoading" @refresh="onRefresh">
    <!-- 分类知识详情 -->
    <div v-if="classroomList.length">
      <ul v-for="(item,index) in classroomList" :key="index" class="classroom_list">
        <li class="classroom_item">
            <div class="classroom_title">
              <span>{{item.video_title}}</span>
            </div> 
            <video controls :src="Video(item.video_file_name)" class="classroom_video"></video>
            <div class="classroom_operation"> 
              <van-button type="primary" @click="edit_classroom(item.video_id)">查看</van-button>
            </div>
        </li>
      </ul>
    </div>
    <div v-else  class="classroom_list">
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
    import VideoService from "../../api/VideoService.js"
    const refresh = inject<any>("reload")
      const router = useRouter()
      const classroomList=ref<any>([])
const isLoading=ref<boolean>(false)
  //     //视频路径
 const Video = (video_file_name: any) =>Constant.URL+ "/video/" + video_file_name
 onMounted(() => {
  getLoadclassroomList();
})
  const onRefresh = () => {
  setTimeout(() => {
      Toast('刷新成功');
      isLoading.value = false;
      getLoadclassroomList()
  }, 1000);
}

      // 获取知识列表+查询列表
    const  getLoadclassroomList=()=> {
          VideoService.get_video_list(1).then((res) => {classroomList.value = res})
      }
      // 编辑
    const  edit_classroom=(classroom_id:any)=> {
          sessionStorage.setItem('operation_source_type', "2")
         sessionStorage.setItem('source_id', classroom_id);
        router.push({
          path: '/classroom_details',
          query: {
            classroom_id: classroom_id
          }
        })
        
      }
 
</script>

<style>
  #classroom .van-button {
     display: block;
    width: 250px;
    margin: 5px auto 0;
  }
  #classroom  .van-empty{
    margin-left: 120px;
  }
</style>

<style scoped>
#classroom{
   height: 700px;
}
  ul,
  li {
    list-style: none;
  }
.classroom_list{
 display: inline-flex;
 flex-wrap:wrap;
}
.classroom_item{
 width: 370px;
    border: 1px solid #000;
    padding: 10px;
}
  .classroom_video {
    width: 300px;
    height: 200px;
    margin-left: 40px;
  
  }

  .classroom_title {
    height: 30px;
    font-size: 30px;
    text-align: center;
  }
</style>