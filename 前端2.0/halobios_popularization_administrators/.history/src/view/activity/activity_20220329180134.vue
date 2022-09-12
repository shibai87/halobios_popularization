<template>
  <div id="activity">
    <!-- 分类知识详情 -->
    <div v-if="activityList.length">
      <ul v-for="(item,index) in activityList" :key="index" class="activity_list">
        <li class="acitivity_item">
            <video controls :src="Video(item)" class="activity_video"></video>
            <div class="activity_title">
              <span>{{item.activity_title}}</span>
              <el-badge :value="3" class="item">
                <i class="el-icon-star-off"></i>
              </el-badge>
            </div>
            <div class="activity_operation">
              <!-- <el-button @click="comment(item.activity_id)">评论列表(待定)</el-button> -->
              <el-button type="primary" @click="edit_activity(item.activity_id)">编辑</el-button>
              <el-button type="primary" @click="delete_activity(item.activity_id)">删除</el-button>
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
    name: "activity",
    data() {
      return {
        activityList: [],
      }
    },
    methods: {
      // 获取知识列表+查询列表
      getLoadactivityList() {

        this.axios.get("http://localhost:8083/activity/get_activity_list")
          .then((res) => {
            console.log(res.data)
            this.activityList = res.data
          })

      },
      // 编辑
      edit_activity(activity_id) {
        this.$store.commit("judgeactivity", true)
        this.$store.commit("getactivity", activity_id)
        this.$router.push("/editactivity_detail");
      },
      delete_activity(activity_id) {
        this.axios.get("http://localhost:8083/activity/delete_activity?activity_id=" + activity_id)
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
        var url = "http://localhost:8083/activity/" + row.activity_file_name
        console.log(url)
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
  #activity .el-alert {
    margin-bottom: 10px;
  }



  #activity .el-button {
    display: block;
    width: 150px;
    margin: 10px auto;


  }

  #activity .el-badge {
    font-size: 20px;
    margin: 0 10px 20px;
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
    height: 200px;
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