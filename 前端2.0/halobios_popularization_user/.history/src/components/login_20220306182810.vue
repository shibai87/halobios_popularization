<template>
  <div id="login">
    <!--login_head -->
    <van-nav-bar title="登录" left-arrow fixed @click-left="pageBack" />

    <!-- login_main -->
    <van-form @submit="onSubmit">
      <van-field v-model="user_tel" type="tel" name="手机号" label="手机号" placeholder="请输入手机号" clearable required
        :focused="showKeyboard_tel" @focus="showKeyboard_tel = true"
        :rules="[{ validator, message: '号码格式错误' }]" />
      <!-- <van-number-keyboard v-model="tel" :show="showKeyboard_tel" @blur="showKeyboard_tel = false" /> -->
      <van-field v-model="user_pwd" type="password" name="密码" label="密码" placeholder="请输入密码" clearable required
        :focused="showKeyboard_pwd" @focus="showKeyboard_pwd = true" :rules="[{ required: true, message: '请填写密码' }]" />
      <!-- <van-number-keyboard v-model="password" :show="showKeyboard_pwd" @blur="showKeyboard_pwd = false" /> -->

          <div  class="login_tip"><router-link to="/register">未注册，请点击这里</router-link></div>

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
        user_tel: '',
        user_pwd: '',
        showKeyboard_tel: false,
        showKeyboard_pwd: false,


      }
    },
    methods: {
      // 返回上一级
      pageBack() {
        window.history.back()
      },
      // 测试代码
      change_login() {
        this.$store.commit("judgeLogin", true)
      },
// 校验函数返回 true 表示校验通过，false 表示不通过
 validator(val) {
      return /^1(3|4|5|6|7|8)\d{9}$/.test(val);
    },
      // 
      onSubmit() {
        this.axios
        .get("http://localhost:8080/user/login?user_tel="+this.user_tel+"&user_pwd="+this.user_pwd
            )
          .then((res) => {
             console.log("登录提示:");
              console.log(res.data);
              if (res.data.code === 0) {
                this.$message({
                  message: "恭喜你，登录成功!",
                  type: "success",
                });

            //     localStorage.setItem("st_id", res.data.data);
            //     this.$store.dispatch("SET_ST_ID", res.data.data);

            //     this.$router.push("/st_course");
            //   } else {
            //     this.$message({
            //       message: "登录失败,用户名或密码错误!",
            //       type: "danger",
            //     });
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
  #login .van-cell__title{
    width: 100px;
  }
</style>
<style scoped>
#login{
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
    margin: 20px 0;
    text-align: right;
    font-size: 20px;
  }
  .login_tip a{
    color: rgb(85, 212, 12);
  }

  .van-button__text {
    font-size: 25px;
  }
</style>