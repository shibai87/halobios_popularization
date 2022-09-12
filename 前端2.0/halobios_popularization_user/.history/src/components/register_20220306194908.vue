<template>
  <div id="register">
    <!-- register_head -->
      <van-nav-bar title="注册"  left-arrow fixed @click-left="pageBack"/>

<!-- register_main -->
      <van-form @submit="onSubmit">
         <van-field v-model="user_name"  type="text" name="用户名" label="用户名" placeholder="请输入用户名"  clearable required  
        :focused="showKeyboard_tel" @focus="showKeyboard_tel = true" 
          :rules="[{ required: true, message: '请输入用户名' }]" />
        <van-field v-model="tel"  type="tel" name="手机号" label="手机号" placeholder="请输入手机号"  clearable required  
        :focused="showKeyboard_tel" @focus="showKeyboard_tel = true" 
          :rules="[{ validator, message: '号码格式错误'  }]" />
          <!-- <van-number-keyboard  v-model="tel"  :show="showKeyboard_tel" @blur="showKeyboard_tel = false" /> -->
        <van-field v-model="password" type="password" name="密码" label="密码" placeholder="请输入密码"  clearable required  
         :focused="showKeyboard_pwd" @focus="showKeyboard_pwd = true" :rules="[{ required: true, message: '请填写密码' }]"/>
         <van-number-keyboard  v-model="password"  :show="showKeyboard_pwd" @blur="showKeyboard_pwd = false" />
        <div style="margin: 16px;">
          <van-button round block type="info" native-type="submit">提交</van-button>
        </div>
      </van-form>
   <!-- 数字键盘 -->

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
#register .van-nav-bar__content {
    height: 70px;
  }

  #register .van-nav-bar__title {
    height: 30px;
    line-height: 30px;
    font-size: 25px;

  }

  #register .van-icon:before {
    color: #000;
    font-size: 30px;

  }
 /* register_main */
  #register .van-form {
    margin: 200px 10px;
  }

  #register .van-cell__title,
  .van-field__control {
    font-size: 25px;
  }

</style>
<style scoped>
#register {
    z-index: -1;
    width: 100%;
    background-image: url("../assets/images/5.jpeg");
    background-repeat: no-repeat;
    background-attachment: fixed;
    overflow: hidden;
    background-size: cover;
    min-height: 100vh;
    position: absolute;
    background-position: center;
  }
  .login_tip {
    color: rgb(55, 12, 212);

    margin: 20px 0;
    margin-left: 45%;
    font-size: 20px;
  }

  .van-button__text {
    font-size: 25px;
  }
</style>