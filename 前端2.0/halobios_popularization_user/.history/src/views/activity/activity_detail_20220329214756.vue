<template>
    <div id="activity_details">
        <van-nav-bar title="分类详情" fixed left-arrow @click-left="pageBack" />

        <div class="activity_details_main">
            <h1>{{ruleForm.activity_title}}</h1>
            <van-image class="activity_details_img" width="400" height="300" fit="fill" :src="Image()" />

            <div class="activity_details_content">
                {{ruleForm.activity_content}}
            </div>
            <div class="activity_details_activity_key">
                <div>关键词:</div>
                <van-row>
                    <van-col span="8">{{sort.sort_type_0}}</van-col>
                    <van-col span="8">{{sort.sort_type_1}}</van-col>
                    <van-col span="8">{{sort.sort_type_2}}</van-col>
                </van-row>
                <van-row type="flex">
                    <van-col span="8">{{sort.sort_type_3}}</van-col>
                    <van-col span="8">{{sort.sort_type_4}}</van-col>
                    <van-col span="8">{{sort.sort_type_5}}</van-col>
                    <van-col span="8">{{sort.sort_type_6}}</van-col>
                </van-row>
            </div>

           <Operaion></Operaion>
            <Comment></Comment>
        </div>
    </div>
</template>

<script>
import Operaion from "../../components/operation.vue"
import Comment from "../../components/comment.vue"
    export default {
        name: 'activity_details',
        components:{
            Operaion,
            Comment
        },
        data() {
            return {
                ruleForm: {
                    activity_sort_id: "",
                    activity_title: '',
                    activity_content: '',
                    activity_img: "",
                },
                // 分类
                sort: [],
            }
        },
        methods: {
            pageBack() {
                window.history.back()
            },
            // 获取需编辑的知识详情
            getLoadactivity() {
                this.axios.get("http://192.168.1.103:8083/activity/get_activity?activity_id=" + this.getactivity)
                    .then((res) => {
                        console.log(res.data[0])
                        this.ruleForm = res.data[0]
                        //   console.log( this.activitys)
                        this.getSort()
                    });
            },
            getSort() {
                this.axios.get("http://192.168.1.103:8083/activity/get_sort?activity_sort_id=" + this.ruleForm
                        .activity_sort_id)
                    .then((res) => {
                        // console.log(res.data[0])
                        this.sort = res.data[0]
                    })
            },
            Image() {
                var url = "http://192.168.1.103:8083/activity/" + this.ruleForm.activity_img
                return url
            },
        },
        computed: {
            getactivity() {
                return JSON.parse(localStorage.getItem('activity_id'));
            },
            getSortListLastId() {
                return JSON.parse(localStorage.getItem('sort_id_list_last'));
            },
        },
        mounted() {
            this.getLoadactivity()
        }
    }
</script>

<style>
    /*  */
    #activity_details .van-nav-bar__title {
        height: 30px;
        line-height: 30px;
        font-size: 20px;
        text-align: center;

    }

    #activity_details .van-nav-bar__content {
        height: 70px;
    }

    #activity_details .van-nav-bar__right {
        right: 5px;
        font-size: 20px;
    }

    #activity_details .van-icon {
        color: #000;
        font-size: 30px;
        margin-right: 5px;
    }
</style>
<style scoped>
    /*  */
    .activity_details_main {
        text-align: center;
        margin: 60px auto;
    }

    .activity_details_main h1 {
        margin-top: 80px;
        font-size: 30px;
    }


    .activity_details_content {
        text-indent: 2em;
        font-size: 25px;
        margin: 10px;
        text-align: left;
    }

    .activity_details_activity_key {
        font-size: 20px;
        text-align: left;
        font-weight: bolder;
        margin: 10px;
        color: rgb(66, 199, 255);
    }
</style>