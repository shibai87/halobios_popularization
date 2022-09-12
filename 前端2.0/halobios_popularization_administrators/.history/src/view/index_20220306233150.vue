<template>
  <div id="index">
    <router-link to="/knowledge">
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
    </router-link>
      
      
      
      
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
  
 
  border: 1px solid rgb(64, 84, 153);
  
  font-size: 30px;
}
.index_modular h1{
  display: block;
  width: 100px;
  height:200px;
  margin: auto;
}

</style>
