<template>
  <div id="index">
    <el-container>
  <el-aside width="200px">Aside</el-aside>
  <el-container>
    <el-header>海洋生物科普系统管理平台</el-header>
    <el-main>Main</el-main>
  </el-container>
</el-container>

    <!-- <router-link to="/knowledge">
      <div class="index_modular">
       <span>科普知识管理模块</span> 
       </div>
    </router-link>
     <router-link to="/protect">
       <div class="index_modular">保护海洋生物主题活动管理模块</div>
    </router-link>
     <router-link to="/class">
       <div class="index_modular">科普小课堂管理模块</div>
    </router-link>
     <router-link to="/user_manage">
       <div class="index_modular">用户管理模块</div>
    </router-link> -->
      
      
      
      
  </div>
</template>

<script>
export default {
  name: 'index',
  data () {
    return {
      msg: ''
    }
  },
  methods: {

  },
  mounted () {

  }
}
</script>
<style >
  .el-header{
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
  }
  
  .el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 200px;
  }
  
  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 160px;
  }
  
  body > .el-container {
    margin-bottom: 40px;
  }
  
  .el-container .el-aside {
    line-height: 260px;
  }
  
</style>
<style scoped>

</style>
