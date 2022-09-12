<template>
    <div id="commentList">
        <el-header>
            <el-page-header @back="goBack" content="用户操作（点赞、收藏、评论）详情页面">
            </el-page-header>
        </el-header>
        <el-main>
            <Operation></Operation>
            <el-divider>以下为评论列表</el-divider>
            <div class="commentList_select">
                <div class="commentList_select_title">可在下拉列表中根据评论状态进行查询评论列表</div>
                 <el-select v-model="value" placeholder="请选择">
                <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                </el-option>
            </el-select>
            </div>
            <div v-if="this.commentList.length">
                <ul class="infinite-list" v-infinite-scroll="load" style="overflow:auto">
                    <li v-for="(item,index) in commentList" :key="index" class="infinite-list-item">
                        <el-descriptions :column="2" border>
                            <el-descriptions-item labelStyle="width:200px" contentStyle="width:400px">
                                <template slot="label">
                                    <i class="el-icon-user"></i>
                                    评论人昵称
                                </template>
                                {{item.user_name}}
                            </el-descriptions-item>
                            <el-descriptions-item labelStyle="width:200px" contentStyle="width:400px">
                                <template slot="label">
                                    <i class="el-icon-tickets"></i>
                                    评论状态
                                </template>
                                {{item.comment_state}}
                            </el-descriptions-item>

                        </el-descriptions>
                        <el-descriptions border>
                            <el-descriptions-item labelStyle="width:200px" contentStyle="width:15000px">
                                <template slot="label">
                                    <i class="el-icon-tickets"></i>
                                    评论内容
                                </template>
                                <div class="comment_content">
                                    {{item.comment_content}}
                                </div>
                            </el-descriptions-item>
                        </el-descriptions>
                        <div class="commentList_button">
                            <el-button type="primary" size="small" :disabled="item.comment_state=='评论未被举报'"
                                @click="toReport(item.comment_id)">去审核</el-button>
                        </div>

                    </li>
                </ul>

            </div>
            <div v-else>
                <el-empty description="没有人评论！"></el-empty>
            </div>

        </el-main>
    </div>
</template>

<script>
    import Operation from "../components/operation.vue"
    export default {
        name: 'commentList',
        components: {
            Operation
        },
        data() {
            return {
                commentList: [],
                options: [{
                    value: '0',
                    label: '查询所有未被举报的评论列表'
                }, {
                    value: '1',
                    label: '查询所有已通过审核的评论列表'
                }, {
                    value: '2',
                    label: '查询所有有待审核的评论列表'
                }, {
                    value: '3',
                    label: '查询所有未通过审核的评论列表'
                },
                ],
                value: ''
            }
        },
        methods: {
            goBack() {
                this.$router.push("/index");
            },
            getLoadCommentList() {
                this.$axios.get("http://192.168.1.105:8083/operation/getAllCommentList?operation_source_type=" + this
                        .getOperationSourceType +
                        "&operation_source_id=" + this.getSourceId)
                    .then((res) => {
                        console.log(res.data)
                        this.commentList = res.data
                        for (let i = 0; i < this.commentList.length; i++) {
                            if (this.commentList[i].comment_state == 0) {
                                this.commentList[i].comment_state = "评论未被举报"
                            } else if (this.commentList[i].comment_state == 1) {
                                this.commentList[i].comment_state = "评论已通过"
                            } else
                            if (this.commentList[i].comment_state == 2) {
                                this.commentList[i].comment_state = "评论待审核"
                            } else
                            if (this.commentList[i].comment_state == 3) {
                                this.commentList[i].comment_state = "该评论未通过审核"
                            }
                        }
                    })
            },
            load() {
                this.count += 2
            },
            toReport(comment_id) {
                this.$router.push({
                    path: "/report",
                    query: {
                        comment_id: comment_id
                    }
                })
            }
        },
        computed: {
            getOperationSourceType() {
                return JSON.parse(sessionStorage.getItem('operation_source_type'))
            },
            getSourceId() {
                return JSON.parse(sessionStorage.getItem('source_id'))
            }
        },
        mounted() {
            this.getLoadCommentList()
        }
    }
</script>

<style>
    #commentList .el-header {
        background-color: rgb(99, 175, 255);
        padding: 20px 10px;
    }
   
    #commentList .el-descriptions-item__label {
        color: #000;
        width: 40px;
    }

    #commentList .el-descriptions-item__content {
        width: 100px;
    }

    #commentList .el-button--small {
        width: 100%;
    }

    #commentList .el-descriptions__header {
        margin-bottom: 0;
    }
</style>
<style scoped>
    li {
        list-style: none;
        width: 1200px;
        margin: 20px auto;
    }
     .commentList_select{
        width: 50px;
        margin: auto;
    }
    .commentList_select_title{
        display:inline-block;
        margin-right: 10px;
    }
</style>