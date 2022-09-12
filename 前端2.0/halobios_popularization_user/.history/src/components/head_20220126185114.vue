<template>
    <div id="head">
      <van-nav-bar :title="title" left-text="返回" left-arrow fixed @click-left="pageBack" @click-right="search">
        <template #right>
          <van-icon name="search" size="20" />
        </template>
      </van-nav-bar>
    </div>
</template>

<script>
export default {
  name: 'head',
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
