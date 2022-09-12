<template>
  <div id="home">
    <!-- home_Swipe-->
      <!-- 轮播图有问题 -->
      <div class="home_Swipe">
         <van-swipe :autoplay="3000"  style="width: 500px">
  <van-swipe-item v-for="(image, index) in images" :key="index" >
    <img v-lazy="image"/>
  </van-swipe-item>
</van-swipe>
      </div>
     
      

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
          require("../assets/images/2.jpeg"),
           require("../assets/images/1.jpeg"),
          require("../assets/images/2.jpeg"),
        ],
        infos:[
          {
            img:require("../assets/images/1.jpeg"),
            title:"这是乌贼"
          },
           {
            img:require("../assets/images/2.jpeg"),
            title:"这是乌贼"
          },
           {
            img:require("../assets/images/3.jpeg"),
            title:"这是乌贼"
          },
           {
            img:require("../assets/images/4.jpeg"),
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
.home_Swipe .van-swipe{
    height: 300px;
}
  
.home_Swipe>.van-swipe>img{
     width: 100%;
      height: 100%;
      background-size: cover;
      background-position: center;
  }
/*  */



 
</style>
<style scoped>

/* home_Swipe */

/* home_other */
.home_other{
  display: inline-block;
  /* position: static; */
  
}
.home_other_info{
  margin: 10px;
  
}
.home_other_info img{
  
  width: 100px;
  height: 100px;
}
</style>