<template>
  <div id="user_infos">
      <!--user_infos_head  -->
      <van-nav-bar title="个人信息" left-arrow fixed @click-left="pageBack" />
  </div>
</template>

<script>
export default {
  name: '',
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
 /* user_infos_head */
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
    font-size: 35px;
  }

</style>
<style scoped>

</style>
