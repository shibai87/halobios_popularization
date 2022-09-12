<template>
  <div id="user_infos">
      <!--user_infos_head  -->
      <van-nav-bar title="个人信息" left-arrow fixed @click-left="pageBack" />

      <!--  -->
      <van-form @submit="onSubmit">
          <van-field v-model="info.name"  type="text"  name="用户名" label="用户名" :placeholder="info.name" clearable required
        :focused="showKeyboard_tel" @focus="showKeyboard_tel = true"
        :rules="[{ required: false, message: '请输入用户名' }]" />
      <van-field v-model="info.tel" type="tel" name="手机号" label="手机号" placeholder="请输入手机号" clearable required
        :focused="showKeyboard_tel" @focus="showKeyboard_tel = true"
        :rules="[{ required: false, message: '请输入手机号' }]" />
      <van-number-keyboard v-model="tel" :show="showKeyboard_tel" @blur="showKeyboard_tel = false" />
      <van-field v-model="password" type="password" name="密码" label="密码" placeholder="请输入密码" clearable required
        :focused="showKeyboard_pwd" @focus="showKeyboard_pwd = true" :rules="[{ required: false, message: '请填写密码' }]" />
      <van-number-keyboard v-model="password" :show="showKeyboard_pwd" @blur="showKeyboard_pwd = false" />
      <router-link to="/register">
        <div class="login_tip">
          未注册，请点击这里
        </div>
      </router-link>

      <div style="margin: 16px;">
        <van-button round block type="info" native-type="submit">提交</van-button>
      </div>
    </van-form>
          
  


  </div>
</template>

<script>
export default {
  name: '',
  data () {
    return {
     info:
         {
             name:"dac",
             tel:"18128034110",
             pwd:"111"

         }
     
    }
  },
  methods: {
      pageBack() {
        window.history.back()
      },
  },
  mounted () {

  }
}
</script>
<style>
 /* user_infos_head */
  #user_infos .van-nav-bar__content {
    height: 70px;
  }

  #user_infos .van-nav-bar__title {
    height: 30px;
    line-height: 30px;
    font-size: 25px;

  }

  #user_infos .van-icon:before {
    color: #000;
    font-size: 35px;
  }

</style>
<style scoped>

</style>
