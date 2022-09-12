<template>
  <div id="user_infos">
    <!--user_infos_head  -->
    <van-nav-bar title="个人信息" left-arrow fixed @click-left="pageBack" />

    <van-row>
  <van-col span="4">span: 8</van-col>
  <van-col span="10"> <van-radio-group v-model="radio" direction="horizontal"  label="性别">
  <van-radio name="1">单选框 1</van-radio>
  <van-radio name="2">单选框 2</van-radio>
</van-radio-group></van-col>
 
</van-row>
    <van-form @submit="onSubmit">
      <van-field v-model="info.user_name" type="text" name="用户名" label="用户名" clearable />
      <van-field v-model="info.user_sex" type="text" name="性别" label="性别" clearable >

      </van-field>

      <van-field v-model="info.user_tel" type="tel" name="手机号" label="手机号" clearable />
      <van-field v-model="info.user_email" type="mail" name="邮箱" label="邮箱" clearable />
      <van-field v-model="info.user_pwd" type="password" name="密码" label="密码" clearable :rules="[{  message: '请填写密码' }]" />
      <div style="margin: 16px;">
        <van-button round block type="info" native-type="submit">提交修改</van-button>
      </div>
    </van-form>

  </div>
</template>

<script>
  export default {
    name: 'user_infos',
    data() {
      return {
       
        info: {
          user_name:"",
           user_sex:"",
          user_tel:"",
           user_email:"",
         user_pwd:"",
        }

      }
    },
    methods: {
      pageBack() {
        this.$router.push("/user")
      },
        getloadInfo() {
        console.log(this.getUserId)
        this.axios.get("http://192.168.1.103:8083/user/info?user_id=" + this.getUserId)
          .then((res) => {
            console.log(res.data.user_name)
            this.info = res.data
          })
      },
    
      onSubmit() {

      }
    },
 computed: {
      getUserId() {
        return this.$store.state.user_id
      }
    },
    mounted() {
      this.getloadInfo()
    },

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
  #user_infos {
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

  .van-form {

    margin-top: 120px;
  }
</style>