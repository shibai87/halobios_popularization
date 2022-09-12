<template>
  <div id="sort">
    <!-- head -->
    <div id="sort_head">
     
      <div  class="sort">
  <div v-for="(value,index) in sort_type_0" :key="index" class="sort_type">{{value}}</div>
   <div v-for="(value,index) in sort_type_1" :key="index" class="sort_type">{{value}}</div>
   <div v-for="(value,index) in sort_type_2" :key="index" class="sort_type">{{value}}</div>
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
      // change_sort(sort_id) {
      //   window.alert("选择的分类id为" + sort_id)
      // },
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
   font-size: 20px; 
  /* height: 30px;
  
  overflow-y:hidden ;
 */
  display: flex;
  flex-wrap:wrap;
  
}
#sort  .sort {
    display: inline-block;
    margin:0 10px;
    
  }
  /* sort_info */
#sort   .sort_info{
    height: 10%;
    margin:0.5rem 1rem;
    border: 1px solid #000;
  }
 #sort  .sort_left{
   
     
    height: 80px;
  }
 #sort  .sort_right{
    display: inline-block;
    width: 60%;
    margin: 1rem;
    

  }
 #sort  .sort_right_title{
    margin-bottom: 1rem;
  }
 #sort  .sort_right_content{
    display: -webkit-box;
-webkit-box-orient: vertical;
-webkit-line-clamp: 2;
overflow: hidden;
  }
</style>