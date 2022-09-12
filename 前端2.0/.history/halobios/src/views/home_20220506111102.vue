<template>
  <div id="home">
    <div class="home_title">
      <h1>海洋生物</h1>
    </div>
    <div class="home_img">
      <img src="../assets/images/home_1.png" alt="图片丢失">
    </div>
    <ul class="home_content">
      <li>
          <span>海洋生物概述：</span>
      <p>海洋生物是指海洋里有生命的物种，包括海洋动物、海洋植物、微生物及病毒等，其中海洋动物包括无脊椎动物和脊椎动物。
        无脊椎动物包括各种螺类和贝类。脊椎动物包括各种鱼类和大型海洋动物，如鲸鱼，鲨鱼等。</p>
      </li>
      <li>
         <span>我国海洋生物概述：</span>
      <p>经过几十年来海洋科技工作者的调查研究，已在我国管辖海域记录到了20278 种海洋生物。这些海洋生物隶属于5个生物界、44个生物门。
        其中动物界的种类最多（12794种），原核生物界最少（229种）。我国的海洋生物种类约占全世界海洋生物总种数的10%，数量占50%。</p>
      </li>
    </ul>
  </div>
</template>
<script>
  export default {
    name: 'home',
    data() {
      return {
        msg: ''
      }
    },
    methods: {

    },
    mounted() {

    }
  }
</script>
<style scoped>
#home{
  height: 670px;
  overflow-x:scroll;
}
  .home_title h1 {
    font-size: 30px;
    text-align: center;
  }
  .home_img img{
    width: 400px;
  }
  .home_content {
    text-align: left;
     padding:0 15px;
  }
  .home_content span{
  font-weight:bold;
    font-size: 25px;
  }
    .home_content p{
    font-size: 24px;
     text-indent: 2em;
  }
</style>