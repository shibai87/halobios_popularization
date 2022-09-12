<template>
    <div id="editclass">
        <el-form :model="ruleForm" label-width="220px" class="demo-ruleForm">
            <el-form-item label="活动主题" prop="class_title">
                <el-input v-model="ruleForm.video_title" placeholder="请输入该科普知识的生物学名称"></el-input>
            </el-form-item>
            <el-form-item label="活动介绍" prop="class_content">
                <el-input type="textarea" v-model="ruleForm.video_info"></el-input>
            </el-form-item>
            <el-form-item label="活动视频">
                <el-upload class="upload-demo" ref="uploadRef"
                    :action="action" :data="ruleForm" :limit="1" 
                    :on-success="handleSuccess" :on-error="handleError" 
                    :on-change="handleChange" :before-upload="beforeUpload" :file-list="fileList" name="file"
                    :auto-upload="false" accept="video/*">
                    <template #trigger>
                        <el-button>选取视频</el-button>
                    </template>
                    <div slot="tip" class="el-upload__tip">只能上传一个视频</div>
                </el-upload>
            </el-form-item>

            <el-alert title="提交前必须重新上传视频！" type="warning" :closable="false" />
            <el-form-item>
                <el-button @click="keepForm">保存</el-button>
                <el-button type="primary" @click="submitUpload()">提交</el-button>
                <el-button @click="resetForm">重置</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script setup lang="ts">
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
// 判断是否有视频上传
const video_video_name = ref("")
const ruleForm = ref<any>({
    video_title: '',
    video_info: '',
})
const action = ref(Constant.URL + "/video/newvideo")
// // 详情
const fileList = reactive([]) //文件存储中间量

const submitUpload = () => {
    ruleForm.value.video_upload = 1
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
    #addVideo .el-form-item__label::before {
        content: '*';
        color: #F56C6C;
        margin-right: 4px;
    }

    #addVideo .upload-demo {
        width: 300px;
    }
</style>