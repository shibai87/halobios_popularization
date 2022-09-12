<template>
  <div id="knowledge">
      <van-notice-bar color="#1989fa" background="#ecf9ff" left-icon="info-o" speed=50 text="请通过点击弹出框的确定按钮进行每一级分类的选择！选择完所有分类选项才能进行分类功能！" />
   <Sort></Sort>
           <van-button type="primary" class="knowledge_query" @click=" Knowledge()">开始查询</van-button>
        <van-button type="warning" class="knowledge_query" @click="clear()" v-if="!knowledgeList.length">重新选择分类
        </van-button>
       <van-divider  :style="{ color: '#1989fa', borderColor: '#1989fa'}">以下为科普知识列表</van-divider>
   
   <!-- 分类知识详情 -->
        <div id="knowledge_list" v-if="knowledgeList.length">
            <div v-for="(item,index) in knowledgeList" :key="index">
                <van-row>
                    <van-col span="4">
                        <div class="knowledge_img"><img style="width: 70px; height: 80px;" :src="Image(item)" /></div>
                    </van-col>
                    <van-col span="14" offset=".5">
                        <div class="knowledge_content">
                            <div>
                                <span class="knowledge_title">{{item.knowledge_title}}</span>
                                <van-badge :content="3" class="item">
                                   <van-icon name="like-o" />
                                </van-badge>
                            </div>

                            <div class="knowledge_info">{{item.knowledge_content}}</div>
                        </div>
                    </van-col>
                    <van-col span="6" offset=".5">
                        <div class="knowledge_operation">
                            <van-button @click="comment(item.knowledge_id)"  size="mini">评论列表(待定)</van-button>
                            <van-button type="primary" @click="edit_knowledge(item.knowledge_id)"  size="mini">编辑</van-button>
                            <van-button type="primary" @click="delete_knowledge(item.knowledge_id)"  size="mini">删除</van-button>
                        </div>
                    </van-col>
                </van-row>
            </div>
        </div>
        <div v-else>
            <van-empty  class="custom-image" image="https://img01.yzcdn.cn/vant/custom-empty-image.png" description="无内容,可测试重新选择分类"></van-empty>
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
                this.axios.get("http://192.168.1.103:8083/knowledge/get_knowledge_list?knowledge_upload=" + 1 +
                        "&options_id=" + this
                        .getSortListLastId)
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
            edit_knowledge(knowledge_id) {
                this.$store.commit("judgeKnowledge", true)
                this.$store.commit("getKnowledge", knowledge_id)
                this.$router.push("/editknowledge_detail");
            },
            delete_knowledge(knowledge_id) {
                this.axios.get("http://192.168.1.103:8083/knowledge/delete_knowledge?knowledge_id=" + knowledge_id)
                    .then((res) => {
                        console.log(res.data)
                        if (res.data.msg === "删除成功") {
                            this.$message({
                                message: "删除成功!",
                                type: "success",
                            });
                        } else {
                            this.$message({
                                message: "删除失败!",
                                type: "danger",
                            });
                        }
                        this.reload()
                    });
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
    #knowledge .van-alert {
        margin-bottom: 10px;
    }

    /*详情  */
    #knowledge_list .van-row {
        border: 1px solid rgb(102, 102, 102);
        border-radius: 6px;
        margin: 10px 0;
        width: 380px;

    }

    #knowledge_list .van-col {
        padding: 10px;

    }

    #knowledge_list .van-button {
        display: block;
        width: 50px;
        margin: 5px auto;
        font-size: 10px;
    }
    #knowledge_list .van-badge{
      margin:0 10px 20px ;
    }
</style>
<style scoped>
#knowledge_list{
  font-size: 10px;
}
    /* 分类 */
    .knowledge_query {
        width: 400px;
        margin: 15px auto 0;
    }

    /* 知识列表 */
    #knowledge_list {
        margin-top: 50px;
    }

    .knowledge_img img {
        margin: 10px;
    }

    .knowledge_title {
        height: 50px;
        font-size: 25px;
    }

    .knowledge_info {
        width: 200px;
        display: -webkit-box;
        -webkit-box-orient: vertical;
        -webkit-line-clamp: 3;
        overflow: hidden;
    }

    .knowledge_operation {
        height: 100px;
    }
</style>
