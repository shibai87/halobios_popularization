<template>
  <div id="knowledge">
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
    height: 200px;
    position:relative;
     border-bottom: 1px solid #000;
}
.knowledge_title h1{
    width: 100px;
    height: 200px;
    position:absolute;
    top:50%;
    left:50%;
    margin-top:-height/2;
    margin-left:-width/2;
   
   
}
</style>
