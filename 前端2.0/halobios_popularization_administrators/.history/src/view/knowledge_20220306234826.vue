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
<style>
#knowledge .el-page-header__title,.el-page-header__content{
    font-size: 30px;
}
</style>
<style scoped>
/*  */
.knowledge_title{
    margin: 20px;
}

</style>
