<template>
  <div id="register">
    <!-- register_head -->
    <van-nav-bar title="注册" left-arrow fixed @click-left="pageBack" />

    <!-- register_main -->
    <van-form @submit="onSubmit">
      <van-field v-model="user_name" type="text" name="用户名" label="用户名" placeholder="请输入用户名" clearable required
        :focused="showKeyboard_tel" @focus="showKeyboard_tel = true" :rules="[{ required: true, message: '请输入用户名' }]" />
      <van-field v-model="user_tel" type="tel" name="手机号" label="手机号" placeholder="请输入手机号" clearable required
        :focused="showKeyboard_tel" @focus="showKeyboard_tel = true" :rules="[{ validator, message: '号码格式错误'  }]" />
      <!-- <van-number-keyboard  v-model="tel"  :show="showKeyboard_tel" @blur="showKeyboard_tel = false" /> -->
      <van-field v-model="user_pwd" type="password" name="密码" label="密码" placeholder="请输入密码" clearable required
        :focused="showKeyboard_pwd" @focus="showKeyboard_pwd = true" :rules="[{ required: true, message: '请填写密码' }]" />
      <!-- <van-number-keyboard  v-model="password"  :show="showKeyboard_pwd" @blur="showKeyboard_pwd = false" /> -->
      <div style="margin: 16px;">
        <van-button round block type="info" native-type="submit">提交</van-button>
      </div>
    </van-form>
    <!-- 数字键盘 -->

  </div>
</template>

<script>
  import {
    Dialog
  } from 'vant';

  export default {
    name: 'login',
    components: {

    },
    data() {
      return {
        user_name: "",
        user_tel: '',
        user_pwd: '',
        showKeyboard_tel: false,
        showKeyboard_pwd: false,


      }
    },
    methods: {
      pageBack() {
        window.history.back()
      },

      // 
      onSubmit() {
        this.axios
          .get("http://localhost:8083/user/register?user_name=" + this.user_name +
            "&user_tel=" + this.user_tel + "&user_pwd=" + this.user_pwd)
          .then((res) => {
            console.log("注册提示:");
            console.log(res.data);
            if (res.data.data === 0) {
              Dialog.alert({
                message: '注册失败，请重新输入！',
              })
            } else if (res.data.data === 1) {
              Dialog.alert({
                message: '注册成功',
              }).then(() => {
                this.$router.push("/login");
              });

            } else if (res.data.data === 2) {
              Dialog.alert({
                message: '该号码已注册！',
              })
            }
          })
          .catch(() => {
            // err
          });
      },


    },
    computed: {

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
    font-size: 35px;

  }

  /* register_main */
  #register .van-form {
    margin: 200px 10px;
  }

  #register .van-cell__title{
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