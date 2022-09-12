<template>
    <div id="editKnowledge_detail">
        <el-header>
            <el-page-header @back="goBack" content="详情页面">
            </el-page-header>
        </el-header>
        <el-main>
            <el-form :model="ruleForm" label-width="220px" class="demo-ruleForm">
                <el-form-item label="活动主题" prop="activity_title">
                    <el-input v-model="ruleForm.activity_title" placeholder="请输入该科普知识的生物学名称">{{}}</el-input>
                </el-form-item>
                <el-form-item label="活动介绍" prop="activity_content">
                    <el-input type="textarea" v-model="ruleForm.activity_info"></el-input>
                </el-form-item>
                <el-form-item label="(已上传的)选取视频">
                    <div class="activity_video" v-show="ruleForm.activity_file_name"> <video controls :src="Video()"
                            class="activity_video"></video></div>
                </el-form-item>
                <div class="activity_file_name">视频文件名： {{ ruleForm.activity_file_name }}</div>
                <el-form-item label="(新编辑的)活动视频">
                    <el-upload class="upload-demo" ref="uploadRef"
                        :action="action" :data="ruleForm" :limit="1" 
                        :on-success="handleSuccess" :on-error="handleError" 
                        :on-change="handleChange" :before-upload="beforeUpload" :file-list="fileList" name="file"
                        :auto-upload="false" accept="video/*">
                        <template #trigger>
                            <el-button v-if="judge_activity">重新选取视频</el-button>
                            <el-button v-else>选取视频</el-button>
                        </template>
                        <div slot="tip" class="el-upload__tip">只能上传一个视频</div>
                    </el-upload>   
                </el-form-item>
                <el-form-item label="活动形式">
                    <el-radio-group v-model="ruleForm.activity_form">
                        <el-radio label="线上参与"></el-radio>
                        <el-radio label="线下参与"></el-radio>
                    </el-radio-group>
                </el-form-item>
                <div v-if="ruleForm.activity_form == `线下参与`">
                    <el-form-item label="活动名称">
                        <el-input v-model="ruleForm.activity_place"></el-input>
                    </el-form-item>
                </div>
                <el-form-item label="原活动时间" v-show="ruleForm.activity_begin_time">
                    <div class="activity_video">{{ ruleForm.activity_begin_time }}至{{ ruleForm.activiy_end_time }}</div>
                </el-form-item>
                <el-form-item label="新活动时间">
                    <el-date-picker v-model="activity_time" type="daterange" range-separator="至"
                        start-placeholder="开始日期" end-placeholder="结束日期" value-format="yyyy-MM-dd" clearable
                        @change="time(activity_time)">
                    </el-date-picker>
                </el-form-item>
                <el-alert title="提交前必须重新上传图片！" type="warning" :closable="false" />
                <el-form-item>
                      <div class="editactivity_button">
                    <el-button @click="keepForm" v-show="!judge_activity">保存</el-button>
                    <el-button type="primary" @click="submitUpload()">提交</el-button>
                    <el-button @click="resetForm" v-show="!judge_activity">重置</el-button>
               </div>
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
import ActivityService from "../../api/ActivityService.js"
// import { useStore } from 'vuex'
import { useRouter } from "vue-router"
import { inject } from "vue"
const refresh = inject("reload")
import type { UploadInstance } from 'element-plus'
import type { FormInstance } from 'element-plus'

const uploadRef = ref<UploadInstance>()
// const store = useStore()
const router = useRouter()
// const upload = ref('')
const ruleForm = ref<any>({
    activity_form:"",
    activity_form:""
})
const action = ref(Constant.URL + "/activity/updataactivity")
const activity_time = ref('')
// 判断是否有视频上传
const activity_video_name = ref("")
// // 详情
const fileList = reactive<any>([]) //文件存储中间量
const judge_activity = ref(sessionStorage.getItem('judgeactivity'))
const old_file_name=ref("")

onMounted(() => {
    ActivityService.get_activity(JSON.parse(sessionStorage.getItem('activity_id')||"")).then((res) => {
        console.log(res)
        ruleForm.value = res
        if (ruleForm.value.activity_form == 0 || ruleForm.value.activity_form == 1) {
            ruleForm.value.activity_form = ruleForm.value.activity_form == 0 ? "线上参与" : "线下参与";
        }
    })
})
const goBack = () => router.push("/index")
const Video = () => Constant.URL + "/activity/" + ruleForm.value.activity_file_name;
const time=(activity_time)=>{
                if(activity_time.value){
                var myDate = new Date();
                var timed = myDate.toLocaleDateString();
                var time2 = new Date(activity_time[0]);
                var time1 = new Date(timed);
                if (time2.getTime() >= time1.getTime()) {
                    ruleForm.value.activity_begin_time = activity_time[0];
                    ruleForm.value.activiy_end_time = activity_time[1];
                } else {
                    ElMessage({
                        message: "开始时间不得早于当今时间，请重新选择",
                        type: "warning",
                    });
                } 
                }
            }
const submitUpload = () => {
    ruleForm.value.activity_upload = 1
    ruleForm.value.old_file_name = old_file_name.value
    if (ruleForm.value.activity_title && ruleForm.value.activity_info && activity_video_name.value &&
        ruleForm.value.activity_form != null && ruleForm.value.activity_begin_time && ruleForm.value.activiy_end_time) {
        ruleForm.value.activity_form = ruleForm.value.activity_form == "线下参与" ? 1 : 0;
        if (ruleForm.value.activity_place || ruleForm.value.activity_form == 0) {
            ActivityService.get_activityTitle(ruleForm.value.activity_title).then((res) => {
                if (res.msg == '查询成功' && res.data != JSON.parse(sessionStorage.getItem('activity_id')||"")) {
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
            ElMessage({
                message: "您已选择活动未线下形式，所有请填写活动地点!",
                type: "warning",
            });
        }
    } else {
        if (!activity_video_name.value) {
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
    if (ruleForm.value.activity_form) {
        ruleForm.value.activity_form = ruleForm.value.activity_form == "线下参与" ? 1 : 0;
    }
    ruleForm.value.activity_upload = 0
    if (activity_video_name.value) {
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

const beforeAvatarUpload = (file) => {
    const isLt100M =
        file.size /
        1024 /
        1024 /
        1024 /
        1024 /
        1024 /
        1024 /
        1024 /
        1024 /
        1024 /
        1024 <
        10;
    if (!isLt100M) {
        ElMessage.error("上传头像图片大小不能超过 100MB!");
    }
    return isLt100M;
}
const handleChange = (file) => {
    activity_video_name.value = file.name
    console.log(activity_video_name);
}
const beforeUpload = (val) => {
    //    judge_video.value=1
    console.log(val);
}
const handleSuccess = () => {
    if (ruleForm.value.activity_upload == 0) {
        ElMessage.success("保存成功！");
    }
    else {
        ElMessage.success("上传成功！");
    }
}
const handleError = () => {
    if (ruleForm.value.activity_upload == 0) {
        ElMessage.success("保存失败！");
    }
    else {
        ElMessage.success("上传失败！");
    }
}
</script>

<style>
/* 布局 */
#editactivity_detail .el-header {
    background-color: rgb(99, 175, 255);
    padding: 20px 10px;
}

#editactivity_detail .el-main {
    background-color: #E9EEF3;
    color: #000;
    text-align: center;
    height: 660px;
}

/* 头部 */
#editactivity_detail .el-page-header__title {
    font-size: 25px;
}

#editactivity_detail .el-icon-back {
    font-size: 30px;
}

#editactivity_detail .el-page-header__content {
    font-size: 25px;
}

#editactivity_detail .upload-demo {
    width: 150px;
}

/* 主体 */
/* #editactivity .el-form-item__label{
        width: 100px;
    } */
.el-upload-list__item-name {
    width: 100px;
}

#editactivity .el-form-item {
    margin-bottom: 15px;
}

#editactivity .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
}

#editactivity .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
}

#editactivity .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
}

#editactivity .avatar {
    width: 178px;
    height: 178px;
    display: block;
}

/* 警示条 */
#editactivity .el-alert {
    height: 30px;
    width: 800px;
    margin: 0 auto 2px;
}

#editactivity .el-date-editor {
    float: left;
}
</style>

<style scoped>
/* 分类 */
.editactivity_sort {
    background-color: #fff;
    text-align: left;
    padding: 0 10px;
}

/* 图片 */
.activity_video {
    margin-left: 0;
    float: left;
}

.activity_video video {
    width: 200px;
    height: 100px;
}

.editactivity_button {
    margin-top: 10px;
}

.activity_file_name {
    text-align: left;
    margin: 0 150px 10px;
}
</style>