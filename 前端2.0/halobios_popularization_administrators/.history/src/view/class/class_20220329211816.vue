<template>
  <div id="class">
    <!-- 分类知识详情 -->
    <div v-if="classList.length">
      <ul v-for="(item,index) in classList" :key="index" class="class_list">
        <li class="acitivity_item">
            <video controls :src="Video(item)" class="class_video"></video>
            <div class="class_title">
              <span>{{item.video_title}}</span>
              <el-badge :value="3" class="item">
                <i class="el-icon-star-off"></i>
              </el-badge>
            </div> 
            <div class="class_operation"> 
              <!-- <el-button @click="comment(item.class_id)">评论列表(待定)</el-button> -->
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
        this.axios.get("http://localhost:8083/video/get_video_list?video_upload=" + 1)
          .then((res) => {
            // console.log(res.data,res.data.length)
            this.classList = res.data
            // console.log(this.classList.length)
          })

      },
      // 编辑
      edit_class(class_id) {
        this.$store.commit("judgeclass", true)
         localStorage.setItem('class_id', class_id);
        this.$store.commit("getclass", class_id)
        this.$router.push("/editclass_detail");
      },
      delete_class(class_id){
        this.axios.get("http://localhost:8083/video/delete_video?video_id="+class_id)
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
      this.getLoadclassList();
    }
  }
</script>
<style>
  /*  */
  #class .el-alert {
    margin-bottom: 10px;
  }



  #class .el-button {
    display: block;
    width: 150px;
    margin: 10px auto;


  }

  #class .el-badge {
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