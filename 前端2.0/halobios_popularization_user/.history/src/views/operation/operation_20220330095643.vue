<template>
  <div>
<van-tabs v-model="active">
    <van-tab title="全部评论">科普</van-tab>
  <van-tab title="科普知识区">科普</van-tab>
  <van-tab title="科普活动区">内容 2</van-tab>
  <van-tab title="科普小课堂区">内容 3</van-tab>
</van-tabs>
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
