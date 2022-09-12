<template>
  <div id="index">
      <div class="index_modular">
       <span>科普知识管理模块</span> 
       </div>
      <div class="index_modular">保护海洋生物主题活动管理模块</div>
      <div class="index_modular">科普小课堂管理模块</div>
      <div class="index_modular">用户管理模块</div>
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

<style scoped>
.index_modular{
  width: 100%;
  height: 125px;
  border: 1px solid #fff;
}
.index_modular span{
  width: 100px;
  height: 50px;
  margin: auto;
}
</style>
