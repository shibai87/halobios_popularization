<template>
    <div id="activity">
      <div v-if="activityList.length">
          
      </div>
         
        <div v-else>
            <el-empty description="无内容,可测试重新选择分类"></el-empty>
        </div>
    </div>
</template>

<script>
    export default {
        inject: ["reload"],
        name: "knowledge",
        data() {
            return {
                knowledgeList: [],
            }
        },
        methods: {
        

        },
        computed: {
         
        },

        mounted() {

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
    /* 分类 */
    .knowledge_query {
        width: 100%;
        margin: 15px auto 0;
    }

    /* 知识列表 */
    #knowledge_list {
        margin-top: 50px;
    }

    .knowledge_img img {
        margin: 10px;
    }

    .knowledge_title {
        height: 50px;
        font-size: 30px;
    }

    .knowledge_info {
        width: 600px;
        display: -webkit-box;
        -webkit-box-orient: vertical;
        -webkit-line-clamp: 3;
        overflow: hidden;
    }

    .knowledge_operation {
        height: 100px;
    }
</style>