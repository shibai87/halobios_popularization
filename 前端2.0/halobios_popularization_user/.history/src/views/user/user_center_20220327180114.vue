<template>
  <div id="user_center">
    <!--user_center_head -->
    <van-nav-bar title="个人中心" left-arrow fixed @click-left="pageBack" />
    <!-- user_center_main -->
    <van-row>
      <van-col span="4" offset="2">
        <van-image round width="100px" height="100px" src="https://img01.yzcdn.cn/vant/cat.jpeg" @click="showPopup"/>
      <van-popup v-model="show" round>
        <div class="img_title">上传图片</div>
        <van-uploader v-model="fileList" multiple :max-count="1" accept="image/*" :before-read="before_read" />
      </van-popup>
      </van-col>
      <van-col span="7" offset="4">
        <router-link to="/user_infos">
        <div class="user_center_userName">{{user_name}}</div>
        </router-link>
      </van-col>
      <van-col span="6" offset="1">
        <div @click="log_off"> <button>注销</button></div>
      </van-col>
    </van-row>
    <ul>

      <li>
        <router-link to="/user_infos">
          <van-icon name="manager-o" />个人信息
        </router-link>

      </li>

      <li @click="details(1)">
        <van-icon name="like-o" />我的点赞
      </li>
      <li @click="details(2)">
        <van-icon name="star-o" />我的收藏</li>
      <li @click="details(3)">
        <van-icon name="comment-o" />我的评论</li>
      <li>
        <router-link to="/user_notice">
          <van-icon name="comment-circle-o" />审核与举报
        </router-link>
      </li>
    </ul>




  </div>

</template>

<script>
  export default {
    name: 'user_center',
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
        user_name: ""
      }
    },
    methods: {
      getloadInfo() {
        console.log(this.getUserId)
        this.axios.get("http://192.168.1.103:8083/user/info?user_id=" + this.getUserId)
          .then((res) => {
            console.log(res.data.user_name)
            this.user_name = res.data.user_name
          })
      },
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

      details(user_type) {
        this.$store.commit("chioceUserType", user_type);
      },
      log_off(){
          localStorage.setItem('judge_login', false);
                localStorage.setItem('user_id',"");
         this.$store.dispatch("updataJudgeLogin",false);
               this.$store.dispatch("updateUserId",);
               this.$router.push("/")
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
  /* user_center_head */
  #user_center .van-nav-bar__content {
    height: 70px;
  }

  #user_center .van-nav-bar__title {
    height: 30px;
    line-height: 30px;
    font-size: 25px;

  }

  #user_center .van-icon:before {
    color: #000;
    font-size: 35px;
  }

  /*  */
  /* 头像 */
   #user_center .van-popup{
    height: 300px;
    width: 300px;
  }
  .van-uploader{
    margin: 10px;
    width: 300px;
  }

  /*  */
   #user_center  .van-row {
    margin-top: 100px;
  }

   #user_center .van-col--14 {
    height: 100px;
    line-height: 100px;
    text-align: left;
  }
</style>
<style scoped>
/* 头像 */
.img_title{
  text-align: center;
}
  /*  */
  .user_center_userName {
    overflow: hidden;
    width: 100px;
    text-overflow: ellipsis;
    white-space: nowrap;
  }

  ul {
    margin-top: 10px;
  }

  li {
    display: block;
    height: 100px;
    line-height: 100px;

    border: 1px solid rgb(187, 183, 183);
  }
</style>