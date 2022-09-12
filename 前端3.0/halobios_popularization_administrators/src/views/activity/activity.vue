<template>
  <div id="activity">
    <Search></Search>
    <!-- 分类知识详情 -->
    <div v-if="activityList.length">
      <ul v-for="(item,index) in activityList[0]" :key="index" class="activity_list">
        <li class="acitivity_item">
          <div class="activity_title">
            <span>{{item.activity_title}}</span>
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

<script setup lang="ts">
  import Search from "../../components/search.vue"
  import ActivityService from "../../api/ActivityService.js"
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
const  activityList= reactive<any>([]);
 // 获取知识列表+查询列表
onMounted(() => {
  ActivityService.get_activity_list(1)
        .then(response => {
          activityList.push(response)
          console.log(response)
        })
        .catch(error => {
            console.log(error)
        })
})
const edit_activity=(activity_id) => {
  sessionStorage.setItem('judgeactivity', "true")
        sessionStorage.setItem('activity_id', activity_id)
        router.push("/editactivity_detail");
}
const delete_activity=(activity_id, activity_file_name)=> {
    ElMessageBox.confirm('此操作将永久删除该文件, 是否继续?', '提示',
        {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        }
    )
        .then(() => {
          ActivityService.delete_activity(activity_id,activity_file_name).then((res) => {
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
const comment = (activity_id) => {
  sessionStorage.setItem('operation_source_type', "1")
        sessionStorage.setItem('source_id', activity_id)
        router.push("/commentList")
}
const Video = (row) => Constant.URL+"/activity/" + row.activity_file_name

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