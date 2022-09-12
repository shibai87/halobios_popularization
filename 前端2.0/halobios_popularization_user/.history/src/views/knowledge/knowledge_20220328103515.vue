<template>
  <div id="knowledge">
   <Sort></Sort>
           <van-button type="primary" class="knowledge_query" @click=" Knowledge()">开始查询</van-button>
        <van-button type="warning" class="knowledge_query" @click="clear()" v-if="!knowledgeList.length">重新选择分类
        </van-button>
       <van-divider  :style="{ color: '#1989fa', borderColor: '#1989fa'}">以下为科普知识列表</van-divider>
   
   <!-- 分类知识详情 -->
        <div id="knowledge_list" v-if="knowledgeList.length">
            <div v-for="(item,index) in knowledgeList" :key="index">
                <van-row>
                    <van-col :span="4">
                        <div class="knowledge_img"><img style="width: 130px; height: 130px;" :src="Image(item)" /></div>
                    </van-col>
                    <van-col :span="14" :offset=".5">
                        <div class="knowledge_content">
                            <div>
                                <span class="knowledge_title">{{item.knowledge_title}}</span>
                                <van-badge :value="3" class="item">
                                   <van-icon name="like-o" />
                                </van-badge>
                            </div>

                            <div class="knowledge_info">{{item.knowledge_content}}</div>
                        </div>
                    </van-col>
                    <van-col :span="6" :offset=".5">
                        <div class="knowledge_operation">
                            <van-button @click="comment(item.knowledge_id)">评论列表(待定)</van-button>
                            <van-button type="primary" @click="edit_knowledge(item.knowledge_id)">编辑</van-button>
                            <van-button type="primary" @click="delete_knowledge(item.knowledge_id)">删除</van-button>
                        </div>
                    </van-col>
                </van-row>
            </div>
        </div>
        <div v-else>
            <van-empty  class="custom-image" image="https://img01.yzcdn.cn/vant/custom-empty-image.png" description="无内容,可测试重新选择分类"></van-empty>
        </div>

<!-- 
     <div class="sort_info" v-for="(knowledge,index) in knowledges" :key="index" @click="changeSortDetails(knowledge.knowledge_id)">
      <img class="sort_left" :src="knowledge.knowledge_img">
      
      <div class="sort_right">
        <div class="sort_right_title">{{knowledge.knowledge_title}}</div>
         <div class="sort_right_content">{{knowledge.knowledge_content}}</div>
      </div>
    </div>
    -->
   
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
        // knowledges:[
        //   {
        //     knowledge_id:1,
        //     knowledge_title:"生物1",
        //     knowledge_img:require("../../assets/images/1.jpeg"),
        //     knowledge_content:"其中动物界的种类最多种原核生物界最少我国的海洋生物种类约占全世界海洋生物总种数的10%其中动物界的种类最多原核生物界最少我国的海洋生物种类约占全世界海洋生物总种数的10%"
        //   },
        //    {
        //     knowledge_id:2,
        //     knowledge_title:"生物2",
        //     knowledge_img:require("../../assets/images/1.jpeg"),
        //     knowledge_content:"其中动物界的种类最多原核生物界最少我国的海洋生物种类约占全世界海洋生物总种数的10%其中动物界的种类最多原核生物界最少我国的海洋生物种类约占全世界海洋生物总种数的10%"
        //   },
        //    {
        //     knowledge_id:3,
        //     knowledge_title:"生物3",
        //     knowledge_img:require("../../assets/images/1.jpeg"),
        //     knowledge_content:"其中动物界的种类最多原核生物界最少我国的海洋生物种类约占全世界海洋生物总种数的10%其中动物界的种类最多原核生物界最少我国的海洋生物种类约占全世界海洋生物总种数的10%"
        //   },
        //    {
        //     knowledge_id:4,
        //     knowledge_title:"生物4",
        //     knowledge_img:require("../../assets/images/1.jpeg"),
        //     knowledge_content:"其中动物界的种类最多种原核生物界最少我国的海洋生物种类约占全世界海洋生物总种数的10%其中动物界的种类最多原核生物界最少我国的海洋生物种类约占全世界海洋生物总种数的10%"
        //   },
        //    {
        //     knowledge_id:5,
        //     knowledge_title:"生物5",
        //     knowledge_img:require("../../assets/images/1.jpeg"),
        //     knowledge_content:"其中动物界的种类最多原核生物界最少我国的海洋生物种类约占全世界海洋生物总种数的10%其中动物界的种类最多原核生物界最少我国的海洋生物种类约占全世界海洋生物总种数的10%"
        //   },
        //    {
        //     knowledge_id:6,
        //     knowledge_title:"生物6",
        //     knowledge_img:require("../../assets/images/1.jpeg"),
        //     knowledge_content:"其中动物界的种类最多原核生物界最少我国的海洋生物种类约占全世界海洋生物总种数的10%其中动物界的种类最多原核生物界最少我国的海洋生物种类约占全世界海洋生物总种数的10%"
        //   },
        // ]


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
    #knowledge .el-alert {
        margin-bottom: 10px;
    }

    /*详情  */
    #knowledge_list .el-row {
        border: 1px solid rgb(102, 102, 102);
        border-radius: 6px;
        margin: 20px auto;
        width: 1000px;

    }

    #knowledge_list .el-col {
        padding: 10px;

    }

    #knowledge_list .el-button {
        display: block;
        width: 150px;
        margin: 10px auto;


    }
    #knowledge_list .el-badge{
        font-size: 35px;
      margin:0 10px 20px ;
    }
</style>
<style scoped>