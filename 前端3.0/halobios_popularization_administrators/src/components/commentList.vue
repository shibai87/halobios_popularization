<template>
    <div id="commentList">
        <el-header>
            <el-page-header @back="goBack" content="用户操作（点赞、收藏、评论）详情页面">
            </el-page-header>
        </el-header>
        <el-main>
            <!-- <Operation></Operation> -->
            <el-divider>以下为评论列表</el-divider>
            <!-- 下拉搜索 -->
            <el-row class="commentList_select">
                <el-col :span="1.5" :offset="9">
                    <div class="commentList_select_title">评论查询:</div>
                </el-col>
                <el-col :span="5">
                    <el-select v-model="value" placeholder="请选择要查询的评论状态">
                        <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                        </el-option>
                    </el-select>
                </el-col>
                <el-col :span="6">
                    <el-button @click="refresh" type="success">清空选择</el-button>
                </el-col>
            </el-row>
            <!-- 评论列表 -->
            <div v-if="commentList[0]">
                <ul class="infinite-list" style="overflow:auto">
                    <li v-for="(item, index) in commentList[0]" :key="index" class="infinite-list-item">
                        <el-descriptions :column="2" border>
                            <el-descriptions-item labelStyle="width:200px" contentStyle="width:400px">
                                <template #label>
                                    <i class="el-icon-user"></i>
                                    评论人昵称
                                </template>
                                {{ item.user_name }}
                            </el-descriptions-item>
                            <el-descriptions-item labelStyle="width:200px" contentStyle="width:400px">
                                <template #label>
                                    <i class="el-icon-tickets"></i>
                                    评论状态
                                </template>
                                {{ item.comment_state }}
                            </el-descriptions-item>

                        </el-descriptions>
                        <el-descriptions border>
                            <el-descriptions-item labelStyle="width:200px" contentStyle="width:15000px">
                                <template #label>
                                    <i class="el-icon-tickets"></i>
                                    评论内容
                                </template>
                                <div class="comment_content">
                                    {{ item.comment_content }}
                                </div>
                            </el-descriptions-item>
                        </el-descriptions>
                        <div class="commentList_button">
                            <el-button type="primary" size="small" v-if="!(item.comment_state == '评论未被举报')"
                                @click="toReport(item.comment_id)">去审核</el-button>
                        </div> 
                    </li>
                </ul>
            </div>
            <div v-else>
                <el-empty description="该评论状态下无评论！" v-if="value == 0 || value == 1 || value == 2"></el-empty>
                <el-empty description="没有人评论！" v-else></el-empty>
            </div>

        </el-main>
    </div>
</template>

<script setup lang="ts">
import OperationService from "../api/OperationService.js"
import { ref, reactive, inject, onMounted } from "vue"
import { watch } from "vue"
import { useRouter } from "vue-router"
const router = useRouter()
const refresh = inject<any>("reload")
const commentList = reactive<any>([])
const options = reactive<any>([
    {
        value: '0',
        label: '未被举报'
    }, {
        value: '1',
        label: '已通过审核'
    }, {
        value: '2',
        label: '待审核'
    }, {
        value: '3',
        label: '未通过审核'
    }])
const value = ref<number>(0)

onMounted(() => {
    OperationService.getAllCommentList(JSON.parse(sessionStorage.getItem('operation_source_type')||""), JSON.parse(sessionStorage.getItem('source_id')||""))
        .then(response => {
            for (let i = 0; i < response.length; i++) {
                if (response[i].comment_state == 0) {
                    response[i].comment_state = "评论未被举报"
                } else if (response[i].comment_state == 1) {
                    response[i].comment_state = "评论已通过"
                } else
                if (response[i].comment_state == 2) {
                    response[i].comment_state = "评论待审核"
                } else
                if (response[i].comment_state == 3) {
                    response[i].comment_state = "该评论未通过审核"
                }
            }
            console.log(response)
            commentList.push(response)
            console.log(commentList[0])
        })
})

const goBack = () => {
    router.push("/index");
}

const toReport = (comment_id) => {
    router.push({
        path: "/report",
        query: {
            comment_id: comment_id
        }
    })
}
watch(value, (newValue, oldValue) => {
    console.log('watch 已触发', newValue)
    OperationService.getAllCommentListByCommentState(JSON.parse(sessionStorage.getItem('operation_source_type')||""), JSON.parse(sessionStorage.getItem('source_id')||""), value.value)
        .then((res) => {
            for (let i = 0; i < res.length; i++) {
                if (res[i].comment_state == 0) {
                    res[i].comment_state = "评论未被举报"
                } else if (res[i].comment_state == 1) {
                    res[i].comment_state = "评论已通过"
                } else
                if (res[i].comment_state == 2) {
                    res[i].comment_state = "评论待审核"
                } else
                if (res[i].comment_state == 3) {
                    res[i].comment_state = "该评论未通过审核"
                }
            }
            console.log("watch:value")
            console.log(res)
            commentList.splice(0,commentList.length)
            commentList.push(res)
            console.log(commentList)
        })
})
</script>

<style>
#commentList .el-header {
    background-color: rgb(99, 175, 255);
    padding: 20px 10px;
}

#commentList .el-select {
    width: 300px;
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

.commentList_select_title {
    display: inline-block;
    margin-right: 10px;
    line-height: 40px;
    height: 40px;
}
</style>