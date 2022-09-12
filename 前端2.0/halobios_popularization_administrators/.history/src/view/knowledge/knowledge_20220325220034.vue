<template>
    <div id="knowledge">
           <el-alert title=" 请按从左至右的顺序选择分类完所有分类（分类分别表示：域、界、门、纲、目、科、属）！" type="success" :closable="false"></el-alert>
        <Sort></Sort>
          <el-button type="primary" @click="queryknow()">开始查询</el-button>
        <!-- 分类知识详情 -->
        <div>
            <el-row>
                <el-col :span="4">
                    <!-- <div class="grid-content bg-purple"><img style="width: 130px; height: 130px;" :src="Image()" /> -->
                    <img style="width: 130px; height: 130px;" src="../../assets/img/login.jpeg" class="knowledge_img">
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
             

            }
        },
        methods: {
          // 获取知识列表
   getLoadKnowledge() {            
        this.axios.get("http://localhost:8083/knowledge/get_knowledge?options_id="+this.getSortListLastId)
                    .then((res)=>{
                        console.log(res.data)
                    })  
                    
            },
            queryknow(){
                // if(!options6_value){
                //      this.$message({
                //   message: "请确保已选完所有分类！",
                //   type: "error",
                // });
                // }
                // else{
                    this.axios.get("http://localhost:8083/knowledge/get_knowledge?options_id="+this.getSortListLastId)
                    .then((res)=>{
                        console.log(res.data)
                    })  
                // }
              
            }



            // 
         
            //图片路径
            //   Image(index, row) {
            //       var url = "http://localhost:8188/"+row.imageId
            //       console.log(url)
            //       return url
            //   },
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