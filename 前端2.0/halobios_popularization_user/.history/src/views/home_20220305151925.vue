<template>
  <div>
    <!-- 轮播图有问题 -->
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
<style>

.van-swipe{
    height: 300px;
}
  
.van-swipe  img{
      width: 100%;
      height: 100%;
      background-size: cover;
  }
</style>
<style scoped>

</style>
