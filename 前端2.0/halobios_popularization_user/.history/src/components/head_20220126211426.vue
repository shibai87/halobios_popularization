<template>
    <div id="head">
      <van-nav-bar :title="get_title"  fixed @click-left="pageBack" @click-right="search">
          <template #left>
              <van-icon name="arrow-left" />
          </template>
        <template #right>
          <van-icon name="search"  />
             <div v-if="judge_login" @click="login_regiser">
            登录
          </div>
          <div v-else>
            <van-icon name="https://b.yzcdn.cn/vant/icon-demo-1126.png"   @click="user" class="icon_user"/>
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
       },
       judge_login(){
           return  this.$store.state.login
       }
      
    },
  methods: {
        pageBack() {
        window.history.back()
      },
      search() {
        this.$router.push("/search");
      },
       login_regiser() {
        this.$router.push("/login")
      },
      user() {
        this.$router.push("/user")
      }
  },
  mounted () {
     
  },

}
</script>
<style>
/* head */
#head .van-icon,.van-nav-bar__text{
    color: #000;
    font-size:25px;
}
#head .van-nav-bar__right{
  padding-right: 0;
}
</style>
<style scoped>
  .icon_user{
margin: 10px;
  }
</style>
