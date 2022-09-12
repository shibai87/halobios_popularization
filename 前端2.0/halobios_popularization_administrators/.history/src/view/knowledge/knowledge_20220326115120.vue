<template>
    <div id="knowledge">
          <el-alert title=" 请按从左至右的顺序选择分类完所有分类（分类分别表示：域、界、门、纲、目、科、属）！" type="success" :closable="false"></el-alert>
        <Sort></Sort>
        <el-button type="primary" class="knowledge_query" @click="queryknow()">开始查询</el-button>
        <el-button type="warning" class="knowledge_query" @click="clear()" v-if="!knowledgeList">清空分类选项</el-button>
        <el-divider>以下查询结果</el-divider>

        <!-- 分类知识详情 -->
        <div id="knowledge_list" >
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
                            <el-button type="primary" @click="edit_knowledge(item.knowledge_id)">编辑</el-button>
                            <el-button type="primary" @click="delete_knowledge">删除</el-button>
                        </div>
                    </el-col>
                </el-row>
            </div>
        </div>
        <!-- <div v-else>
            <el-empty description="无内容,请重新选择分类"></el-empty>
        </div> -->
    </div>
</template>

<script>
    import Sort from "../../components/sort.vue"
    export default {
        inject: ["reload"],
        name: "knowledge",
        components: {
            Sort
        },
        data() {
            return {
                knowledgeList: [],
            }
        },
        methods: {
            // 获取知识列表
            getLoadKnowledgeList() {
                this.axios.get("http://localhost:8083/knowledge/get_knowledge_list?options_id=" + this.getSortListLastId)
                    .then((res) => {
                        console.log(res.data)
                        this.knowledgeList = res.data
                    })

            },
            // 查询列表
            queryknow() {
                this.axios.get("http://localhost:8083/knowledge/get_knowledge?options_id=" + this.getSortListLastId)
                    .then((res) => {
                        console.log(res.data)
                       this.knowledgeList.push(res.data)
                        // this.knowledgeList = res.data

                    })
            },
            clear(){
               this.reload();
            },
            // 编辑
            edit_knowledge(knowledge_id){
                this.$store.commit("judgeKnowledge",true)
                 this.$store.commit("getKnowledge",knowledge_id)
                this.$router.push("/editknowledge_detail");
            },
            delete_knowledge(){

            },


            // 

            //图片路径
            Image(row) {
                var url = "http://localhost:8083/knowledge/" + row.knowledge_img
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
        height: 100px;
        display: -webkit-box;
        /**对象作为伸缩盒子模型展示**/
        -webkit-box-orient: vertical;
        /**设置或检索伸缩盒子对象的子元素的排列方式**/
        -webkit-line-clamp: 3;
        /**显示的行数**/
        overflow: hidden;
        /**隐藏超出的内容**/
    }

    .knowledge_operation {
        height: 100px;
    }
</style>