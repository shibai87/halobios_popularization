<template>
    <div id="knowledge">
        <Search></Search>
        <!-- 分类列表 -->
        <el-alert title=" 请按从左至右的顺序选择完所有分类（分类分别表示：界、门、纲、目、科、属）！" type="success" :closable="false"></el-alert>
        <Sort></Sort>
        <el-button type="primary" class="knowledge_query" @click="Knowledge()">开始查询</el-button>
        <el-button type="warning" class="knowledge_query" @click="clear()" v-if="!knowledgeList.length">重新选择分类
        </el-button>

        <el-divider>以下查询结果</el-divider>

        <!-- 分类知识详情 -->
        <div id="knowledge_list" v-if="knowledgeList.length">
            <div v-for="(item,index) in knowledgeList" :key="index">
                <el-row>
                    <el-col :span="4">
                        <div class="knowledge_img"><img style="width: 130px; height: 130px;" :src="Image(item)" /></div>
                    </el-col>
                    <el-col :span="14" :offset=".5">
                        <div class="knowledge_content">
                            <div>
                                <span class="knowledge_title">{{item.knowledge_title}}</span>
                            </div>

                            <div class="knowledge_info">{{item.knowledge_content}}</div>
                        </div>
                    </el-col>

                    <el-col :span="6" :offset=".5">
                        <div class="knowledge_operation">
                            <el-button type="primary" @click="comment(item.knowledge_id)">用户操作详情</el-button>
                            <el-button type="primary" @click="edit_knowledge(item.knowledge_id)">编辑</el-button>
                            <el-button type="primary" @click="delete_knowledge(item.knowledge_id,item.knowledge_img)">删除
                            </el-button>
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
    import Search from "../../components/search.vue"
    import Sort from "../../components/sort.vue"
    import KnowledgeService from "../../api/KnowledgeService.js"
    export default {
        inject: ["reload"],
        name: "knowledge",
        components: {
            Search,
            Sort
        },
        data() {
            return {
                knowledgeList: [],
            }
        },
        methods: {
            // 获取知识列表+查询列表
            getLoadKnowledgeList() {
                KnowledgeService.get_knowledge_list(1).then((res) => {this.knowledgeList = res})
            },
            Knowledge() {
                    KnowledgeService.get_knowledge_list(1,this.getSortListLastId).then((res) => {
                        this.knowledgeList = res
                        if (res.length != 0) {
                            this.$message({
                                message: "查询成功!",
                                type: "success",
                            });
                        } else {
                            this.$message({
                                message: "查询失败,该分类下没有任何内容!",
                                type: "danger",
                            });
                        }
                    })
            },
            clear() {
                this.$store.commit("getSortIdListLast", "")
                this.getLoadKnowledgeList()
                this.reload();
            },
            comment(knowledge_id) {
                sessionStorage.setItem('operation_source_type', 0)
                sessionStorage.setItem('source_id', knowledge_id)
                this.$router.push("/commentList")
            },
            // 编辑
            edit_knowledge(knowledge_id) {
                sessionStorage.setItem('judgeKnowledge', true)
                sessionStorage.setItem('getKnowledge', knowledge_id)
                this.$router.push("/editknowledge_detail");
            },
            delete_knowledge(knowledge_id, knowledge_img) {
                this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                        KnowledgeService.delete_knowledge(knowledge_id,knowledge_img).then((res) => {
                            if (res.msg === "删除成功") {
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
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
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
    #knowledge .icon {
        width: 1em;
        height: 1em;
        vertical-align: -0.15em;
        fill: currentColor;
        overflow: hidden;
    }

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

    #knowledge_list .el-badge {
        font-size: 35px;
        margin: 0 10px 20px;
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