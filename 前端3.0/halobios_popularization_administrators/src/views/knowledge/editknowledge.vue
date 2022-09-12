<template>
    <div id="editknowledge">
        <!-- 分类知识详情 -->
        <div id="editknowledge_list" v-if="editknowledgeList.length">
            <div v-for="(item, index) in editknowledgeList[0]" :key="index">
                <el-row>
                    <el-col :span="4">
                        <div class="knowledge_img"><img style="width: 130px; height: 130px;"
                                :src="Image(item.knowledge_img)" /></div>
                    </el-col>
                    <el-col :span="14" :offset=".5">
                        <div class="knowledge_content">
                            <div>
                                <span class="knowledge_title">{{ item.knowledge_title }}</span>
                            </div>

                            <div class="knowledge_info">{{ item.knowledge_content }}</div>
                        </div>
                    </el-col>

                    <el-col :span="6" :offset=".5">
                        <div class="knowledge_operation">
                            <el-button type="primary" @click="edit_knowledge(item.knowledge_id)">编辑</el-button>
                            <el-button type="primary" @click="delete_knowledge(item.knowledge_id, item.knowledge_img)">删除
                            </el-button>
                        </div>
                    </el-col>
                </el-row>
            </div>
        </div>
        <div v-else>
            <el-empty description="无内容,可测试重新选择分类"/>
        </div>
    </div>
</template>

<script setup lang="ts">
import KnowledgeService from "../../api/KnowledgeService.js"
import Constant from "../../assets/constant.js"
import { reactive, inject, onMounted } from "vue"
import { ElMessage, ElMessageBox } from 'element-plus'
import { useRouter } from 'vue-router'
import { useStore } from "vuex"
const refresh = inject<any>("reload")
const router = useRouter();
const store = useStore()

// 异步请求首屏数据
const editknowledgeList = reactive<any>([]);
onMounted(() => {
    KnowledgeService.get_knowledge_list(0)
        .then((response)=> {
            editknowledgeList.push(response.data)
        })
        .catch(error => {
            console.log(error)
        })
})
//图片路径
const Image = (knowledge_img) => Constant.URL + "/knowledge/" + knowledge_img
// 编辑
const edit_knowledge = (knowledge_id) => {
    sessionStorage.setItem('judgeKnowledge',"false")
    sessionStorage.setItem('getKnowledge', knowledge_id)
    router.push("/editknowledge_detail");
}
const delete_knowledge = (knowledge_id, knowledge_img) => {
    ElMessageBox.confirm('此操作将永久删除该文件, 是否继续?', '提示',
        {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        }
    )
        .then(() => {
            KnowledgeService.delete_knowledge(knowledge_id, knowledge_img).then((res) => {
                if (res.msg === "删除成功") {
                    ElMessage({
                        message: "删除成功!",
                        type: "success",
                    });
                } else {
                    ElMessage({
                        message: "删除失败!",
                        type: "warning",
                    });
                }
                refresh()
            });
        }).catch(() => {
            ElMessage({
                type: 'info',
                message: '已取消删除'
            });
        })
}
</script>

<style>
#editknowledge .icon {
    width: 1em;
    height: 1em;
    vertical-align: -0.15em;
    fill: currentColor;
    overflow: hidden;
}

/*  */
#editknowledge .el-alert {
    margin-bottom: 10px;
}

/*详情  */
#editknowledge_list .el-row {
    border: 1px solid rgb(102, 102, 102);
    border-radius: 6px;
    margin: 20px auto;
    width: 1000px;

}

#editknowledge_list .el-col {
    padding: 10px;

}

#editknowledge_list .el-button {
    display: block;
    width: 150px;
    margin: 10px auto;


}

#editknowledge_list .el-badge {
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
#editknowledge_list {
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