<template>
  <div id="knowledge">
    <div id="knowledge_top">
          <van-notice-bar color="#1989fa" background="#ecf9ff" left-icon="info-o" speed=50 text="请通过点击弹出框的确定按钮进行每一级分类的选择！选择完所有分类选项才能进行分类功能！" />
   <Sort></Sort>
           <van-button type="primary" class="knowledge_query" @click="Knowledge"  size="mini">开始查询</van-button>
        <van-button type="warning" class="knowledge_query" @click="clear()" v-if="!knowledgeList.length">重新选择分类
        </van-button>
       <van-divider  :style="{ color: '#1989fa', borderColor: '#1989fa'}">以下为科普知识列表</van-divider>
    </div>
   <!-- 分类知识详情 -->
        <div id="knowledge_list" v-if="knowledgeList.length">
            <div v-for="(item,index) in knowledgeList" :key="index">
                <van-row>
                    <van-col span="4">
                        <div class="knowledge_img"><img style="width: 70px; height: 80px;" :src="Image(item)" /></div>
                    </van-col>
                    <van-col span="10" offset="2">
                        <div class="knowledge_content">
                            <span class="knowledge_title">{{item.knowledge_title}}</span>
                            <div class="knowledge_info">{{item.knowledge_content}}</div>
                        </div>
                    </van-col>
                    <van-col span="4" offset="3">
                      <div class="knowledge_operation">
  <van-button  type="primary" @click="knowledge(item.knowledge_id)"  size="mini">编辑</van-button>
                      </div>
                          
                    </van-col>
                </van-row>
            </div>
        </div>
        <div v-else  id="knowledge_list" >
          <van-empty image="error" description="描述文字" />
            <!-- <van-empty  class="custom-image" image="https://img01.yzcdn.cn/vant/custom-empty-image.png" description="无内容,可测试重新选择分类"></van-empty> -->
        </div>
  </div>
</template>

<script>
import Sort from "../../components/sort.vue"
  export default {
    name: 'knowledge',
    components:{
      Sort
    },
    data() {
      return {
         knowledgeList: [],
      }
    },
        methods: {
            // 获取知识列表+查询列表
            getLoadKnowledgeList() {
                this.axios.get("http://192.168.1.103:8083/knowledge/get_knowledge_list?knowledge_upload=" + 1 +
                        "&options_id=")
                    .then((res) => {
                        // console.log(res.data)
                        this.knowledgeList = res.data
                    })
            },
            Knowledge() {
              console.log(this.getSortListLastId)
                this.axios.get("http://192.168.1.103:8083/knowledge/get_knowledge_list?knowledge_upload=" + 1 +
                        "&options_id=" + this.getSortListLastId)
                    .then((res) => {
                        // console.log(res.data)
                        this.knowledgeList = res.data
                    })
            },
            clear() {
                this.$store.commit("getSortIdListLast", "")
                this.getLoadKnowledgeList()
                this.reload();
            },
            // 编辑
            knowledge(knowledge_id) {
                this.$store.commit("judgeKnowledge", true)
                this.$store.commit("getKnowledge", knowledge_id)
                this.$router.push("/editknowledge_detail");
            },
        

            //图片路径
            Image(row) {
                var url = "http://192.168.1.103:8083/knowledge/" + row.knowledge_img
                console.log(url)
                return url
            },


        },
        computed: {
            getSortListLastId() {
                return JSON.parse(localStorage.getItem('sort_id_list_last'));
            },
        },

        mounted() {

            this.getLoadKnowledgeList();
        }
  }
</script>
<style>
    /*  */
    #knowledge .van-notice-bar{
       height: 20px;
    }
     #knowledge .van-notice-bar .van-icon::before{
      font-size:20px;
    }
   
    /*详情  */
    #knowledge_list .van-row {
        border: 1px solid rgb(102, 102, 102);
        border-radius: 6px;
        width: 385px;
        margin: 5px;
    }
    #knowledge_list .van-col {
        padding: 10px;
    }
    #knowledge_list .van-button {
      display: inline-block;
       height: 30px;
        width: 60px;
        font-size: 20px;
        vertical-align:middle;
    }
    #knowledge_top .van-divider{
      margin: 5px 0;
    }
    /*  */
    #knowledge_list .van-empty__image{
      width: 370px;
    }
   #knowledge_list .van-empty__image img{
     
    }
</style>
<style scoped>
    /* 分类 */
    #knowledge_top{
      background-color: #fff;
      z-index: 1;
      position: fixed;
      top:180px
    }
    .knowledge_query {
      display: block;
      height: 15px;
        width: 400px;
        margin: 5px 0;
       
    }

    /* 知识列表 */
    #knowledge_list{
     margin-top: 160px;
    }
    .knowledge_img img {
        margin: 10px;
    }

    .knowledge_title {
        height: 50px;
        font-size: 20px;
    }

    .knowledge_info {
        width: 200px;
        font-size: 15px;
        display: -webkit-box;
        -webkit-box-orient: vertical;
        -webkit-line-clamp: 3;
        overflow: hidden;
    }
    .knowledge_operation{
      height: 100px;
      line-height: 100px;
    }
    
</style>
