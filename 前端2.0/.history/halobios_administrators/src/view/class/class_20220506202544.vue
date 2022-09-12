<template>
  <div id="class">
    <Search></Search>
    <!-- 分类知识详情 -->
    <div v-if="classList.length">
      <ul v-for="(item,index) in classList" :key="index" class="class_list">
        <li class="acitivity_item">
          <div class="class_title">
            <span>{{item.video_title}}</span>
            <el-badge :value="3" class="item">
              <i class="el-icon-star-off"></i>
            </el-badge>
          </div>
          <video controls :src="Video(item)" class="class_video"></video>
          <div class="class_operation">
            <el-row>
              <el-col :span="11" :offset="1">
                <el-button type="primary"  @click="comment(item.video_id)">用户操作详情</el-button>
              </el-col>
              <el-col :span="6">
                <el-button type="primary" @click="edit_class(item.video_id)">编辑/查看</el-button>
              </el-col>
              <el-col :span="6">
                <el-button type="primary" @click="delete_class(item.video_id,item.video_file_name)">删除</el-button>
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
  import VideoService from "../../api/VideoService.js"
  import Constant from "../../assets/constant.js"
  export default {
    inject: ["reload"],
    name: "class",
    components: {
      Search,
    },
    data() {
      return {
        classList: [],
      }
    },
    methods: {
      // 获取知识列表+查询列表
      getLoadclassList() {
          VideoService.get_video_list(1).then((res) => {this.classList = res})
      },
      // 编辑
      edit_class(video_id) {
        sessionStorage.setItem('judgeclass', true)
        sessionStorage.setItem('class_id', video_id)
        this.$router.push("/editclass_detail");
      },
      delete_class(video_id, video_file_name) {
          this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          VideoService.delete_video(video_id,video_file_name).then((res) => {
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
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
      
      },
      comment(video_id) {
        sessionStorage.setItem('operation_source_type', 2)
        sessionStorage.setItem('source_id', video_id)
        this.$router.push("/commentList")
      },
      //视频路径
      Video(row) {
        var url = Constant.URL+"/video/" + row.video_file_name
        return url
      },
    },
    computed: {},

    mounted() {
      this.getLoadclassList();
    }
  }
</script>

<style>
  /*  */
  #class .el-alert {
    margin-bottom: 10px;
  }

  #class .el-badge {
    font-size: 20px;
  }

  #class .el-icon-star-off:before {
    margin: 10px 10px 0;
    font-size: 30px;
  }
</style>

<style scoped>
  ul,
  li {
    list-style: none;
  }

  .class_list {
    display: inline-flex;
    flex-wrap: wrap;
  }

  .acitivity_item {
    margin: 10px;
    width: 300px;
    border: 1px solid #000;
  }

  .class_video {
    width: 200px;
    height: 150px;
    margin-left: 40px;
  }

  .class_title {
    margin-top: 10px;
    height: 40px;
    line-height: 40px;
    font-size: 25px;
    text-align: center;
  }

  .class_operation {
    height: 60px;
  }
</style>