<template>
  <div id="activity">
    <!-- 分类知识详情 -->
    <div v-if="activityList.length">
      <ul v-for="(item,index) in activityList" :key="index">
        <li>
          <div class="acitivity_item">
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
      activity() {
        this.axios.get("http://localhost:8083/activity/get_activity_list?activity_upload=" + 1 +
            "&options_id=" + this
            .getSortListLastId)
          .then((res) => {
            // console.log(res.data)
            this.activityList = res.data
          })
      },
      clear() {
        this.$store.commit("getSortIdListLast", "")
        this.getLoadactivityList()
        this.reload();
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

      //图片路径
      Video(row) {
        var url = "http://localhost:8083/activity/" + row.activity_file_name
        console.log(url)
        return url
      },


    },
    computed: {
      getSortListLastId() {
        return this.$store.state.sort_id_list_last;
      },
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

  /*详情  */
  #activity_list .el-row {
    border: 1px solid rgb(102, 102, 102);
    border-radius: 6px;
    margin: 20px auto;
    width: 1000px;

  }

  #activity_list .el-col {
    padding: 10px;

  }

  #activity_list .el-button {
    display: block;
    width: 150px;
    margin: 10px auto;


  }

  #activity_list .el-badge {
    font-size: 35px;
    margin: 0 10px 20px;
  }
</style>
<style scoped>
  ul,
  li {
    list-style: none;
  }

  #activity_list {
    margin-top: 50px;
  }

  .activity_video {
    width: 200px;
    height: 200px;
  }

  .activity_title {
    height: 50px;
    font-size: 30px;
  }

  .activity_operation {
    height: 100px;
  }
</style>