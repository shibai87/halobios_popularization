<template>
  <div id="user_center">
        <!--user_center_head -->
    <van-nav-bar title="个人中心" left-arrow fixed @click-left="pageBack" />
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

  },
  mounted () {

  }
}
</script>

<style scoped>

</style>
