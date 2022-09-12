<template>
  <div id="editclass">
    <!-- 分类知识详情 -->
    <div v-if="classList.length">
      <ul v-for="(item,index) in classList[0]" :key="index" class="class_list">
        <li class="acitivity_item">
              <div class="class_title">
               <span v-if="!item.video_title">标题未编辑</span>
                <span v-else>{{item.video_title}}</span>
              </div>
              <video controls :src="Video(item)" class="class_video"></video>
              <div class="class_operation">
                <el-button type="primary" @click="edit_class(item.video_id)">编辑</el-button>
                <el-button type="primary" @click="delete_class">删除</el-button>
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
  VideoService.get_video_list(0)
        .then(response => {
          classList.push(response)
          console.log(classList)
        })
        .catch(error => {
            console.log(error)
        })
})
const edit_class=(class_id) => {
  sessionStorage.setItem('judgeclass', "false")
           sessionStorage.setItem('class_id', class_id)
        router.push("/editclass_detail");
}
const delete_class=()=> {
    ElMessageBox.confirm('此操作将永久删除该文件, 是否继续?', '提示',
        {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        }
    )
        .then(() => {
          VideoService.delete_video(classList.video_id).then((res) => {
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

const Video = (row) => Constant.URL+"/activity/" + row.activity_file_name

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