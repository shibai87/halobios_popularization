<template>
  <div>
      <van-swipe :autoplay="3000">
  <van-swipe-item v-for="(image, index) in images" :key="index">
    <img v-lazy="image" />
  </van-swipe-item>
</van-swipe>
  </div>
</template>

<script>


export default {
  data() {
    return {
      images: [
        require("../assets/images/1.jpeg"),
        require("../assets/images/1.jpeg"),
      ],
    };
  },
};
</script>

<style scoped>
  
  img{
      width: 100%;
      background-size: cover;
  }
</style>
