<template>
  <div id="home">
    <!-- home_Swipe-->
     
        <van-swipe :autoplay="3000" style="width:2000px;height:300px ">
          <van-swipe-item v-for="(image, index) in images" :key="index"  style="width:2000px;height:300px ">
            <img v-lazy="image" />
          </van-swipe-item>
        </van-swipe>
      

      <!-- home_other -->
     
        <!-- <div v-for="(info,index) in infos" :key="index" class="home_other_info">
         <img :src="info.img" >
         <div>{{info.title}}</div>
        </div> -->
      
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
.van-swipe{
 
  
   
}
/deep/ .van-swipe__track{
  transform: translateX(-2000px); 
}
.van-swipe__track img{
  width: 2000px;
  height:300px;
  background-size:cover;
/*让图片覆盖满整个div*/
}

/*
 .van-swipe-item{

    /* background-position:center center; */
    /*让图片在div里水平垂直居中*/
/* } */
 
</style>
<style scoped>

/* home_Swipe */

/* home_other */

</style>