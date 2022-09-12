<template>
  <div id="user_infos">
    <!--user_infos_head  -->
    <van-nav-bar title="个人信息" left-arrow fixed @click-left="pageBack" />

 <van-field v-model="info.user_name" label="用户名" placeholder="请输入用户名" />


  
  </div>
</template>

<script>
  export default {
    name: 'user_infos',
    data() {
      return {
  
        info: {
          user_name: "",
          user_sex: "",
          user_tel: "",
          user_email: "",
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

  .van-cell{
  margin-top: 200px;
}
 .sex{
  background-color: #fff;
  height: 50px;
}
/*.sex_title{
  display: inline-block;
  width: 20px;
} */
</style>