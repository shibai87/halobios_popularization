<template>
    <div id="activity_details">
        <van-nav-bar title="小课堂详情" fixed left-arrow @click-left="pageBack" />

        <div class="classroom_details_main">
            <h1>{{ruleForm.video_title}}</h1>
            <video controls :src="Video()" width="400" height="200" class="classroom_video"></video>

            <div class="classroom_details_content">
                {{ruleForm.video_info}}
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
        name: 'classroom_details',
        components:{
            Operaion,
            Comment
        },
        data() {
            return {
                ruleForm: {
                    video_sort_id: "",
                    video_title: '',
                    video_content: '',
                    video_img: "",
                },
                // 分类
                sort: [],
            }
        },
        methods: {
            pageBack() {
               this.$router.push("/")
            },
            // 获取需编辑的知识详情
            getLoadclassroom() {
                this.axios.get("http://192.168.1.105:8083/video/get_video?video_id=" + this.getclassroom)
                    .then((res) => {
                        console.log(res.data)
                        this.ruleForm = res.data
                        //   console.log( this.activitys)
                    });
            },
        
              Video() {
        var url = "http://192.168.1.105:8083/video/" + this.ruleForm.video_file_name
        console.log(url)
        return url
      },
        },
        computed: {
            getclassroom() {
                return JSON.parse(sessionStorage.getItem('source_id'));
            },
            getSortListLastId() {
                return JSON.parse(sessionStorage.getItem('sort_id_list_last'));
            },
        },
        mounted() {
            this.getLoadclassroom()
        }
    }
</script>

<style>
    /*  */
    #classroom_details .van-nav-bar__title {
        height: 30px;
        line-height: 30px;
        font-size: 20px;
        text-align: center;

    }

    #classroom_details .van-nav-bar__content {
        height: 70px;
    }

    #classroom_details .van-nav-bar__right {
        right: 5px;
        font-size: 20px;
    }

    #classroom_details .van-icon {
        color: #000;
        font-size: 35px;
        margin-right: 5px;
    }
</style>
<style scoped>
    /*  */
    .classroom_details_main {
        text-align: center;
        margin: 60px auto 0;
    }

    .classroom_details_main h1 {
        margin-top: 80px;
        font-size: 30px;
    }


    .classroom_details_content {
        text-indent: 2em;
        font-size: 25px;
        margin: 10px;
        text-align: left;
    }

    .classroom_details_classroom_key {
        font-size: 20px;
        text-align: left;
        font-weight: bolder;
        margin: 10px;
        color: rgb(66, 199, 255);
    }
</style>