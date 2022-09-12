<template>
  <div id="index">
    <el-container>
  <el-aside width="200px">Aside</el-aside>
  <el-container>
    <el-header>Header</el-header>
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
  .el-header, .el-footer {
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
  
  .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 260px;
  }
  
  .el-container:nth-child(7) .el-aside {
    line-height: 320px;
  }
</style>
<style scoped>

/* .index_modular{
  display: block;
  width: 100%;
  height:200px;
  line-height: 200px;
  border: 1px solid rgb(64, 84, 153);
  text-align: center;
  font-size: 30px;
} */

</style>
