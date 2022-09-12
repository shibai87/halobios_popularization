<template>
  <div id="user_center">
    <!--user_center_head -->
    <van-nav-bar title="个人中心" left-arrow fixed @click-left="pageBack" />
    <!-- user_center_main -->
    <van-row type="flex" justify="space-around">
      <van-col span="6">
        <router-link to="/user_infos">
          <div class="user_center_userName">{{user_name}}</div>
        </router-link>
      </van-col>
      <van-col span="6" offset="4">
        <div @click="log_off"> <button>注销</button></div>
      </van-col>
    </van-row>
    <ul>
      <li>
        <router-link to="/user_infos"><van-icon name="manager-o" />个人信息(基本信息)</router-link>
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
       this.$router.push("/")
      },
      showPopup() {
        this.show = true;
      },
      // 上传图片
      before_read(val) {
        console.log(val,this.fileList)
        this.fileList = val
      },
      afterRead(file) {
        // 此时可以自行将文件上传至服务器
        console.log(file);
      },

      details(val) {
        let user_type;
        if(val==3){
          this.$router.push("/user_comment")
        }else{
          if(val==1){user_type="我的点赞"}else{user_type="我的收藏"}
             localStorage.setItem('user_type', user_type);
        this.$store.commit("chioceUserType", user_type);
          this.$router.push("/user_other_operation")
        }
        
      },
      log_off() {
        localStorage.setItem('judge_login', false);
        localStorage.setItem('user_id', "");
        this.$store.dispatch("updataJudgeLogin", false);
        this.$store.dispatch("updateUserId", );
        this.$router.push("/")
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
  /* user_center_head */
  #user_center .van-nav-bar__content {
    height: 70px;
    border-bottom: 1px solid #000;
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
 
  /*  */
  #user_center .van-row {
    margin: 120px 0 40px;
    font-size: 30px;
  }

  #user_center .van-col--14 {
    height: 100px;
    line-height: 100px;
    text-align: left;
  }
</style>
<style scoped>
  /* 头像 */
  .img_title {
    text-align: center;
  }

  /*  */
  .user_center_userName {
    overflow: hidden;
    width: 200px;
    text-overflow: ellipsis;
    white-space: nowrap;
  }

  ul {
    margin-top: 10px;
  }

  li {
    display: block;
    padding-left: 10px;
    height: 90px;
    line-height: 90px;
    border: 1px solid rgb(187, 183, 183);
  }
</style>