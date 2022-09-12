<template>
  <div id="login">
    <!--login_head -->
      <van-nav-bar title="登录"  left-arrow fixed @click-left="pageBack"/>

    <div id="login_main">
      <van-form @submit="onSubmit">
        <van-field v-model="tel"  type="tel" name="手机号" label="手机号" placeholder="请输入手机号"  clearable required  
        :focused="showKeyboard_tel" @focus="showKeyboard_tel = true" 
          :rules="[{ required: false, message: '请输入手机号' }]" />
          <van-number-keyboard  v-model="tel"  :show="showKeyboard_tel" @blur="showKeyboard_tel = false" />
        <van-field v-model="password" type="password" name="密码" label="密码" placeholder="请输入密码"  clearable required  
         :focused="showKeyboard_pwd" @focus="showKeyboard_pwd = true" 
         :rules="[{ required: false, message: '请填写密码' }]" />
         <van-number-keyboard  v-model="password"  :show="showKeyboard_pwd" @blur="showKeyboard_pwd = false" />
         <router-link to="/register">
           <div class="login_tip">
           未注册，请点击这里
         </div>
         </router-link>
        
        <div style="margin: 16px;">
          <van-button round block type="info" native-type="submit">提交</van-button>
        </div>
      </van-form>
   <!-- 数字键盘 -->


    </div>

  </div>
</template>

<script>



 
  export default {
    name: 'login',
    components: {
    
    },
    data() {
      return {
        tel: '',
        password: '',
        showKeyboard_tel: false,
        showKeyboard_pwd: false,

        
      }
    },
    methods: {
       pageBack() {
                window.history.back()
            },
      change_login() {
        this.$store.commit("judgeLogin", true)
      },
      
      // 
      onSubmit(values) {
        console.log('submit', values);
      },

     
    },
    computed:{
     
    },
    mounted() {
     
     
      this.change_login();
    }
  }
</script>
<style>
/* login_head */
#login .van-nav-bar{
  border-bottom: 1px solid #000;
}
 #login .van-nav-bar__content {
    height: 70px;
    
    
  }

  #login .van-nav-bar__title {
    height: 30px;
    line-height: 30px;
    font-size: 25px;

  }

  #login .van-icon:before {
    color: #000;
    font-size: 30px;
   
  }
  .van-form{
    margin-top: 200px;
    
  }

</style>
<style scoped>
 /* #login_main{
   margin-top: 20px;
 } */
  .login_tip{
    color: rgb(55, 12, 212);
   margin-left: 50%;
  }
</style>