<template>
  <div id="user_infos">
    <!--user_infos_head  -->
    <van-nav-bar title="个人信息" left-arrow fixed @click-left="pageBack" />
       <van-pull-refresh v-model="isLoading" @refresh="onRefresh">
    <van-field v-model="user_name" label="用户名" placeholder="请输入用户名" class="name"  clearable/>
    <van-field v-model="user_tel" type="tel" label="手机号"  placeholder="请输入手机号码" disabled/>
    <van-field v-model="user_pwd" type="password" label="密码"   clearable/>
    <van-row class="sex">
      <van-col span="6"><div class="sex_title">性别</div></van-col>
      <van-col span="18">
        <van-radio-group v-model="user_sex" direction="horizontal">
          <van-radio name="0">男</van-radio>
          <van-radio name="1">女</van-radio>
        </van-radio-group>
      </van-col>
    </van-row>
  </van-pull-refresh>
  <van-button type="primary" size="large" @click="onSubmit">提交</van-button>


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
    name: 'user_infos',
       inject: ["reload"],
    data() {
      return {
             // 刷新
        isLoading: false,
       
          user_name: "",
          user_sex: "",
          user_tel: "",
          user_pwd: "",
     

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
            console.log(res.data)
            this.user_name = res.data.user_name
            this.user_sex = res.data.user_sex
            this.user_tel = res.data.user_tel
            this.user_pwd = res.data.user_pwd
          })
      },
        // 刷新
      onRefresh() {
        setTimeout(() => {
          Toast('刷新成功');
          this.isLoading = false;
         this.reload()
        }, 1000);
      },
      onSubmit() {
        console.log(this.user_name)
          this.axios
          .get("http://192.168.1.103:8083/user/updateUserInfo?user_name=" + this.user_name 
                            + "&user_pwd=" +this.user_pwd+ "&user_sex=" + this.user_sex+ "&user_id=" + this.getUserId)
          .then((res) => {
            console.log("登录提示:");
            console.log(res.data);
            let user_id = res.data.data
            if (res.data.msg == '修改成功') {
              Dialog.alert({
                title: '修改成功',
                message: '恭喜你，修改成功!',
              }).then(() => {
                localStorage.setItem('judge_login', true);
                localStorage.setItem('user_id', user_id);
                this.$store.dispatch("updataJudgeLogin", true);
                this.$store.dispatch("updateUserId", user_id);
                this.$router.push("/");
              });

            } else {
              Dialog.alert({
                title: '修改失败',
                message: '修改失败!',
              }).then(() => {
                // on close
              });
            }
          })
          .catch(() => {
            // err
          });

      }
    },
    computed: {
      getUserId() {
        return JSON.parse(localStorage.getItem('user_id'));
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