<template>
  <div id="index">
    <el-container style="height: 800px; border: 1px solid #eee">

      <el-aside width="300px" style="background-color: rgb(238, 241, 246)">

        <el-menu :default-openeds="['1', '3']">
          <!-- <el-menu-item index="1-1">
            <i class="el-icon-message"></i>
            科普知识管理模块
          </el-menu-item> -->
          <el-submenu index="1">
            <template slot="title"><i class="el-icon-message"></i>科普知识管理模块</template>
            <el-menu-item-group>
              <el-menu-item index="1-1"  @click="tabSelect(1)">查看科普分类知识（默认首页）</el-menu-item>
              <el-menu-item index="1-2"  @click="tabSelect(2)">新增科普分类知识</el-menu-item>
              <el-menu-item index="1-3"  @click="tabSelect(3)">编辑未上传的科普分类知识</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title"><i class="el-icon-menu"></i>保护海洋生物主题活动管理模块</template>
            <el-menu-item-group>
              <el-menu-item index="2-1" @click="tabSelect(4)">查看保护海洋生物主题活动</el-menu-item>
              <el-menu-item index="2-2" @click="tabSelect(5)">新建保护海洋生物主题活动</el-menu-item>
              <el-menu-item index="2-3" @click="tabSelect(6)">编辑未上传的保护海洋生物主题活动</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="2-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="2-4">
              <template slot="title">选项4</template>
              <el-menu-item index="2-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title"><i class="el-icon-setting" @click="tabSelect(3)"></i>科普小课堂管理模块</template>
            <!-- <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="3-1">选项1</el-menu-item>
              <el-menu-item index="3-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="3-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="3-4">
              <template slot="title">选项4</template>
              <el-menu-item index="3-4-1">选项4-1</el-menu-item>
            </el-submenu> -->
          </el-submenu>
          <el-submenu index="4" @click="tabSelect(4)">
            <template slot="title"><i class="el-icon-setting"></i>用户管理模块</template>
            <!-- <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="4-1">选项1</el-menu-item>
              <el-menu-item index="4-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="4-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="4-4">
              <template slot="title">选项4</template>
              <el-menu-item index="4-4-1">选项4-1</el-menu-item>
            </el-submenu> -->
          </el-submenu>
        </el-menu>
      </el-aside>

      <el-container>
        <el-header>海洋生物科普系统管理平台
            <button style="border: none; border-radius: 5px; margin-left: 1000px; height: 30px; width: 60px;\"
            @click="logout()">注销</button>
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
  import Protect from "../view/protect.vue"
  import Class from "../view/class.vue"
  import UserManage from "../view/user_management.vue"
  export default {
    name: 'index',
    data() {
      return {
        interfaceView: Knowledge,
      }
    },
    methods: {
      logout() {
        this.$router.go(0);
      },
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
            this.interfaceView = Editknowledge;
            break;
          }
          case 4: {
            this.interfaceView = Protect;
            break;
          }
          case 5: {
            this.interfaceView = Class;
            break;
          }
          case 6: {
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