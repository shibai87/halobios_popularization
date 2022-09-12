<template>
  <div id="knowledge">
      <el-page-header @back="goBack" content="详情页面">
</el-page-header>
      <div class="knowledge_title">
          <h1>科普知识管理模块</h1>
      </div>
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
