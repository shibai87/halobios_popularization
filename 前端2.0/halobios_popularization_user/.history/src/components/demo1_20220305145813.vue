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
  .my-swipe .van-swipe-item {
    color: #fff;
    font-size: 20px;
    line-height: 150px;
    text-align: center;
    background-color: #39a9ed;
  }
  img{
      background-size: cover;
  }
</style>
