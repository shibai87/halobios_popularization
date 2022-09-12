<template>
  <div id="activity">
    <!-- activity_Swipe-->
      <div id="activity_Swipe">
        <!-- {{title}} -->
        <van-swipe :autoplay="3000">
          <van-swipe-item v-for="(image, index) in images" :key="index">
            <img v-lazy="image" />
          </van-swipe-item>
        </van-swipe>
      </div>
      <!-- activity_other -->
      <div id="activity_other">
        <div v-for="(info,index) in infos" :key="index" class="activity_other_info">
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
            require("../assets/images/1.jpeg"),
          require("../assets/images/1.jpeg"),
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
#activity .van-tabs__nav{
    position: fixed;
  
}
</style>
<style scoped>
/* activity_Swipe */
#activity_Swipe{
  height: 200px;
}
#activity_Swipe img{
max-width: 100%;
width: 500px;
height: 100px;
}
/* activitye_other */
#activity_other{
margin-top: 200px;
}
.activity_other_info{
 
  display: inline-block;
  width: 30%;
  margin: auto;

}
#activity_other img{
  width: 150px;
  height: 150px;
}
</style>