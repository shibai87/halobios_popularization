<template>
    <div id="activity">
      <div v-if="activityList.length">
           <div v-for="(item,index) in activityList" :key="index">
                <el-row>
                    <el-col :span="4">
                        <div class="knowledge_img"><img style="width: 130px; height: 130px;" :src="Image(item)" /></div>
                    </el-col>
                    <el-col :span="14" :offset=".5">
                        <div class="knowledge_content">
                            <div>
                                <span class="knowledge_title">{{item.knowledge_title}}</span>
                                <el-badge :value="3" class="item">
                                    <i class="el-icon-star-off"></i>
                                </el-badge>
                            </div>

                            <div class="knowledge_info">{{item.knowledge_content}}</div>
                        </div>
                    </el-col>
                    <el-col :span="6" :offset=".5">
                        <div class="knowledge_operation">
                            <el-button @click="comment(item.knowledge_id)">评论列表(待定)</el-button>
                            <el-button type="primary" @click="edit_knowledge(item.knowledge_id)">编辑</el-button>
                            <el-button type="primary" @click="delete_knowledge(item.knowledge_id)">删除</el-button>
                        </div>
                    </el-col>
                </el-row>
            </div>
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