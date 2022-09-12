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
          <h1>{{knowledges.knowledge_title}}</h1>
          <div>
              {{knowledges.knowledge_content}}
          </div>
          <div>
              关键词:{{knowledges.knowledge_key}}
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
                  knowledge_content:"其中动物界的种类最多原核生物界最少我国的海洋生物种类约占全世界海洋生物总种数的10%其中动物界的种类最多原核生物界最少我国的海洋生物种类约占全世界海洋生物总种数的10%"，
                  knowledge_key:"浮游生物"
             }
         ]
      
    }
  },
  methods: {

  },
  mounted () {

  }
}
</script>

<style scoped>

</style>
