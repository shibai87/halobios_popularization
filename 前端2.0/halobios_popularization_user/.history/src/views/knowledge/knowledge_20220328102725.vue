<template>
  <div id="knowledge">
   <Sort></Sort>
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
                                <el-badge :value="3" class="item">
                                    <i class="el-icon-star-off"></i>
                                </el-badge>
                            </div>

                            <div class="knowledge_info">{{item.knowledge_content}}</div>
                        </div>
                    </van-col>
                    <van-col :span="6" :offset=".5">
                        <div class="knowledge_operation">
                            <el-button @click="comment(item.knowledge_id)">评论列表(待定)</el-button>
                            <el-button type="primary" @click="edit_knowledge(item.knowledge_id)">编辑</el-button>
                            <el-button type="primary" @click="delete_knowledge(item.knowledge_id)">删除</el-button>
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
     
    },
    mounted() {
     
    }
  }
</script>

<style scoped>
/* sort_head */
.sort1{
  height: 200px;
 
  
}

  /* sort_info */
   .sort_info{
    height: 100px;
   width: 370px;
    border: 1px solid #000;
  }
  .sort_left{
    display: inline-block;
     text-align:left;
    height: 80px;
    margin: 10px;
    width: 100px;
   
  }
  .sort_right{
    display: inline-block;
   width: 200px;
    margin-left: 10px;
   
    

  }
   .sort_right_title{
    text-align: center;
  }
   .sort_right_content{
      text-align: right;
    display: -webkit-box;
-webkit-box-orient: vertical;
-webkit-line-clamp: 2;
overflow: hidden;
  }
</style>