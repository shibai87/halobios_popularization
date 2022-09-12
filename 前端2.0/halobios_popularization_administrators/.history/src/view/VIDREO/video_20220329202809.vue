<template>
  <div id="video">
    <!-- 分类知识详情 -->
    <div v-if="videoList.length">
      <ul v-for="(item,index) in videoList" :key="index" class="video_list">
        <li class="acitivity_item">
            <video controls :src="Video(item)" class="video_video"></video>
            <div class="video_title">
              <span>{{item.video_title}}</span>
              <el-badge :value="3" class="item">
                <i class="el-icon-star-off"></i>
              </el-badge>
            </div> 
            <div class="video_operation"> 
              <!-- <el-button @click="comment(item.video_id)">评论列表(待定)</el-button> -->
              <el-button type="primary" @click="edit_video(item.video_id)">编辑</el-button>
              <el-button type="primary" @click="delete_video(item.video_id)">删除</el-button>
            </div>
        </li>
      </ul>
    </div>
    <div v-else>
      <el-empty description="无内容！"></el-empty>
    </div>
  </div>
</template>

<script>
  export default {
    inject: ["reload"],
    name: "video",
    data() {
      return {
        videoList: [],
      }
    },
    methods: {
      // 获取知识列表+查询列表
      getLoadvideoList() {
        this.axios.get("http://localhost:8083/video/get_video_list?video_upload=" + 1)
          .then((res) => {
            // console.log(res.data,res.data.length)
            this.videoList = res.data
            // console.log(this.videoList.length)
          })

      },
      // 编辑
      edit_video(video_id) {
        this.$store.commit("judgevideo", true)
         localStorage.setItem('video_id', video_id);
        this.$store.commit("getvideovideo", video_id)
        this.$router.push("/editvideo_detail");
      },
      delete_video(video_id){
        this.axios.get("http://localhost:8083/video/delete_video?video_id="+video_id)
          .then((res) => {
            console.log(res.data)
            if (res.data.msg === "删除成功") {
              this.$message({
                message: "删除成功!",
                type: "success",
              });
            } else {
              this.$message({
                message: "删除失败!",
                type: "danger",
              });
            }
            this.reload()
          });
      },

      //视频路径
      Video(row) {
        var url = "http://localhost:8083/video/" + row.video_file_name
        console.log(url)
        return url
      },
    },
    computed: {
    },

    mounted() {
      this.getLoadvideoList();
    }
  }
</script>
<style>
  /*  */
  #video .el-alert {
    margin-bottom: 10px;
  }



  #video .el-button {
    display: block;
    width: 150px;
    margin: 10px auto;


  }

  #video .el-badge {
    font-size: 20px;
    margin: 0 10px 20px;
  }
</style>
<style scoped>
  ul,
  li {
    list-style: none;
  }
.video_list{
 display: inline-flex;
 flex-wrap:wrap;
}
.acitivity_item{
margin: 10px;
  width: 250px;
  border: 1px solid #000;
}
  .video_video {
    width: 200px;
    height: 200px;
    margin-left: 20px;
  }

  .video_title {
    height: 50px;
    font-size: 25px;
    text-align: center;
  }

  .video_operation {
    height: 100px;
  }
</style>