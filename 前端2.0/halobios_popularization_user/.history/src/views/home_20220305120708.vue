<template>
  <div id="home">
    <!-- home_Swipe-->
      <div id="home_Swipe">
        <van-swipe :autoplay="3000">
          <van-swipe-item v-for="(image, index) in images" :key="index" style="width: 100%">
            <img v-lazy="image" style="" />
          </van-swipe-item>
        </van-swipe>
      </div>

      <!-- home_other -->
      <div id="home_other">
        <div v-for="(info,index) in infos" :key="index" class="home_other_info">
         <img :src="info.img" >
         <div>{{info.title}}</div>
        </div>
      </div>
  </div>
</template>

<script>
  export default {
    name: 'home',
    data() {
      return {
        title: "海洋生物科普首页",
        images: [
          'https://img01.yzcdn.cn/vant/apple-1.jpg',
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
#home .van-tabs__nav{
    position: fixed;
    top:10%;
    /* left:0; */
    z-index:1;
}
.van-swipe-item{
  
}
</style>
<style scoped>
/* home_Swipe */
#home_Swipe{
  
}
#home_Swipe img{

}
/* home_other */
#home_other{
padding-top: 15%;
}
.home_other_info{
 margin-bottom: 1rem;
  display: inline-block;
  width: 50%;
}
#home_other img{
  width: 15rem;
  height: 150px;
}
</style>