<template>
  <div id="sort">
    <!-- head -->
    <div id="sort_head">
      <div class="sort" v-for="(sort,index) in sorts" :key="index" @click="change_sort(sort.sort_id)">
        {{sort.sort_type_name}}
      </div>
    </div>
   
     <div class="sort_info" v-for="(knowledge,index) in knowledges" :key="index" @click="changeSortDetails(knowledge.knowledge_id)">
      <img class="sort_left" :src="knowledge.knowledge_img">
      
      <div class="sort_right">
        <div class="sort_right_title">{{knowledge.knowledge_title}}</div>
         <div class="sort_right_content">{{knowledge.knowledge_content}}</div>
      </div>
    </div>
   
   
  </div>
</template>

<script>
  export default {
    name: 'sort',
    data() {
      return {
        sorts: [{
            sort_id: 1,
            sort_type_name: "浮游生物"
          },
          {
            sort_id: 2,
            sort_type_name: "浮游生物"
          },
          {
            sort_id: 3,
            sort_type_name: "浮游生物"
          },
          {
            sort_id: 4,
            sort_type_name: "浮游生物"
          },
          {
            sort_id: 5,
            sort_type_name: "浮游生物"
          },
          {
            sort_id: 6,
            sort_type_name: "浮游生物"
          },
        ],
        knowledges:[
          {
            knowledge_id:1,
            knowledge_title:"生物1",
            knowledge_img:require("../assets/images/1.jpeg"),
            knowledge_content:"其中动物界的种类最多种原核生物界最少我国的海洋生物种类约占全世界海洋生物总种数的10%其中动物界的种类最多原核生物界最少我国的海洋生物种类约占全世界海洋生物总种数的10%"
          },
           {
            knowledge_id:2,
            knowledge_title:"生物2",
            knowledge_img:require("../assets/images/1.jpeg"),
            knowledge_content:"其中动物界的种类最多原核生物界最少我国的海洋生物种类约占全世界海洋生物总种数的10%其中动物界的种类最多原核生物界最少我国的海洋生物种类约占全世界海洋生物总种数的10%"
          },
           {
            knowledge_id:3,
            knowledge_title:"生物3",
            knowledge_img:require("../assets/images/1.jpeg"),
            knowledge_content:"其中动物界的种类最多原核生物界最少我国的海洋生物种类约占全世界海洋生物总种数的10%其中动物界的种类最多原核生物界最少我国的海洋生物种类约占全世界海洋生物总种数的10%"
          },
           {
            knowledge_id:4,
            knowledge_title:"生物4",
            knowledge_img:require("../assets/images/1.jpeg"),
            knowledge_content:"其中动物界的种类最多种原核生物界最少我国的海洋生物种类约占全世界海洋生物总种数的10%其中动物界的种类最多原核生物界最少我国的海洋生物种类约占全世界海洋生物总种数的10%"
          },
           {
            knowledge_id:5,
            knowledge_title:"生物5",
            knowledge_img:require("../assets/images/1.jpeg"),
            knowledge_content:"其中动物界的种类最多原核生物界最少我国的海洋生物种类约占全世界海洋生物总种数的10%其中动物界的种类最多原核生物界最少我国的海洋生物种类约占全世界海洋生物总种数的10%"
          },
           {
            knowledge_id:6,
            knowledge_title:"生物6",
            knowledge_img:require("../assets/images/1.jpeg"),
            knowledge_content:"其中动物界的种类最多原核生物界最少我国的海洋生物种类约占全世界海洋生物总种数的10%其中动物界的种类最多原核生物界最少我国的海洋生物种类约占全世界海洋生物总种数的10%"
          },
        ]


      }
    },
    methods: {
      change_sort(sort_id) {
        window.alert("选择的分类id为" + sort_id)
      },
      changeSortDetails(knowledge_id){
        window.alert("选择查看的分类详情id为" + knowledge_id),
        this.$router.push('/sort_details')
      }
    },
    mounted() {

    }
  }
</script>

<style scoped>
/* sort_head */
#sort_head{
  background-color: rgb(143, 139, 139);
  margin: 1rem;
}
  .sort {
    display: inline-block;
    margin:0 10px;
    
  }
  /* sort_info */
  .sort_info{
    height: 10%;
    margin:0.5rem 1rem;
    border: 1px solid #000;
  }
  .sort_left{
   
     
    height: 80px;
  }
  .sort_right{
    display: inline-block;
    width: 60%;
    margin: 1rem;
    

  }
  .sort_right_title{
    margin-bottom: 1rem;
  }
  .sort_right_content{
    display: -webkit-box;
-webkit-box-orient: vertical;
-webkit-line-clamp: 2;
overflow: hidden;
  }
</style>