<template>
  <div id="sort_details">
      <div class="sort_details_head">
           <van-nav-bar title="分类科普详情页" fixed>
        <template #right>
          <van-icon name="search" @click="search" />
          <div v-if="judge_login">
            <van-icon class="icon_user" name="https://b.yzcdn.cn/vant/icon-demo-1126.png" @click="user" />
          </div>
          <div v-else class="login" @click="login">
            登录
          </div>
        </template>
      </van-nav-bar>
      </div>
      <div class="sort_details_main">
         
          <h1>{{knowledges[0].knowledge_title}}</h1>
           <img class="sort_details_img" :src="knowledges[0].knowledge_img">
          <div class="sort_details_content">
              {{knowledges[0].knowledge_content}}
          </div>
          <div class="sort_details_knowledge_key">
              关键词:{{knowledges[0].knowledge_key}}
          </div>
          <div class="sort_details_other">
              <van-row>
  <van-col span="8"><van-icon name="good-job-o" :color="good_color" badge="5" @click="changeGoodColor()"/></van-col>
  <van-col span="8"><van-icon name="star-o" :color="star_color" badge="5" /></van-col>
  <van-col span="8"><van-icon name="comment-o" :color="comment_color" badge="5" /></van-col>
</van-row>

          </div>
      </div>
      
  </div>
</template>

<script>
export default {
  name: '',
  data () {
    return {
         knowledges:[
             {
                  knowledge_title:"生物1",
                  knowledge_img:require("../assets/images/1.jpeg"),
                  knowledge_content:"其中动物界的种类最多原核生物界最少我国的海洋生物种类约占全世界海洋生物总种数的10%其中动物界的种类最多原核生物界最少我国的海洋生物种类约占全世界海洋生物总种数的10%",
                  knowledge_key:"浮游生物"
             }
         ],
         good_color:"#ff5000",
         star_color:"#ff5000",
         comment_color:"#ff5000",
      
    }
  },
  methods: {
       search() {
        this.$router.push("/search");
      },
      login() {
        this.$router.push("/login")
      },
      user() {
        this.$router.push("/user")
      },
  },
    computed: {
      judge_login() {
        return this.$store.state.judge_login
      },
    },
  mounted () {
      
  }
}
</script>

<style scoped>
.sort_details_main{
    margin: 1rem;
}
.sort_details_img{
    height: 20%;
    width: 100%;
}
.sort_details_content{
    text-align: left;
    margin: 1rem 0;

}
.sort_details_knowledge_key{
    text-align: left;
}
</style>
