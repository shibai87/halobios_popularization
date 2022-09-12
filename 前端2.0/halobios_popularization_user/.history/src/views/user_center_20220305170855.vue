<template>
  <div id="user_center">
        <!--user_center_head -->
    <van-nav-bar title="个人中心" left-arrow fixed @click-left="pageBack" />
    <!-- user_center_main -->
      <div>
        <div class="user_img">
          <van-image
  round
  width="10rem"
  height="10rem"
  src="https://img01.yzcdn.cn/vant/cat.jpeg"
/>
        </div>
        <div class="user_name"></div>
      </div>
  </div>
</template>

<script>
export default {
  name: 'user_center',
  data () {
    return {
      msg: ''
    }
  },
  methods: {
     pageBack() {
        window.history.back()
      },
  },
  mounted () {

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
</style>
<style scoped>

</style>
