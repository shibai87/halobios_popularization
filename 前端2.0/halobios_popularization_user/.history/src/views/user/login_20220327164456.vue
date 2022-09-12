// 登录问题
// 1、键盘
<template>
  <div id="login">
    <!--login_head -->
    <van-nav-bar title="登录" left-arrow fixed @click-left="pageBack" />
    <!-- login_main -->
    <van-pull-refresh v-model="isLoading" @refresh="onRefresh">

      <van-form @submit="onSubmit">
        <van-field class="keyboard" v-model="user_tel" type="tel" name="手机号" label="手机号" placeholder="请输入手机号" clearable
          required :rules="[{ validator, message: '手机号格式错误' }]" />
        <van-field v-model="user_pwd" type="password" name="密码" label="密码" placeholder="请输入密码" clearable required
          :rules="[{ required: true, message: '请填写密码' }]" />
        <div class="login_tip">
          <router-link to="/register">未注册，请点击这里</router-link>
        </div>

        <div style="margin: 16px;">
          <van-button round block type="info" native-type="submit">提交</van-button>
        </div>
      </van-form>

    </van-pull-refresh>
  </div>
</template>

<script>
  import {
    Toast
  } from 'vant';
  import {
    Dialog
  } from 'vant';
  export default {
    name: 'login',
  inject: ["reload"],
    data() {
      return {
        // 刷新
        isLoading: false,

        user_tel: '',
        user_pwd: '',
      }
    },
    methods: {
      // 刷新
      onRefresh() {
        setTimeout(() => {
          Toast('刷新成功');
          this.isLoading = false;
         this.reload()
        }, 1000);
      },
      // 返回上一级
      pageBack() {
       this.$router.push("/")
      },
      // 校验函数返回 true 表示校验通过，false 表示不通过
      validator(val) {
        return /^1(3|4|5|6|7|8)\d{9}$/.test(val);
      },
      // 提交登录表单
      onSubmit() {
        this.axios
          .get("http://192.168.1.103:8083/user/login?user_tel=" + this.user_tel + "&user_pwd=" + this.user_pwd)
          .then((res) => {
            console.log("登录提示:");
            console.log(res.data);
           
            if (res.data.msg =='登录成功') {
               Dialog.alert({
                title: '登录成功',
                message: '恭喜你，登录成功!',
              }).then(() => {
                // on close
              });
              this.$store.commit("judgeLogin",true)
              //  this.$store.dispatch("updataJudgeLogin",true);
               this.$store.dispatch("updateUserId", res.data.data);
              
                this.$router.push("/");
            } else {
              Dialog.alert({
                title: '登录失败',
                message: '登录失败,用户名或密码错误!',
              }).then(() => {
                // on close
              });
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

    },
   
  }
</script>
<style>
  /* login_head */
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
    font-size: 35px;

  }

  /* login_main */
  #login .van-form {
    margin: 200px 10px;
  }

  #login .van-cell__title,
  .van-field__control {
    font-size: 20px;
  }

  #login .van-cell__title {
    width: 100px;
  }
</style>
<style scoped>
  #login {
    z-index: -1;
    width: 100%;
    background-image: url("../../assets/images/5.jpeg");
    background-repeat: no-repeat;
    background-attachment: fixed;
    overflow: hidden;
    background-size: cover;
    min-height: 100vh;
    position: absolute;
    background-position: center;
  }

  .login_tip {
    margin: 20px 0;
    text-align: right;
    font-size: 20px;
  }

  .login_tip a {
    color: rgb(85, 212, 12);
  }

  .van-button__text {
    font-size: 25px;
  }
</style>