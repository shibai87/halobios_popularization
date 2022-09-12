<template>
    <div id="index_search">   
        <!-- index_search_head -->
        <van-nav-bar title="登录" left-arrow fixed @click-left="pageBack" />
        <!--  -->
       
                <form action="/">
                   <van-search
  v-model="value"
  placeholder="请输入搜索关键词"
  input-align="center"
/>
                </form>
          
    </div>
</template>

<script>
    import {
        Toast
    } from 'vant';
    export default {
         name: 'index_search',
        data() {
            return {   
                search_value: '',
            }
        },
     
        methods: {
            pageBack() {
                window.history.back()
            },
            onSearch(val) {
                Toast(val);
            },
          
        }
    }
</script>

<style>

 #index_search .van-nav-bar__content {
    height: 70px;
  }

  #index_search .van-nav-bar__title {
    height: 30px;
    line-height: 30px;
    font-size: 25px;

  }

  #index_search .van-icon:before {
    color: #000;
    font-size: 35px;

  }
  /*  */


</style>
<style scoped>

</style>