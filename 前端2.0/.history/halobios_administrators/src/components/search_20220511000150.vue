<template>
    <div id="search">
        <el-select v-model="search_value" filterable placeholder="请输入标题进行搜索" clearable no-match-text="未有相关数据！"
            no-data-text="未上传任何信息，空空如也！">
            <div v-if="this.getSerchType == 0">
                <el-option v-for="item in search" :key="item.knowledge_title" :label="item.knowledge_title"
                    :value="item.knowledge_id">
                </el-option>
            </div>
            <div v-else-if="this.getSerchType == 1">
                <el-option v-for="item in search" :key="item.activity_title" :label="item.activity_title"
                    :value="item.activity_id">
                </el-option>
            </div>
            <div v-else>
                <el-option v-for="item in search" :key="item.video_title" :label="item.video_title"
                    :value="item.video_id">
                </el-option>
            </div>
        </el-select>
        <el-button type="primary" icon="el-icon-search" @click="getsearch">搜索</el-button>
    </div>
</template>

<script>
import KnowledgeService from "../api/KnowledgeService.js"
import ActivityService from "../api/ActivityService"
import VideoService from "../api/VideoService"
    export default {
        name: "sort",
        data() {
            return {
                search: [],
                search_value: '',
            }
        },
        methods: {
            getLoadList() {
                if (this.getSerchType == 0) {
                    KnowledgeService.search().then((res) => {
                        console.log(res)
                        this.search = res});
                } else if (this.getSerchType == 1) {
                    ActivityService.search().then((res) => {this.search = res});
                } else {
                    VideoService.search().then((res) => {this.search = res});
                }
            },
            getsearch() {
                if (this.getSerchType == 0) {
                    sessionStorage.setItem('judgeKnowledge', true)
                    sessionStorage.setItem('getKnowledge', this.search_value)
                    this.$router.push("/editknowledge_detail");
                } else if (this.getSerchType == 1) {
                    sessionStorage.setItem('judgeactivity', true)
                    sessionStorage.setItem('activity_id', this.search_value)
                    this.$router.push("/editactivity_detail");
                } else {
                    sessionStorage.setItem('judgeclass', true)
                    sessionStorage.setItem('class_id', this.search_value)
                    this.$router.push("/editclass_detail");
                }
            }
        },
        computed: {
            getSerchType() {
                return JSON.parse(sessionStorage.getItem('search_type'));
            }
        },
        mounted() {
            this.getLoadList();
        }
    }
</script>

<style scoped>
    #search {
        display: inline-block;
        margin: 0 400px 10px;
    }
</style>