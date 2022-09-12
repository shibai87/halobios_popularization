<template>
  <div id="sort">
    <!-- head -->
   <!-- sort_head -->
     
      <div  class="sort">
    <ul  class="sort1">
      
       <li v-for="(item,index) in sort_type_0" :key="index" class="sort_type">{{item}}</li>
      </ul>   
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
        sort_type_0:[],
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
         loadSortData(){
        let i,j;
        //获取json数据信息
      this.axios.get('sort1.json')
        .then((res)=> {
            console.log(res.data,res.data.length);
            // 未设置为空情况

  // 第零级
            this.sort_type_0[0]=res.data[0].sort_type_0;
            for(i=0;i<res.data.length;i++){
               for(j=0;j<this.sort_type_0.length;j++){
                // 遍历完已有数组，有新成员需添加
                 if(res.data[i].sort_type_0!=this.sort_type_0[j]&&this.sort_type_0.length==j+1){
                  
                     this.sort_type_0[++j]=res.data[i].sort_type_0;
                     break;
                   }
                    // 遍历完已有数组，未有新成员需添加
                    if(res.data[i].sort_type_0==this.sort_type_0[j]){
                     break;
                   }    
            }
            
            }
            console.log("数组："+this.sort_type_0);

// 第一级
          

// 第二级
           

        
        })
        .catch((error) => {
          console.log(error)
        });
    
    },
      
      changeSortDetails(knowledge_id){
        window.alert("选择查看的分类详情id为" + knowledge_id),
        this.$router.push('/sort_details')
      }
    },
    mounted() {
      this.loadSortData();
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
    
  }
   .sort_right_content{
    display: -webkit-box;
-webkit-box-orient: vertical;
-webkit-line-clamp: 2;
overflow: hidden;
  }
</style>