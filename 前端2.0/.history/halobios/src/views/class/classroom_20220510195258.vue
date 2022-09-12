<template>
  <div id="classroom">
    <van-pull-refresh v-model="isLoading" @refresh="onRefresh">
    <!-- 分类知识详情 -->
    <div v-if="classroomList.length">
      <ul v-for="(item,index) in classroomList" :key="index" class="classroom_list">
        <li class="acitivity_item">
            <div class="classroom_title">
              <span>{{item.video_title}}</span>
            </div> 
            <video controls :src="Video(item)" class="classroom_video"></video>
            <div class="classroom_operation"> 
              <van-button type="primary" @click="edit_classroom(item.video_id)">查看</van-button>
            </div>
        </li>
      </ul>
    </div>
    <div v-else>
      <van-empty description="无内容！"></van-empty>
    </div>
    </van-pull-refresh>
  </div>
</template>

<script>
    import Constant from "../../assets/constant.js"
    import VideoService from "../../api/VideoService.js"
  export default {
    inject: ["reload"],
    name: "classroom",
    data() {
      return {
        classroomList: [],
        isLoading: false,
      }
    },
    methods: {
      // 获取知识列表+查询列表
      getLoadclassroomList() {
          VideoService.get_video_list(1).then((res) => {this.classroomList = res})
      },
      // 编辑
      edit_classroom(classroom_id) {
          sessionStorage.setItem('operation_source_type', 2)
         sessionStorage.setItem('source_id', classroom_id);
        this.$router.push({
          path: '/classroom_details',
          query: {
            classroom_id: classroom_id
          }
        })
        
      },
      //视频路径
      Video(row) {
        var url =  Constant.URL+"/video/" + row.video_file_name
        return url
      },
    },
    mounted() {
      this.getLoadclassroomList();
    }
  }
</script>

<style>
  #classroom .van-button {
     display: block;
    width: 250px;
    margin: 5px auto 0;
  }
</style>

<style scoped>
  ul,
  li {
    list-style: none;
  }
.classroom_list{
 display: inline-flex;
 flex-wrap:wrap;
}
.acitivity_item{
 width: 360px;
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