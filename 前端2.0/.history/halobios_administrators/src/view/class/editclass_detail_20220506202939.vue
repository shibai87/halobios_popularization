<template>
    <div id="editclass">
        <el-header>
            <el-page-header @back="goBack" content="详情页面">
            </el-page-header>
        </el-header>
        <el-main>
            <el-form :model="ruleForm" ref="ruleForm" label-width="220px" class="demo-ruleForm">
                <el-form-item label="活动主题" prop="class_title">
                    <el-input v-model="ruleForm.video_title" placeholder="请输入该科普知识的生物学名称">{{}}</el-input>
                </el-form-item>

                <el-form-item label="活动介绍" prop="class_content">
                    <el-input type="textarea" v-model="ruleForm.video_info"></el-input>
                </el-form-item>
                <el-form-item label="(已上传的)选取视频">
                    <div v-show="judge_class" class="class_video"> <video controls :src="Video()"
                            class="class_video"></video></div>
                </el-form-item>
                <div class="video_file_name">视频文件名： {{ruleForm.video_file_name}}</div>
                <el-form-item label="(新编辑的)活动视频" prop="class_video">
                    <el-upload class="upload-demo" ref="upload" action="http://192.168.1.105:8083/video/updatavideo"
                        :on-preview="handlePreview" :on-remove="handleRemove" :limit="1" :on-exceed="handleExceed"
                        :on-success="handleSuccess" :on-error="handleError" :before-remove="beforeRemove"
                        :on-change="handleChange" :before-upload="beforeUpload" :file-list="fileList" :data="ruleForm"
                        name="file" :auto-upload="false" accept="video/*">
                        <el-button v-if="judge_class" slot="trigger">重新选取视频</el-button>
                        <el-button v-else slot="trigger">选取视频</el-button>
                        <div slot="tip" class="el-upload__tip">只能上传一个视频</div>
                    </el-upload>
                </el-form-item>
                <el-alert title="提交前必须重新上传图片！" type="warning" :closable="false">
                </el-alert>
                <el-form-item>
                    <el-button @click="keepForm('ruleForm')" v-show="!judge_class">保存</el-button>
                    <el-button type="primary" @click="submitForm()">提交</el-button>
                    <el-button @click="resetForm('ruleForm')" v-show="!judge_class">重置</el-button>
                </el-form-item>
            </el-form>
        </el-main>
    </div>
</template>

<script>
import VideoService from "../../api/VideoService.js"
    export default {
        name: "editclass",
        inject: ["reload"],
        data() {
            return {
                // 判断是否有视频上传
                class_video_name: "",
                ruleForm: {
                    video_title: '',
                    video_info: '',
                    video_file_name: "",
                },
                fileList: [], //文件存储中间量

            };
        },
        methods: {
            goBack() {
                this.$router.push("/index");
            },
            // 获取需编辑的知识详情
            getLoadclass() {
                VideoService.get_video(this.getclass).then((res) =>{this.ruleForm = res});
            },

            Video() {
                this.old_file_name = this.ruleForm.video_file_name
                var url = "http://192.168.1.105:8083/video/" + this.ruleForm.video_file_name
                console.log(url)
                return url
            },

            // 提交
            submitForm() {
                this.ruleForm.video_upload = 1
                this.ruleForm.old_file_name = this.old_file_name
                if (this.ruleForm.video_title && this.ruleForm.video_info && this.video_video_name) {
                    this.$axios.get("http://192.168.1.105:8083/video/get_videoTitle?video_title=" + this
                            .ruleForm.video_title)
                        .then((res) => {
                            if (res.data.msg == '查询成功' && res.data.data != this.getclass) {
                                this.$message({
                                    message: "标题已存在，请重新为科普小课堂重新命名!",
                                    type: "warning",
                                });
                            } else {
                                this.$refs.upload.submit();
                                this.$router.push("/index")
                            }
                        })
                } else {
                    if (!this.video_video_name) {
                        this.$message({
                            message: "请上传视频!",
                            type: "warning",
                        });
                    } else {
                        this.$message({
                            message: "请确认信息填写完毕!",
                            type: "warning",
                        });
                    }

                }
            },
            keepForm() {
                this.ruleForm.video_upload = 0
                this.ruleForm.old_file_name = this.old_file_name
                if (this.video_video_name) {
                    this.$refs.upload.submit();
                    console.log(this.fileList)
                } else {
                    console.log(this.ruleForm)
                    this.$message({
                        message: "请上传视频!",
                        type: "warning",
                    });
                }

            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },

            // 
            handleExceed() {
                this.$message.warning(`当前限制选择 1个文件`);
            },
            beforeRemove(file) {
                this.$confirm(`确定移除 ${file.name}？`);
                this.video_video_name = null
                console.log(this.video_video_name);
            },

            handleRemove(file, fileList) {
                console.log(file, fileList);
            },
            handlePreview(file) {
                console.log(file);
                this.judge_video = 1
            },
            beforeAvatarUpload(file) {
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
                    this.$message.error("上传头像图片大小不能超过 100MB!");
                }
                return isLt100M;
            },
            handleChange(file) {
                this.video_video_name = file.name
                console.log(this.video_video_name);
            },
            beforeUpload(val) {
                this.judge_video = 1
                console.log(val);
            },
            handleSuccess() {
                this.$message.success("上传成功！");
            },
            handleError() {
                this.$message.error("上传失败！");
            },
        },
        computed: {
            judge_class() {
                return JSON.parse(sessionStorage.getItem('judgeclass'));
            },
            getclass() {
                return JSON.parse(sessionStorage.getItem('class_id'))
            },
        },
        mounted() {
            this.getLoadclass()
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