<template>
    <div id="editknowledge">
        <div id="editknowledge_list" v-if="editknowledgeList.length">
            <div v-for="(item,index) in editknowledgeList" :key="index">
                <el-row>
                    <el-col :span="4">
                        <div class="knowledge_img"><img style="width: 130px; height: 130px;" :src="Image(item)" /></div>
                    </el-col>
                    <el-col :span="14" :offset=".5">
                        <div class="knowledge_content">
                            <div class="knowledge_title">
                                <span v-if="!item.knowledge_title">标题未编辑</span>
                                <span v-else>{{item.knowledge_title}}</span>
                            </div>
                            <div class="knowledge_info">
                                <span v-if="!item.knowledge_content">内容未编辑</span>
                                <span v-else>{{item.knowledge_content}}</span>
                            </div>
                        </div>
                    </el-col>
                    <el-col :span="6" :offset=".5">
                        <div class="knowledge_operation">
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
        name: "editknowledge",
        data() {
            return {
                editknowledgeList: [],
            }
        },
        methods: {
            // 获取未上传知识列表
            getLoadKnowledgeList() {
                this.axios.get("http://192.168.1.105:8083/knowledge/get_knowledge_list?knowledge_upload=" + 0 +
                        "&options_id=")
                    .then((res) => {
                        console.log(res.data)
                        this.editknowledgeList = res.data
                    })

            },

            // 编辑
            edit_knowledge(knowledge_id) {
                sessionStorage.setItem('judgeKnowledge', false)
                sessionStorage.setItem('getKnowledge', knowledge_id)
                this.$router.push("/editknowledge_detail");
            },
            delete_knowledge(knowledge_id) {
                this.axios.get("http://192.168.1.105:8083/knowledge/delete_knowledge?knowledge_id=" + knowledge_id)
                    .then((res) => {
                        console.log(res.data)
                        if (res.data.msg === "删除成功") {
                            this.$message({
                                message: "删除成功!",
                                type: "success",
                            });
                        } else {
                            this.$message({
                                message: "删除失败!",
                                type: "danger",
                            });
                        }
                        this.reload()
                    });
            },

            //图片路径
            Image(row) {
                var url = "http://192.168.1.105:8083/knowledge/" + row.knowledge_img
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

            this.getLoadKnowledgeList();
        }
    }
</script>

<style>
    /*  */
    #editknowledge .el-alert {
        margin-bottom: 10px;
    }

    /*详情  */
    #editknowledge .el-row {
        border: 1px solid rgb(102, 102, 102);
        border-radius: 6px;
        margin: 20px auto;
        width: 1000px;

    }

    #editknowledge .el-col {
        padding: 10px;

    }

    #editknowledge .el-button {
        display: block;
        width: 150px;
        margin: 20px auto;


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