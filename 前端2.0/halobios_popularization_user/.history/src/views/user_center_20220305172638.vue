<template>
  <div id="user_center">
    <!--user_center_head -->
    <van-nav-bar title="个人中心" left-arrow fixed @click-left="pageBack" />
    <!-- user_center_main -->
    <ul>
      <li>
         <!-- user_info1 -->
    <van-row>
      <van-col span="4"  offset="2" >
        <van-image round width="100px" height="100px" src="https://img01.yzcdn.cn/vant/cat.jpeg" />
      </van-col>
      <van-col span="14"  offset="4" >sdfdg</van-col>

    </van-row>

      </li>
      <li></li>
      <li></li>
      <li></li>
      <li></li>
      <li></li>
    </ul>
   



  </div>

</template>

<script>
  export default {
    name: 'user_center',
    data() {
      return {
        msg: ''
      }
    },
    methods: {
      pageBack() {
        window.history.back()
      },
    },
    mounted() {

    }
  }
</script>
<style>
  /* user_center_head */
  #user_center .van-nav-bar__content {
    height: 70px;
  }

  #user_center .van-nav-bar__title {
    height: 30px;
    line-height: 30px;
    font-size: 25px;

  }

  #user_center .van-icon:before {
    color: #000;
    font-size: 30px;
  }

  /*  */
  .van-row{
    margin-top: 100px;
  }
  .van-col--14{
    height:100px;
    line-height:100px;
    text-align: left;
  }
</style>
<style scoped>
  .user_img,
  .user_name {
    display: inline-block;
  }

  .user_name{
    height: 30px;
    line-height: 30px;
  }
</style>