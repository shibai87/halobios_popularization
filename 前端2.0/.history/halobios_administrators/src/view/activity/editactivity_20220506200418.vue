<template>
  <div id="editactivity">
    <!-- 分类知识详情 -->
    <div v-if="activityList.length">
      <ul v-for="(item,index) in activityList" :key="index" class="activity_list">
        <li class="acitivity_item">
            <div class="activity_title">
             <span v-if="!item.activity_title">标题未编辑</span>
              <span v-else>{{item.activity_title}}</span>
            </div> 
            <video controls :src="Video(item)" class="activity_video"></video>
          
            <div class="activity_operation"> 
              <!-- <el-button @click="comment(item.activity_id)">评论列表(待定)</el-button> -->
              <el-button type="primary" @click="edit_activity(item.activity_id)">编辑</el-button>
              <el-button type="primary" @click="delete_activity(item.activity_id,item.activity_file_name)">删除</el-button>
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
import ActivityService from "../../api/ActivityService.js"
 import Constant from "../../assets/constant.js"
  export default {
    inject: ["reload"],
    name: "activity",
    data() {
      return {
        activityList: [],
      }
    },
    methods: {
      // 获取知识列表+查询列表
      getLoadactivityList() {
          ActivityService.get_activity_list(0).then((res) => {this.activityList = res})
      },
      // 编辑
      edit_activity(activity_id) {
        sessionStorage.setItem('judgeactivity', false)
         sessionStorage.setItem('activity_id', activity_id)
        this.$router.push("/editactivity_detail");
      },
      delete_activity(activity_id,activity_file_name){
          ActivityService.delete_activity(activity_id,activity_file_name).then((res) => {
            if (res.msg === "删除成功") {
              this.$message({
                message: "删除成功!",
                type: "success",
              });
              this.getLoadactivityList()
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
        var url = Constant.URL+"/activity/" + row.activity_file_name
        return url
      },
    },
    computed: {
    },

    mounted() {
      this.getLoadactivityList();
    }
  }
</script>

<style>
  /*  */
  #editactivity .el-alert {
    margin-bottom: 10px;
  }



  #editactivity .el-button {
    display: block;
    width: 150px;
    margin: 10px auto;


  }
</style>

<style scoped>
  ul,
  li {
    list-style: none;
  }
.activity_list{
 display: inline-flex;
 flex-wrap:wrap;
}
.acitivity_item{
margin: 10px;
  width: 250px;
  border: 1px solid #000;
}
  .activity_video {
    width: 200px;
   
    margin-left: 20px;
  }

  .activity_title {
    height: 50px;
    font-size: 25px;
    text-align: center;
  }

  .activity_operation {
    height: 100px;
  }
</style>