<template>
  <div id="activity">
    <Search></Search>
    <!-- 分类知识详情 -->
    <div v-if="activityList.length">
      <ul v-for="(item,index) in activityList" :key="index" class="activity_list">
        <li class="acitivity_item">
          <div class="activity_title">
            <span>{{item.activity_title}}</span>
            <el-badge :value="3" class="item">
              <i class="el-icon-star-off"></i>
            </el-badge>
          </div>
          <video controls :src="Video(item)" class="activity_video"></video>
          <div class="activity_operation">
            <el-row >
              <el-col :span="11" >
                <el-button type="primary" @click="comment(item.activity_id)">用户操作详情</el-button>
              </el-col>
              <el-col :span="7" >
                <el-button type="primary" @click="edit_activity(item.activity_id)">编辑</el-button>
              </el-col>
              <el-col :span="6" >
                <el-button type="primary" @click="delete_activity(item.activity_id,item.activity_file_name)">删除</el-button>
              </el-col>
            </el-row>
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
  import Search from "../../components/search.vue"
  import ActivityService from "../../api/ActivityService.js"
  export default {
    inject: ["reload"],
    name: "activity",
    components: {
      Search,
    },
    data() {
      return {
        activityList: [],
      }
    },
    methods: {
      // 获取知识列表+查询列表
      getLoadactivityList() {
          ActivityService.get_activity_list(1).then((res) => {this.activityList = res})
      },
      // 编辑
      edit_activity(activity_id) {
        sessionStorage.setItem('judgeactivity', true)
        sessionStorage.setItem('activity_id', activity_id)
        this.$router.push("/editactivity_detail");
      },

      delete_activity(activity_id, activity_file_name) {
          this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          ActivityService.delete_activity(activity_id,activity_file_name).then((res) => {
            if (res.data.msg === "删除成功") {
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

          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
      
      },
      comment(activity_id) {
        sessionStorage.setItem('operation_source_type', 1)
        sessionStorage.setItem('source_id', activity_id)
        this.$router.push("/commentList")
      },
      //视频路径
      Video(row) {
        var url = "http://192.168.1.105:8083/activity/" + row.activity_file_name
        console.log(url)
        return url
      },
    },
    computed: {},

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

  #activity .el-badge {
    font-size: 20px;

  }

  #activity .el-icon-star-off:before {
    margin: 10px 10px 0;
    font-size: 30px;
  }
</style>

<style scoped>
  ul,
  li {
    list-style: none;
  }

  .activity_list {
    display: inline-flex;
    flex-wrap: wrap;
  }

  .acitivity_item {
    margin: 10px;
    padding: 10px;
    width: 300px;
    border: 1px solid #000;
  }

  .activity_video {
    width: 250px;
    height: 150px;
    margin-left: 20px;
  }

  .activity_title {
    margin-top: 10px;
    height: 40px;
    line-height: 40px;
    font-size: 25px;
    text-align: center;
  }

  .activity_operation {
    height: 60px;
  }
</style>