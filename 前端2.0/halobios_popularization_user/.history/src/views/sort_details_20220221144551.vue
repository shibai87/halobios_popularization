<template>
  <div id="sort_details">
      <div class="sort_details_head">
           <van-nav-bar title="分类科普详情页" fixed>
        <template #right>
          <van-icon name="search" @click="search" />
          <div v-if="judge_login">
            <van-icon class="icon_user" name="https://b.yzcdn.cn/vant/icon-demo-1126.png" @click="user" />
          </div>
          <div v-else class="login" @click="login">
            登录
          </div>
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
