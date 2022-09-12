<template>
    <div id="activity_details">
        <van-nav-bar title="小课堂详情" fixed left-arrow @click-left="onClickLeft" />
        <van-pull-refresh v-model="isLoading" @refresh="onRefresh">
            <div class="classroom_details_main">
                <h1>{{ruleForm.video_title}}</h1>
                <video controls :src="Video()" width="400" height="200" class="classroom_video"></video>

                <div class="classroom_details_content">
                    {{ruleForm.video_info}}
                </div>
            </div>
            <Operaion></Operaion>
            <Comment></Comment>

        </van-pull-refresh>
    </div>
</template>

<script lang="ts" setup>
import { ref} from "vue"
import { onMounted } from "vue";
import { useRouter } from 'vue-router'

import { Toast } from 'vant';

import Operaion from "../../components/operation.vue"
import Comment from "../../components/comment.vue"
import VideoService from "../../api/VideoService.js"
import Constant from "../../assets/constant.js"
const router = useRouter()

const ruleForm = ref<any>({
    video_sort_id: "",
    video_title: '',
    video_content: '',
    video_img: "",
})
const isLoading = ref<boolean>(false)

const getclassroom = ref(JSON.parse(sessionStorage.getItem('source_id') || ""))
//     //视频路径
const Video = () => Constant.URL + "/video/" + ruleForm.value.video_file_name
onMounted(() => {
    getLoadclassroom()
})
const onClickLeft = () => { router.push("/") }
// 刷新
const onRefresh = () => {
    setTimeout(() => {
        Toast('刷新成功');
        isLoading.value = false;
        getLoadclassroom()
    }, 1000);
}

// 获取需编辑的知识详情
const getLoadclassroom = () => {
    VideoService.get_video(getclassroom.value)
        .then((res) => {
            ruleForm.value = res
        });
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