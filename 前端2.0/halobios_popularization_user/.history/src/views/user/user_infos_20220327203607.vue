<template>
  <div id="user_infos">
    <!--user_infos_head  -->
    <van-nav-bar title="个人信息" left-arrow fixed @click-left="pageBack" />

    <van-field v-model="info.user_name" label="用户名" placeholder="请输入用户名" class="name"/>
    <van-field v-model="info.user_tel" type="tel" label="手机号"  placeholder="请输入用户名"/>
    <van-field v-model="info.user_pwd" type="password" label="密码" />
    <van-row class="sex">
      <van-col span="6"><div class="sex_title">性别</div></van-col>
      <van-col span="18">
        <van-radio-group v-model="radio" direction="horizontal">
          <van-radio name="1">单选框 1</van-radio>
          <van-radio name="2">单选框 2</van-radio>
        </van-radio-group>
      </van-col>
    </van-row>




  </div>
</template>

<script>
  export default {
    name: 'user_infos',
    data() {
      return {
        radio: '1',
        info: {
          user_name: "",
          user_sex: "",
          user_tel: "",
          user_pwd: "",
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
// 邮箱
 formatter(val) {
      const reg = /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/;

      if (!val) return '';
      // return reg.test(val);
      if (!reg.test(val)) {
        this.errorText = '请输入正确的邮箱';
      } else {
        this.errorText = '';
      }
      return val;
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
.van-cell__title{
  font-size: 24px;
  color: #000;
}

  /*  */
  .van-radio-group--horizontal {
    height: 50px;
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

  /*  */
  .name{
  margin-top: 200px;
  }
/* 性别 */
  .sex {
    background-color: #fff;
    height: 50px;
  }

  .sex_title {
    padding: 10px;
    font-size: 26px;
  }
</style>