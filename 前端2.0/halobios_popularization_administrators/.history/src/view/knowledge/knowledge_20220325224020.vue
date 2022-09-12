<template>
    <div id="knowledge">
         
        <Sort></Sort>
          <el-button type="primary" @click="queryknow()">开始查询</el-button>
        <!-- 分类知识详情 -->
        <div>
            <div v-for="(item,index) in knowledgeList" :key="index">
                
                   <el-row>
                <el-col :span="4">
                    <div class="knowledge_img"><img style="width: 130px; height: 130px;" :src="Image(item)" /></div>
                </el-col>
                <el-col :span="18">
                    <div class="knowledge_content">
                        <div class="knowledge_title">{{item.knowledge_title}}</div>
                        <div class="knowledge_info">{{item.knowledge_content}}</div>
                    </div>
                </el-col>
                <el-col :span="2">
                    <div class="knowledge_operation">
                        <el-button type="primary" icon="el-icon-edit" circle></el-button>
                     <el-button type="danger" icon="el-icon-delete" circle></el-button> 
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

    /* sort */
    #knowledge_sort .el-select {
        width: 180px;
    }
    /*  */
#knowledge .el-row{
        border: 1px solid rgb(102, 102, 102);
        border-radius:6px;
        margin: 10px 20px;
        width: 1000px;
       
    }
#knowledge .el-col{
    height: 150px;
}
</style>
<style scoped>
.knowledge_img{
    display: inline-block;
    height: 100px;
    width: 100px;
    margin: 10px;
}

</style>