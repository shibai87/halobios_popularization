<template>
  <div id="index">
    <el-container style="height: 800px; border: 1px solid #eee">
      <el-aside width="300px" style="background-color: rgb(238, 241, 246)">
        <el-menu :default-openeds="['1','2', '3','4']">
          <el-submenu index="1">
            <template slot="title"><i class="el-icon-message"></i>科普知识管理模块</template>
            <el-menu-item-group>
              <el-menu-item index="1-1"  @click="tabSelect(1)">查看知识（默认首页）</el-menu-item>
              <el-menu-item index="1-2"  @click="tabSelect(2)">新增知识</el-menu-item>
              <el-menu-item index="1-3"  @click="tabSelect(3)">编辑未上传的知识</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title"><i class="el-icon-menu"></i>保护海洋生物主题活动管理模块</template>
            <el-menu-item-group>
              <el-menu-item index="2-1" @click="tabSelect(4)">查看活动</el-menu-item>
              <el-menu-item index="2-2" @click="tabSelect(5)">新建活动</el-menu-item>
              <el-menu-item index="2-3" @click="tabSelect(6)">未发布的活动</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title"><i class="el-icon-setting" @click="tabSelect(3)"></i>科普小课堂管理模块</template>
           <el-menu-item-group>
              <el-menu-item index="3-1" @click="tabSelect(7)">查看活动</el-menu-item>
              <el-menu-item index="3-2" @click="tabSelect(8)">新建活动</el-menu-item>
              <el-menu-item index="3-3" @click="tabSelect(9)">未发布的活动</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <el-submenu index="4" @click="tabSelect(4)">
            <template slot="title"><i class="el-icon-setting"></i>用户管理模块</template>
            <el-menu-item-group>
              <el-menu-item index="4-1" @click="tabSelect(4)">审核举报</el-menu-item>
              <el-menu-item index="4-2" @click="tabSelect(5)">管理用户</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
        </el-menu>
      </el-aside>

      <el-container>
        <el-header>海洋生物科普系统管理平台
          <router-link to="/"> 
          <button style="border: none; border-radius: 5px; margin-left: 2000px; height: 30px; width: 60px;\">注销</button>
          </router-link>
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
  import UserManage from "../view/user_management.vue"
  export default {
    name: 'index',
    data() {
      return {
        interfaceView: Knowledge,
      }
    },
    methods: {
      tabSelect(i) {
        // window.alert(i)
        switch (i) {
          case 1: {
            // 查看科普分类知识
            this.interfaceView = Knowledge;
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
          case 4: {
            // 查看活动
            this.interfaceView = Activity;
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
            // 
            this.interfaceView = Class;
            break;
          }
          case 8: {
            this.interfaceView = UserManage;
            break;
          }
          default:
            break;
        }
      },

    },
    mounted() {

    }
  }
</script>
<style>
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
  }

  .el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 800px;
  }

  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 160px;
  }

  body>.el-container {
    margin-bottom: 40px;
  }

  /* .el-container .el-aside {
    line-height: 260px;
  } */
</style>
<style scoped>

</style>