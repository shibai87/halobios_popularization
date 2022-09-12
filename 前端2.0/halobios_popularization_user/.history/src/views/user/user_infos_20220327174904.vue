<template>
  <div id="user_infos">
    <!--user_infos_head  -->
    <van-nav-bar title="个人信息" left-arrow fixed @click-left="pageBack" />

    <!--  -->
    <van-form @submit="onSubmit">

      <van-image
  width="100px"
  height="100px"
  fit="contain"
  :src="info.image"
/>
    <van-button  round block native-type="submit" @click="showPopup">上传图片</van-button>
    <van-popup v-model="show" round>
      <van-uploader v-model="fileList" multiple :max-count="1" accept="image/*" :before-read="before_read" />
    </van-popup>

      <van-field v-model="info.name" type="text" name="用户名" label="用户名" clearable />
      <van-field v-model="info.sex" type="text" name="性别" label="性别" clearable />
      <van-field v-model="info.tel" type="tel" name="手机号" label="手机号" clearable />
      <van-field v-model="info.mail" type="mail" name="邮箱" label="邮箱" clearable />
      <van-field v-model="info.pwd" type="password" name="密码" label="密码" clearable :rules="[{  message: '请填写密码' }]" />
      <div style="margin: 16px;">

        <van-button round block type="info" native-type="submit">提交修改</van-button>
      </div>
    </van-form>




  </div>
</template>

<script>
  export default {
    name: '',
    data() {
      return {
        show: false,
        fileList: [{
            url: 'https://img01.yzcdn.cn/vant/leaf.jpg'
          },
          // Uploader 根据文件后缀来判断是否为图片文件
          // 如果图片 URL 中不包含类型信息，可以添加 isImage 标记来声明
          // { url: 'https://cloud-image', isImage: true },
        ],
        info: {
          name: "dac",
          tel: "18128034110",
          pwd: "111",
          image: require("../../assets/images/1.jpeg"),
          mail: "553395947@qq.com",
          sex: "女"
        }

      }
    },
    methods: {
      pageBack() {
        window.history.back()
      },
      showPopup() {
        this.show = true;
      },
      // 上传图片
      before_read(val) {
        this.fileList = val
      },
      onSubmit() {

      }
    },
    mounted() {

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