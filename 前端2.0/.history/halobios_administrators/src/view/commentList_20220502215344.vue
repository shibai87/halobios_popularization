<template>
    <div id="commentList">
        <el-header>
            <el-page-header @back="goBack" content="用户操作（点赞、收藏、评论）详情页面">
            </el-page-header>
        </el-header>
        <el-main>
            <Operation></Operation>
            <el-divider>以下为评论列表</el-divider>
            <div v-if="this.commentList.length">
                <ul class="infinite-list" v-infinite-scroll="load" style="overflow:auto">
                    <li v-for="(item,index) in commentList" :key="index" class="infinite-list-item">
                        <el-descriptions class="margin-top" :column="3" border>
                            <el-descriptions-item>
                                <template slot="label">
                                    <i class="el-icon-user"></i>
                                    评论人昵称
                                </template>
                                {{item.user_name}}
                            </el-descriptions-item>
                            <el-descriptions-item>
                                <template slot="label">
                                    <i class="el-icon-tickets"></i>
                                    评论内容
                                </template>
                                <div class="comment_content">
                                  {{item.comment_content}}
                                </div>
                            </el-descriptions-item>
                            <el-descriptions-item>
                                <template slot="label">
                                    <i class="el-icon-tickets"></i>
                                    评论状态
                                </template>
                                {{item.comment_state}}
                            </el-descriptions-item>
                          
                        </el-descriptions>
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
        components:{
            Operation
        },
        data() {
            return {
                commentList: [],
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
                            }else if (this.commentList[i].comment_state == 1) {
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
   #commentList  .el-button--small{
       width: 100%;
    }
</style>
<style scoped>
    li {
        list-style: none;
        width: 1400px;
        margin: 0 auto;
       
    }
     .comment_content{
      display:-webkit-box;/**对象作为伸缩盒子模型展示**/
            -webkit-box-orient:vertical;/**设置或检索伸缩盒子对象的子元素的排列方式**/
            -webkit-line-clamp:3;/**显示的行数**/
            overflow-x:scroll;
            bottom: -17;
    }
</style>