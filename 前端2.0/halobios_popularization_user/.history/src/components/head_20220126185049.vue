<template>
  <div>
        <!-- head -->
    <div id="index_head">
      <van-nav-bar :title="title" left-text="返回" left-arrow fixed @click-left="pageBack" @click-right="search">
        <template #right>
          <van-icon name="search" size="20" />
        </template>
      </van-nav-bar>
    </div>
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
