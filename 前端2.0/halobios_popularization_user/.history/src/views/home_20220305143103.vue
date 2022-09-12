<template>
  <div id="home">
    <!-- home_Swipe-->
     
        <van-swipe :autoplay="3000" style="width:1000px;height:300px">
          <van-swipe-item v-for="(image, index) in images" :key="index"  style="width:1000px;height:300px">
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
          'https://img01.yzcdn.cn/vant/apple-2.jpg',
          'https://img01.yzcdn.cn/vant/apple-2.jpg',
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
.van-swipe__track img{
  width: 1000px;
  height:300px
}

/*
 .van-swipe-item{
background-size:cover;
/*让图片覆盖满整个div*/
    /* background-position:center center; */
    /*让图片在div里水平垂直居中*/
/* } */
 
</style>
<style scoped>

/* home_Swipe */

/* home_other */

</style>