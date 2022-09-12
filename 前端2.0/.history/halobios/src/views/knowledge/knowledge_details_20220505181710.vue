<template>
    <div id="knowledge_details">
        <van-nav-bar title="分类详情" fixed left-arrow @click-left="pageBack" />
        <van-pull-refresh v-model="isLoading" @refresh="onRefresh">
            <div class="knowledge_details_main">
                <h1>{{ruleForm.knowledge_title}}</h1>
                <van-image class="knowledge_details_img" width="400" height="300" fit="fill" :src="Image()" />
                <div class="knowledge_details_content">
                    {{ruleForm.knowledge_content}}
                </div>
                <div class="knowledge_details_knowledge_key">
                    <div>所属分类:</div>
                    <van-row>
                        <van-col span="12">{{sort.sort_type_1}}</van-col>
                        <van-col span="12">{{sort.sort_type_2}}</van-col>
                        <van-col span="6">{{sort.sort_type_3}}</van-col>
                        <van-col span="6">{{sort.sort_type_4}}</van-col>
                        <van-col span="6">{{sort.sort_type_5}}</van-col>
                        <van-col span="6">{{sort.sort_type_6}}</van-col>
                    </van-row>
                </div>
            </div>
            <Operaion></Operaion>
            <Comment></Comment>
        </van-pull-refresh>
    </div>
</template>

<script>
    import {
        Toast
    } from 'vant';
    import Operaion from "../../components/operation.vue"
    import Comment from "../../components/comment.vue"
    import KnowledgeService from"../../api/KnowledgeService.js"
    export default {
        name: 'knowledge_details',
        components: {
            Operaion,
            Comment
        },
        data() {
            return {
                // 刷新
                isLoading: false,

                ruleForm: {
                    knowledge_sort_id: "",
                    knowledge_title: '',
                    knowledge_content: '',
                    knowledge_img: "",
                },
                // 分类
                sort: [],
            }
        },
        methods: {
            pageBack() {
               this.$router.push("/")
            },

            // 刷新
            onRefresh() {
                setTimeout(() => {
                    Toast('刷新成功');
                    this.isLoading = false;
                    this.getLoadKnowledge()
                }, 1000);
            },

            // 获取需编辑的知识详情
            getLoadKnowledge() {
                // this.axios.get("http://192.168.197.65:8083/knowledge/get_knowledge?knowledge_id=" + this.getKonwledgeId)
                    KnowledgeService.getLoadKnowledge(this.getKonwledgeId).then((res) => {
                        console.log(res)
                        this.ruleForm = res.data[0]
                        
                        this.getSort()
                    });
            },
            // 获取所属分类
            getSort() {
                this.axios.get("http://192.168.197.65:8083/knowledge/get_sort?knowledge_sort_id=" + this.ruleForm
                        .knowledge_sort_id)
                    .then((res) => {
                        this.sort = res.data[0]
                    })
            },
            Image() {
                var url = "http://192.168.197.65:8083/knowledge/" + this.ruleForm.knowledge_img
                return url
            },
        },
        computed: {
            getKonwledgeId() {
                return JSON.parse(sessionStorage.getItem('source_id'));
            }
        },
        mounted() {
            this.getLoadKnowledge()
        }
    }
</script>

<style lang="less">
       #knowledge_details  .van-nav-bar__title {
            height: 30px;
            line-height: 30px;
            font-size: 20px;
            text-align: center;
        }

       #knowledge_details  .van-nav-bar__content {
            height: 70px;
        }

      #knowledge_details   .van-nav-bar__right {
            right: 5px;
            font-size: 20px;
        }

       #knowledge_details  .van-icon {
            color: #000;
            font-size: 30px;
            margin-right: 5px;
        }

       #knowledge_details  .knowledge_details_knowledge_key .van-col {
            overflow: scroll;
            white-space: nowrap;
            text-align: center;
        }
</style>

<style scoped>
    .knowledge_details_main {
        text-align: center;
    }

    .knowledge_details_main h1 {
        margin-top: 80px;
        font-size: 30px;
    }


    .knowledge_details_content {
        text-indent: 2em;
        font-size: 25px;
        margin: 10px;
        text-align: left;
    }

    .knowledge_details_knowledge_key {
        font-size: 20px;
        text-align: left;
        font-weight: bolder;
        margin: 10px;
        color: rgb(66, 199, 255);
    }
</style>