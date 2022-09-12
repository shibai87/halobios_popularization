<template>
  <div id="user_center">
    <!--user_center_head -->
    <van-nav-bar title="个人中心" left-arrow fixed @click-left="pageBack" />
    <!-- user_center_main -->
  
         <!-- user_info1 -->
    <van-row>
      <van-col span="4"  offset="2" >
        <van-image round width="100px" height="100px" src="https://img01.yzcdn.cn/vant/cat.jpeg" />
      </van-col>
      <van-col span="14"  offset="4" >sdfdg</van-col>

    </van-row>
  <ul>
    
      <li >
        <router-link to="/user_infos">
 <van-icon name="manager-o"/>个人信息
        </router-link>
       
      </li>
      <li @click="details(thumbs-up)">
<van-icon name="like-o"/>我的点赞
      </li>
      <li  @click="details(Collection)"><van-icon name="star-o"/>我的收藏</li>
      <li  @click="details(comment)"><van-icon name="comment-o"/>我的评论</li>
      <li  >
        <router-link to="/user_notice">
<van-icon name="comment-circle-o"/>审核与举报
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
        msg: ''
      }
    },
    methods: {
      pageBack() {
        window.history.back()
      },
      details(user_type){
        window.alert(user_type);
        this.$store.commit("chioceUserType", user_type)
      }
    },
    mounted() {
      
    }
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
  .van-row{
    margin-top: 100px;
  }
  .van-col--14{
    height:100px;
    line-height:100px;
    text-align: left;
  }
</style>
<style scoped>

ul{
 margin-top: 10px;
}
 li{
   display: block;
   height: 100px;
   line-height: 100px;
  
   border: 1px solid rgb(187, 183, 183);
 }
</style>