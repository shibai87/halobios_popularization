<template>
    <div id="knowledge">
         
        <Sort></Sort>
          <el-button type="primary" @click="queryknow()">开始查询</el-button>
        <!-- 分类知识详情 -->
        <div>
            <div v-for="(item,index) in knowledgeList" :key="index">
                   <el-row>
                <el-col :span="4">
                    <div><img style="width: 130px; height: 130px;" :src="Image(item[index])" /></div>
                </el-col>
                <el-col :span="12">
                    <div class="knowledge_content">
                        <div class="knowledge_title"></div>
                        <div class="knowledge_info"></div>
                    </div>
                </el-col>
                <el-col :span="8">
                    <div class="grid-content bg-purple-light"></div>
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
                        // console.log(res.data)
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
              Image(index, row) {
                  var url = "http://localhost:8083/"+row.imageId
                  console.log(url)
                  return url
              },
            // Image() {

            //     var url = "http://localhost:8083/" + "../../assets/img/login.jpeg"
            //     console.log(url)
            //     return url
            // },

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
</style>
<style scoped>
</style>