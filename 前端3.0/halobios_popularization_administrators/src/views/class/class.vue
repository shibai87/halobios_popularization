<template>
  <div id="class">
    <Search></Search>
    <!-- 分类知识详情 -->
    <div v-if="classList.length">
      <ul v-for="(item,index) in classList[0]" :key="index" class="class_list">
        <li class="acitivity_item">
          <div class="class_title">
            <span>{{item.video_title}}</span>
          </div>
          <video controls :src="Video(item)" class="class_video"></video>
          <div class="class_operation">
            <el-row >
              <el-col :span="11" >
                <el-button type="primary" @click="comment(item.video_id)">用户操作详情</el-button>
              </el-col>
              <el-col :span="7" >
                <el-button type="primary" @click="edit_class(item.video_id)">编辑</el-button>
              </el-col>
              <el-col :span="6" >
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

<script setup lang="ts">
  import Search from "../../components/search.vue"
  import VideoService from "../../api/VideoService.js"
  import Constant from "../../assets/constant.js"
  import { reactive} from "vue"
  import { onMounted } from "vue"
  import { useRouter } from 'vue-router'
  import { useStore } from "vuex"
  import {inject} from "vue"
  import { ElMessage,ElMessageBox } from 'element-plus'
  const router = useRouter();
  const store = useStore()
  const refresh = inject<any>("reload")
// 异步请求首屏数据
const  classList= reactive<any>([]);
 // 获取知识列表+查询列表
onMounted(() => {
  VideoService.get_video_list(1)
        .then(response => {
          classList.push(response)
          console.log(classList)
        })
        .catch(error => {
            console.log(error)
        })
})
const edit_class=(video_id) => {
  sessionStorage.setItem('judgeclass', "true")
        sessionStorage.setItem('class_id', video_id)
        router.push("/editclass_detail");
}
const delete_class=(video_id, video_file_name)=> {
    ElMessageBox.confirm('此操作将永久删除该文件, 是否继续?', '提示',
        {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        }
    )
        .then(() => {
          VideoService.delete_video(video_id,video_file_name).then((res) => {
                if (res.msg === "删除成功") {
                    ElMessage({
                        message: "删除成功!",
                        type: "success",
                    });
                } else {
                    ElMessage({
                        message: "删除失败!",
                        type: "warning",
                    });
                }
                refresh()
            });
        }).catch(() => {
            ElMessage({
                type: 'info',
                message: '已取消删除'
            });
        })
}
//评论按钮
const comment = (video_id) => {
  sessionStorage.setItem('operation_source_type', "2")
        sessionStorage.setItem('source_id', video_id)
        router.push("/commentList")
}
const Video = (row) => Constant.URL+"/video/" + row.video_file_name

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