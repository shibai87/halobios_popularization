<template>
  <div id="home">
    <!-- home_Swipe-->
      <div id="home_Swipe">
        <!-- {{title}} -->
        <van-swipe :autoplay="3000">
          <van-swipe-item v-for="(image, index) in images" :key="index">
            <img v-lazy="image" />
          </van-swipe-item>
        </van-swipe>
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
#home_Swipe{
  
  height: 30%;
}
#home_Swipe img{
/* height: 30%; */

max-width: 100%;
}
</style>