<template>
  <div id="editclass">
    <!-- 分类知识详情 -->
    <div v-if="classList.length">
      <ul v-for="(item,index) in classList" :key="index" class="class_list">
        <li class="acitivity_item">
            <video controls :src="Video(item)" class="class_video"></video>
            <div class="class_title">
               <span v-if="!item.video_title">标题未编辑</span>
              <span v-else>{{item.video_title}}</span>
              <el-badge :value="3" class="item">
                <i class="el-icon-star-off"></i>
              </el-badge>
            </div> 
            <div class="class_operation"> 
              <el-button type="primary" @click="edit_class(item.video_id)">编辑</el-button>
              <el-button type="primary" @click="delete_class(item.video_id)">删除</el-button>
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
import VideoService from "../../api/VideoService.js"
import Constant from "../../assets/constant.js"
  export default {
    inject: ["reload"],
    name: "class",
    data() {
      return {
        classList: [],
      }
    },
    methods: {
      // 获取知识列表+查询列表
      getLoadclassList() {
          VideoService.get_video_list(0).then((res) => {this.classList = res})
      },
      // 编辑
      edit_class(class_id) {
        sessionStorage.setItem('judgeclass', false)
        sessionStorage.setItem('class_id', class_id)
        this.$router.push("/editclass_detail");
      },
      delete_class(class_id){
          VideoService.delete_video(class_id).then((res) => {
            if (res.msg === "删除成功") {
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
        var url = "http://192.168.1.105:8083/video/" + row.video_file_name
        console.log(url)
        return url
      },
    },
    computed: {
    },

    mounted() {
      this.getLoadclassList();
    }
  }
</script>
<style>
  /*  */
  #editclass .el-alert {
    margin-bottom: 10px;
  }



  #editclass .el-button {
    display: block;
    width: 150px;
    margin: 10px auto;


  }

  #editclass .el-badge {
    font-size: 20px;
    margin: 0 10px 20px;
  }
</style>
<style scoped>
  ul,
  li {
    list-style: none;
  }
.class_list{
 display: inline-flex;
 flex-wrap:wrap;
}
.acitivity_item{
margin: 10px;
  width: 250px;
  border: 1px solid #000;
}
  .class_video {
    width: 200px;
    height: 200px;
    margin-left: 20px;
  }

  .class_title {
    height: 50px;
    font-size: 25px;
    text-align: center;
  }

  .class_operation {
    height: 100px;
  }
</style>