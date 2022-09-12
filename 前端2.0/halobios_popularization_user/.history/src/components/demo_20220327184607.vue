<template>
  <div>
<li>
    <div class="item" v-for="(item,index) in hallImg" :key="index">
        <img :src="item" alt="">
    </div>
    <van-uploader :after-read="onRead2" class="upimg">
        <van-icon name="plus" />
    </van-uploader>
</li>复制代码
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

  },
  mounted () {

  }
}
</script>

<style scoped>

</style>

