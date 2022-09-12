<template>
  <div id="knowledge">
      <!-- knowledge_title -->
      <el-page-header @back="goBack" content="科普知识管理模块" class="knowledge_title">
</el-page-header>
      
  </div>
</template>

<script>
export default {
  name: '',
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

<style scoped>
.knowledge_title{
    width: 100%;
   height: 100px;
line-height: 100px;
  text-align: center;
     border-bottom: 1px solid #000;
}
.knowledge_title h1{
  display: inline;


   
   
}
</style>
