<template>
  <div id="home">
    <!-- home_Swipe-->
      <!-- 轮播图有问题 -->
      <van-swipe :autoplay="3000">
  <van-swipe-item v-for="(image, index) in images" :key="index">
    <img v-lazy="image" />
  </van-swipe-item>
</van-swipe>
      

      <!-- home_other -->
     <div class="home_other">
        <div v-for="(info,index) in infos" :key="index" class="home_other_info">
         <img :src="info.img" >
         <div>{{info.title}}</div>
        </div>
     </div>
       
      
  </div>
</template>

<script>
import Vue from 'vue';
import { Lazyload } from 'vant';
Vue.use(Lazyload);
  export default {
    name: 'home',
    data() {
      return {
        title: "海洋生物科普首页",
        images: [
          require("../assets/images/1.jpeg"),
          require("../assets/images/1.jpeg"),
        ],
        infos:[
          {
            img:require("../assets/images/1.jpeg"),
            title:"这是乌贼"
          },
           {
            img:require("../assets/images/1.jpeg"),
            title:"这是乌贼"
          },
           {
            img:require("../assets/images/1.jpeg"),
            title:"这是乌贼"
          },
           {
            img:require("../assets/images/1.jpeg"),
            title:"这是乌贼"
          },
        ]
      }
    },
    methods: {

    },
    mounted() {

    }
  }
</script>

<style>
/* home_Swipe */
#home .van-swipe{
    height: 300px;
}
  
#home .van-swipe  img{
      width: 100%;
      height: 100%;
     
  }
/*  */



 
</style>
<style scoped>

/* home_Swipe */

/* home_other */
.home_other{
  width: 100%;
  /* width: 80%;
  margin: 10px auto; */
}
.home_other_info{
  margin: 10px;
  display: inline-block;
}
.home_other_info img{
  width: 100px;
  height: 100px;
}
</style>