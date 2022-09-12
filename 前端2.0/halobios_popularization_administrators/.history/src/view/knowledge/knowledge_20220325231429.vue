<template>
    <div id="knowledge">
         
        <Sort></Sort>
          <el-button type="primary" class="knowledge_query" @click="queryknow()">开始查询</el-button>
        <!-- 分类知识详情 -->
        <div id="knowledge_list">
            <div v-for="(item,index) in knowledgeList" :key="index">
                
                   <el-row>
                <el-col :span="4">
                    <div class="knowledge_img"><img style="width: 130px; height: 130px;" :src="Image(item)" /></div>
                </el-col>
                <el-col :span="14" :offset=".5">
                    <div class="knowledge_content">
                        <div class="knowledge_title">{{item.knowledge_title}}</div>
                        <div class="knowledge_info">{{item.knowledge_content}}</div>
                    </div>
                </el-col>
                <el-col :span="6" :offset=".5">
                    <div class="knowledge_operation">
                        <el-button type="primary">编辑</el-button>
                     <el-button type="primary">删除</el-button> 
                    </div>
                    
                </el-col>
            </el-row>
            </div>
         
        </div>
    </div>
</template>

<script>
import Sort from "../../components/sort.vue"
    export default {
         inject: ["reload"],
        name: "knowledge",
        components:{
            Sort
        },
        data() {
            return {
             knowledgeList:[],

            }
        },
        methods: {
          // 获取知识列表
   getLoadKnowledge() {            
        this.axios.get("http://localhost:8083/knowledge/get_knowledge?options_id="+this.getSortListLastId)
                    .then((res)=>{
                        console.log(res.data)
                        this.knowledgeList=res.data

                    })  
                    
            },
            queryknow(){
             
                    this.axios.get("http://localhost:8083/knowledge/get_knowledge?options_id="+this.getSortListLastId)
                    .then((res)=>{
                        console.log(res.data)
                         this.knowledgeList=res.data
                       
                    })  
            },



            // 
         
            //图片路径
              Image(row) {
                  var url = "http://localhost:8083/knowledge/"+row.knowledge_img
                  console.log(url)
                  return url
              },
         

        },
        computed: {
    getSortListLastId() {
      return this.$store.state.sort_id_list_last;
    },
  },
     
        mounted() {

            this.getLoadKnowledge();
        }
    }
</script>
<style>
    /*  */
#knowledge_list .el-row{
        border: 1px solid rgb(102, 102, 102);
        border-radius:6px;
        margin: 20px auto;
        width: 1000px;
       
    }
#knowledge_list .el-col{
    padding: 10px;
   
}
#knowledge_list .el-button{
    display: block;
    width: 150px;
    margin: 20px auto;


}
</style>
<style scoped>
/* 分类 */
.knowledge_query{
    width: 200px;
    margin: auto;
}
/* 知识列表 */
#knowledge_list{
    margin-top: 50px;
}
.knowledge_img img{
    margin: 10px;
}
.knowledge_title{
    height:50px;
    font-size: 30px;
}
.knowledge_info{
     height:100px;
     display:-webkit-box;/**对象作为伸缩盒子模型展示**/
            -webkit-box-orient:vertical;/**设置或检索伸缩盒子对象的子元素的排列方式**/
            -webkit-line-clamp:3;/**显示的行数**/
            overflow:hidden;/**隐藏超出的内容**/
}
.knowledge_operation{
    height: 100px;
}
</style>