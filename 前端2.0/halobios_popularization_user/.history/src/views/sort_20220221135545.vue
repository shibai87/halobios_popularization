<template>
  <div id="sort">
    <!-- head -->
    <div id="sort_head">
      <div class="sort" v-for="(sort,index) in sorts" :key="index" @click="change_sort(sort.sort_id)">
        {{sort.sort_type_name}}
      </div>
    </div>
    <div class="sort_info" v-for="(knowledge,index) in knowledges" :key="index">
      <img class="sort_left" :src="knowledge.knowledge_img">
      
      <div class="sort_right">
        <div class="sort_right_title">{{knowledge.knowledge_title}}</div>
         <div class="sort_right_content"></div>{{knowledge.knowledge_content}}
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
            knowledge_content:"其中动物界的种类最多（12794种），原核生物界最少（229种）。我国的海洋生物种类约占全世界海洋生物总种数的10%，数量占50%。我国海域的海洋生物，按照分布情况大致可以分为水域海洋生物和滩涂海洋生物两大类。在水域海洋"
          },
           {
            knowledge_id:2,
            knowledge_title:"生物1",
            knowledge_img:require("../assets/images/1.jpeg"),
            knowledge_content:"其中动物界的种类最多（12794种），原核生物界最少（229种）。我国的海洋生物种类约占全世界海洋生物总种数的10%，数量占50%。我国海域的海洋生物，按照分布情况大致可以分为水域海洋生物和滩涂海洋生物两大类。在水域海洋"
          },
        ]


      }
    },
    methods: {
      change_sort(sort_id) {
        window.alert("选择的分类id为" + sort_id)
      }
    },
    mounted() {

    }
  }
</script>

<style scoped>
  .sort {
    display: inline-block;

    margin: 10px;
   

  }
  .sort_left{
   
    /* width: 50px; */
    height: 80px;
  }
  .sort_right{
    width: 60%;
    display: inline-block;
   
  }
  .sort_right_content{
     overflow: hidden;
            text-overflow:ellipsis;
            white-space: nowrap;
  }
</style>