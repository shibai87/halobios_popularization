<template>
  <div id="index">
    <el-container style="height: 720px; border: 1px solid #eee">
      <el-aside width="320px" style="background-color: rgb(238, 241, 246)">
        <el-menu :default-openeds="['1','2','3','4']">
          <el-submenu index="1">
            <template slot="title">科普知识管理模块</template>
            <el-menu-item-group>
              <el-menu-item index="1-1" @click="tabSelect(1)">查看科普知识列表（默认首页）</el-menu-item>
              <el-menu-item index="1-2" @click="tabSelect(2)">新增科普知识</el-menu-item>
              <el-menu-item index="1-3" @click="tabSelect(3)">未上传的科普知识列表</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title">保护海洋生物主题活动管理模块</template>
            <el-menu-item-group>
              <el-menu-item index="2-1" @click="tabSelect(4)">查看活动列表</el-menu-item>
              <el-menu-item index="2-2" @click="tabSelect(5)">新增活动</el-menu-item>
              <el-menu-item index="2-3" @click="tabSelect(6)">未发布的活动列表</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title">科普小课堂管理模块</template>
            <el-menu-item-group>
              <el-menu-item index="3-1" @click="tabSelect(7)">查看小课堂列表</el-menu-item>
              <el-menu-item index="3-2" @click="tabSelect(8)">新增小课堂</el-menu-item>
              <el-menu-item index="3-3" @click="tabSelect(9)">未发布的小课堂列表</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <el-submenu index="4">
            <template slot="title">用户管理模块</template>
            <el-menu-item-group>
              <el-menu-item index="4-1" @click="tabSelect(10)">审核举报</el-menu-item>
              <el-menu-item index="4-2" @click="tabSelect(11)">管理用户</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
        </el-menu>
      </el-aside>

      <el-container>
        <el-header>
          <el-row>
             <el-col :span="1" >
              <button style="border: none; border-radius: 5px;height: 30px; width: 60px;" @click="refresh()">刷新</button>
            </el-col>
            <el-col :span="6" :offset="8">
              <h1 class="index_title">海洋生物科普系统管理平台</h1>
            </el-col>
            <el-col :span="1" :offset="7">
              <router-link to="/">
                <button style="border: none; border-radius: 5px;height: 30px; width: 60px;">注销</button>
              </router-link>
            </el-col>
          </el-row>
        </el-header>

        <el-main>
          <component :is="interfaceView"></component>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
  import Knowledge from "../view/knowledge/knowledge.vue"
  import AddKnowledge from "../view/knowledge/addknowledge.vue"
  import Editknowledge from "../view/knowledge/editknowledge.vue"
  import Activity from "../view/activity/activity.vue"
  import AddActivity from "../view/activity/addactivity.vue"
  import EditActivity from "../view/activity/editactivity.vue"
  import Class from "../view/class/class.vue"
  import AddClass from "../view/class/addclass.vue"
  import EditClass from "../view/class/editclass.vue"
  import Report from "../view/user/report.vue"
  import UserManage from "../view/user/user_management.vue"
  export default {
    name: 'index',
     inject: ["reload"],
    data() {
      return {
        interfaceView: Knowledge,
      }
    },
    methods: {
      refresh(){
         this.reload();
      },
      tabSelect(i) {
        switch (i) {
          case 1: {
            // 查看科普分类知识
            this.interfaceView = Knowledge;
            sessionStorage.setItem('search_type', 0)
            break;
          }
          case 2: {
            // 新增科普分类知识
            this.interfaceView = AddKnowledge;
            break;
          }
          case 3: {
            //  编辑科普分类知识
            this.interfaceView = Editknowledge;
            break;
          }
          // 活动
          case 4: {
            // 查看活动
            this.interfaceView = Activity;
            sessionStorage.setItem('search_type', 1)
            break;
          }
          case 5: {
            // 新建活动
            this.interfaceView = AddActivity;
            break;
          }
          case 6: {
            // 编辑活动
            this.interfaceView = EditActivity;
            break;
          }
          case 7: {
            // 查看学习视频
            this.interfaceView = Class;
            sessionStorage.setItem('search_type', 2)
            break;
          }
          case 8: {
            // 新建学习视频
            this.interfaceView = AddClass;
            break;
          }
          case 9: {
            // 编辑学习视频
            this.interfaceView = EditClass;
            break;
          }
          case 10: {
            this.interfaceView = Report;
            break;
          }
          case 11: {
            this.interfaceView = UserManage;
            break;
          }
          default:
            break;
        }
      },
    },
  }
</script>

<style>
  /* 侧边 */
  #index .el-aside {
    background-color: #D3DCE6;
    color: #333;
  padding-left: 0;
    line-height: 800px;
  }
#index .el-submenu__title{
  margin-left: 0;
  font-size: 20px;
}
  #index .el-submenu .el-menu-item {
    font-size: 17px;
  }

  #index .el-menu-item-group {
    background-color: #F2F6FC;
  }

  /* 头部 */
  #index .el-header {
    background-color: #409EFF;
    color: #333;
    font-size: 25px;
  }

  .el-col {
    margin-top: 8px;
  }

  /* 主体 */
  #index .el-main {
    background-color: #E9EEF3;
  }
</style>

<style scoped>
  .index_title {
    display: inline-block;
    width: 450px;
  }
</style>