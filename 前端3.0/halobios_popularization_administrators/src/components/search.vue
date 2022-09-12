<template>
    <div id="search">
        <!-- {{search[0]}} -->
        <el-select v-model="search_value" filterable placeholder="请输入标题进行搜索" clearable no-match-text="未有相关数据！"
            no-data-text="未上传任何信息，空空如也！">

            <div v-if="getSerchType == 0">
                <el-option v-for="item in search[0]" :key="item.knowledge_title" :label="item.knowledge_title"
                    :value="item.knowledge_id">
                </el-option>
            </div>
            <div v-else-if="getSerchType == 1">
                <el-option v-for="item in search[0]" :key="item.activity_title" :label="item.activity_title"
                    :value="item.activity_id">
                </el-option>
            </div>
            <div v-else>
                <el-option v-for="item in search[0]" :key="item.video_title" :label="item.video_title"
                    :value="item.video_id">
                </el-option>
            </div>
        </el-select>
        <el-button type="primary" icon="el-icon-search" @click="getsearch">搜索</el-button>
    </div>
</template>

<script setup lang="ts">
import KnowledgeService from "../api/KnowledgeService.js"
import ActivityService from "../api/ActivityService"
import VideoService from "../api/VideoService"
import { ref, reactive } from "vue"
import { onMounted } from "vue"
import { useRouter } from 'vue-router'
const router = useRouter();
const search = reactive<any>([])
const search_value = ref("")
const getSerchType = ref(JSON.parse(sessionStorage.getItem('search_type')||""))

onMounted(() => {
    if (getSerchType.value == 0) {
        KnowledgeService.search().then((res) => {
            search.push(res)
        });
    }
    else if (getSerchType.value == 1) {
        ActivityService.search().then((res) => { search.push(res) });
    }
    else {
        VideoService.search().then((res) => { search.push(res) });
    }
})
const getsearch = () => {
    if (getSerchType.value == 0) {
        sessionStorage.setItem('judgeKnowledge', "true")
        sessionStorage.setItem('getKnowledge', search_value.value)
        router.push("/editknowledge_detail");
    } else if (getSerchType.value == 1) {
        sessionStorage.setItem('judgeactivity', "true")
        sessionStorage.setItem('activity_id', search_value.value)
        router.push("/editactivity_detail");
    } else {
        sessionStorage.setItem('judgeclass', "true")
        sessionStorage.setItem('class_id', search_value.value)
        router.push("/editclass_detail");
    }
}
</script>

<style scoped>
#search {
    width: 450px;
    margin: 0 auto 10px;
}

.el-select {
    width: 350px;
    padding-right: 10px;
}
</style>