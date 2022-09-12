<template>
    <div id="head">
      <van-nav-bar :title="get_title" left-text="返回" left-arrow fixed @click-left="pageBack" @click-right="search">
        <template #right>
          <van-icon name="search" size="25" />
             <div v-if="login" @click="login_regiser">
            登录
          </div>
          <div v-else>
            <van-icon name="https://b.yzcdn.cn/vant/icon-demo-1126.png" size="25"  @click="user" class="icon_user"/>
          </div>
        </template>
      </van-nav-bar>
    </div>
</template>

<script>
export default {
  name: 'head',
  data () {
    return {

    }
  },
   computed:{
       get_title(){
          return  this.$store.state.title
       }
      
    },
  methods: {
        pageBack() {
        window.history.back()
      },
      search() {
        this.$router.push("/search");
      },
  },
  mounted () {
     
  },

}
</script>

<style scoped>

</style>
