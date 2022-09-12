<template>
    <div id="editclass">
        <el-header>
            <el-page-header @back="goBack" content="详情页面">
            </el-page-header>
        </el-header>
        <el-main>
            <el-form :model="ruleForm" label-width="220px" class="demo-ruleForm">
                <el-form-item label="活动主题" prop="class_title">
                    <el-input v-model="ruleForm.video_title" placeholder="请输入该科普知识的生物学名称"></el-input>
                </el-form-item>
                <el-form-item label="活动介绍" prop="class_content">
                    <el-input type="textarea" v-model="ruleForm.video_info"></el-input>
                </el-form-item>
                <el-form-item label="(已上传的)选取视频">
                    <div class="class_video" v-show="ruleForm.video_file_name"> <video controls :src="Video()"
                            class="class_video"></video></div>
                </el-form-item>
                <div class="class_file_name">视频文件名： {{ ruleForm.video_file_name }}</div>
                <el-form-item label="(新编辑的)活动视频">
                    <el-upload class="upload-demo" ref="uploadRef"
                        :action="action" :data="ruleForm" :limit="1"
                        :on-success="handleSuccess" :on-error="handleError"
                        :on-change="handleChange" :before-upload="beforeUpload" :file-list="fileList" name="file"
                        :auto-upload="false" accept="video/*">
                        <template #trigger>
                            <el-button v-if="judge_video">重新选取视频</el-button>
                            <el-button v-else>选取视频</el-button>
                        </template>
                        <div slot="tip" class="el-upload__tip">只能上传一个视频</div>
                    </el-upload>
                </el-form-item>

                <el-alert title="提交前必须重新上传视频！" type="warning" :closable="false" />
                <el-form-item>
                    <el-button @click="keepForm" v-show="!judge_video">保存</el-button>
                    <el-button type="primary" @click="submitUpload()">提交</el-button>
                    <el-button @click="resetForm" v-show="!judge_video">重置</el-button>
                </el-form-item>
            </el-form>
        </el-main>
    </div>
</template>

<script setup lang="ts">
import { onMounted } from "vue"
import { ref, reactive } from "vue"
import { ElMessage } from 'element-plus'
import Constant from "../../assets/constant.js"
import VideoService from "../../api/VideoService.js"
// import { useStore } from 'vuex'
import { useRouter } from "vue-router"
import { inject } from "vue"
import type { UploadInstance } from 'element-plus'
import type { FormInstance } from 'element-plus'
const refresh = inject("reload")
const uploadRef = ref<UploadInstance>()
// const store = useStore()
const router = useRouter()
// const upload = ref('')
const ruleForm = ref<any>({})
const action = ref(Constant.URL + "/video/updatavideo")
const activity_time = ref('')
// 判断是否有视频上传
const video_video_name = ref("")
// // 详情
const fileList = reactive([]) //文件存储中间量
const judge_video = ref(sessionStorage.getItem('judgeclass'))
const old_file_name=ref("")


onMounted(() => {
    VideoService.get_video(JSON.parse(sessionStorage.getItem('class_id')||"0")).then((res) => {
        console.log(res)
        ruleForm.value = res
    })
})
const goBack = () => router.push("/index")
const Video = () => Constant.URL + "/video/" + ruleForm.value.video_file_name;

const submitUpload = () => {
    ruleForm.value.video_upload = 1
    ruleForm.value.old_file_name = old_file_name.value
    if (ruleForm.value.video_title && ruleForm.value.video_info && video_video_name.value) {
            VideoService.get_videoTitle(ruleForm.value.video_title).then((res) => {
                if (res.msg == '查询成功' && res.data != JSON.parse(sessionStorage.getItem('class_id')||"")) {
                    ElMessage({
                        message: "标题已存在，请重新为科普知识重新命名!",
                        type: "warning",
                    });
                } else {
                    console.log(ruleForm)
                    uploadRef.value!.submit()
                    router.push("/index")
                }
            })
    } else {
        if (!video_video_name.value) {
            ElMessage({
                message: "请确认已重新上传了图片!",
                type: "warning",
            });
        } else {
            ElMessage({
                message: "请确认信息填写完毕!",
                type: "warning",
            });
        }
    }

}

const keepForm = () => {
    ruleForm.value.old_file_name = old_file_name.value
    ruleForm.value.video_upload = 0
    if (video_video_name.value) {
        uploadRef.value!.submit()
        router.push("/index")
    } else {
        ElMessage({
            message: "请上传图片!",
            type: "warning",
        });
    }

}

const resetForm = (formEl: FormInstance | undefined) => {
    if (!formEl) return
    formEl.resetFields()
}

const handleChange = (file) => {
    video_video_name.value = file.name
    console.log(video_video_name);
}
const beforeUpload = (val) => {
    //    judge_video.value=1
    console.log(val);
}
const handleSuccess = () => {
    if (ruleForm.value.video_upload == 0) {
        ElMessage.success("保存成功！");
    }
    else {
        ElMessage.success("上传成功！");
    }
}
const handleError = () => {
    if (ruleForm.value.video_upload == 0) {
        ElMessage.success("保存失败！");
    }
    else {
        ElMessage.success("上传失败！");
    }
}
</script>

<style>
    /* 布局 */
    #editclass .el-header {
        background-color: rgb(99, 175, 255);
        padding: 20px 10px;
    }

    #editclass .el-main {
        background-color: #E9EEF3;
        color: #333;
        text-align: center;
        height: 660px;
    }

    /* 头部 */
    /* #editclass  .el-page-header{
   
} */
    #editclass .el-page-header__title {
        font-size: 25px;
    }

    #editclass .el-icon-back {
        font-size: 30px;
    }

    #editclass .el-page-header__content {
        font-size: 25px;
    }

    #editclass .upload-demo {
        width: 300px;
    }

    /* 主体 */
    #editclass .avatar-uploader .el-upload {
        border: 1px dashed #d9d9d9;
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
    }

    #editclass .avatar-uploader .el-upload:hover {
        border-color: #409EFF;
    }

    #editclass .avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 178px;
        height: 178px;
        line-height: 178px;
        text-align: center;
    }

    #editclass .avatar {
        width: 178px;
        height: 178px;
        display: block;
    }

    #editclass .el-alert {
        height: 30px;
        width: 1200px;
        margin: 0 auto 2px;
    }
</style>
<style scoped>
    /* 分类 */
    .editclass_sort {
        background-color: #fff;
        text-align: left;
        padding: 0 10px;
    }

    /* 图片 */
    .class_video {
        width: 100px;
        margin-left: 0;
        float: left;
    }

    .class_video video {
        width: 100px;
        height: 100px;
    }

    .video_file_name {
        text-align: left;
        margin: 0 220px 10px;
    }
</style>