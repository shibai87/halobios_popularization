<template>
    <div id="editactivity_detail">
        <el-header>
            <el-page-header @back="goBack" content="详情页面">
            </el-page-header>
        </el-header>
        <el-main>
            <el-form :model="ruleForm" ref="ruleForm" label-width="150px" class="demo-ruleForm">
                <el-form-item label="活动主题" prop="activity_title">
                    <el-input v-model="ruleForm.activity_title" placeholder="请输入该科普知识的生物学名称">{{}}</el-input>
                </el-form-item>

                <el-form-item label="活动介绍" prop="activity_content">
                    <el-input type="textarea" v-model="ruleForm.activity_info"></el-input>
                </el-form-item>
                <el-form-item label="(已上传的)选取视频" v-show="ruleForm.activity_file_name">
                    <div class="activity_video"> <video controls :src="Video()" class="activity_video"></video></div>
                </el-form-item>
                 <div class="activity_file_name">视频文件名： {{ruleForm.activity_file_name}}</div>
                <el-form-item label="(新编辑的)活动视频" prop="activity_video">
                    <el-upload class="upload-demo" ref="upload"
                        action="http://192.168.1.105:8083/activity/updataactivity" :on-preview="handlePreview"
                        :on-remove="handleRemove" :limit="1" :on-exceed="handleExceed" :on-success="handleSuccess"
                        :on-error="handleError" :before-remove="beforeRemove" :on-change="handleChange"
                        :before-upload="beforeUpload" :file-list="fileList" :data="ruleForm" name="file"
                        :auto-upload="false" accept="video/*">
                        <el-button v-if="judge_activity" slot="trigger">重新选取视频</el-button>
                        <el-button v-else slot="trigger">选取视频</el-button>
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
                    <div class="activity_video">{{ruleForm.activity_begin_time}}至{{ruleForm.activiy_end_time}}</div>
                </el-form-item>
                <el-form-item label="新活动时间">
                    <el-date-picker v-model="activity_time" type="daterange" range-separator="至"
                        start-placeholder="开始日期" end-placeholder="结束日期" value-format="yyyy-MM-dd" clearable
                        @change="time(activity_time)">
                    </el-date-picker>
                </el-form-item>
                <el-alert title="提交前必须重新上传图片！" type="warning" :closable="false">
                </el-alert>
               <div class="editactivity_button">
                    <el-button @click="keepForm('ruleForm')" v-show="!judge_activity">保存</el-button>
                    <el-button type="primary" @click="submitForm()">提交</el-button>
                    <el-button @click="resetForm('ruleForm')" v-show="!judge_activity">重置</el-button>
               </div>
                   
                
            </el-form>
        </el-main>
    </div>
</template>

<script>
import ActivityService from "../../api/ActivityService.js"
    export default {
        name: "editactivity",
        inject: ["reload"],
        data() {
            return {
                activity_time: [],
                // 判断是否有视频上传
                activity_video_name: "",
                ruleForm: {
                    activity_id:"",
                    activity_title: '',
                    activity_info: '',
                    activity_file_name: "",
                    activity_form: "",
                    activity_place: "",
                    activity_begin_time: "",
                    activiy_end_time: "",
                    old_file_name:"",
                },
                fileList: [], //文件存储中间量
                
            };
        },
        methods: {
            goBack() {
                this.$router.push("/index");
            },
             time(activity_time) {
                if(activity_time){
                var myDate = new Date();
                var timed = myDate.toLocaleDateString();
                var time2 = new Date(activity_time[0]);
                var time1 = new Date(timed);
                if (time2.getTime() >= time1.getTime()) {
                    this.ruleForm.activity_begin_time = activity_time[0];
                    this.ruleForm.activiy_end_time = activity_time[1];
                } else {
                    this.$message({
                        message: "开始时间不得早于当今时间，请重新选择",
                        type: "warning",
                    });
                } 
                }
            },
            // 获取需编辑的知识详情
            getLoadactivity() {
                console.log(this.getactivity)
                this.axios.get("http://192.168.1.105:8083/activity/get_activity?activity_id=" + this.getactivity)
                    ActivityService.get_activity(this.getactivity).then((res) => {
                        // console.log(res.data)
                        this.ruleForm = res.data
                        if(this.ruleForm.activity_form==0||this.ruleForm.activity_form==1){
                   this.ruleForm.activity_form = this.ruleForm.activity_form==0 ?"线上参与": "线下参与";
                    // console.log(this.ruleForm.activity_form)
                }
                         

                    });
            },

            Video() {
                this.old_file_name=this.ruleForm.activity_file_name
                var url = "http://192.168.1.105:8083/activity/" + this.ruleForm.activity_file_name
                console.log(url)
                return url
            },

            // 提交
            submitForm() {
             console.log(this.ruleForm)
                this.ruleForm.activity_upload= 1
                this.ruleForm.old_file_name=this.old_file_name
                if (this.ruleForm.activity_title && this.ruleForm.activity_info && this.activity_video_name && this
                    .ruleForm.activity_form!=null && this.ruleForm.activity_begin_time && this.ruleForm.activiy_end_time) {
                    //          console.log(this.ruleForm.activity_title,this.ruleForm.activity_info,this.activity_video_name,this
                    // .ruleForm.activity_form,this.ruleForm.activity_begin_time,this.ruleForm
                    // .activiy_end_time)
                         this.ruleForm.activity_form = this.ruleForm.activity_form=="线下参与" ? 1 : 0;
                        if(this.ruleForm.activity_place||this.ruleForm.activity_form==0){
                              this.$axios.get("http://192.168.1.105:8083/activity/get_activityTitle?activity_title=" + this
                            .ruleForm.activity_title)
                        .then((res) => {
                            if (res.data.msg == '查询成功'&&res.data.data!=this.getactivity) {
                                this.$message({
                                    message: "标题已存在，请重新为科普知识命名!",
                                    type: "warning",
                                });
                            } else {
                                this.$refs.upload.submit();
                                this.$router.push("/index")
                                // setTimeout(this.$router.push("/index"),1000)
                            }
                        })
                        }else{
                              this.$message({
                            message: "您已选择活动未线下形式，所有请填写活动地点!",
                            type: "warning",
                        });
                        }
                   
                } else {
                    if (!this.activity_video_name) {
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
                  this.ruleForm.old_file_name=this.old_file_name
                console.log(this.ruleForm.activity_form )
                if(this.ruleForm.activity_form){
                    this.ruleForm.activity_form = this.ruleForm.activity_form=="线下参与" ? 1 : 0;
                    console.log(this.ruleForm.activity_form)
                }
                 
                this.ruleForm.activity_upload = 0
                if (this.activity_video_name) {
                    this.$refs.upload.submit();
                    this.$router.push("/index")
                    // console.log(this.fileList)
                } else {
                    // console.log(this.ruleForm)

                    this.$message({
                        message: "请上传图片!",
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
                this.activity_video_name = null
                console.log(this.activity_video_name);
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
                this.activity_video_name = file.name
                console.log(this.activity_video_name);
            },
            beforeUpload(val) {
                this.judge_video = 1
                console.log(val);
            },
            handleSuccess() {
                if(this.ruleForm.activity_upload==0){
                    this.$message.success("保存成功！");
                }
                else{
                    this.$message.success("上传成功！");
                }
                


            },
            handleError() {
                 if(this.ruleForm.activity_upload==0){
                    this.$message.success("保存失败！");
                }
                else{
                    this.$message.success("上传失败！");
                }
            },
        },
        computed: {
            judge_activity() {
                return JSON.parse(sessionStorage.getItem('judgeactivity'));
            },
            getactivity() {
                return JSON.parse(sessionStorage.getItem('activity_id'))
            },
        },
        mounted() {
            this.getLoadactivity()
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
        background-color:#E9EEF3;
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
    .el-upload-list__item-name{
        width: 100px;
    }
    #editactivity .el-form-item{
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
    .editactivity_button{
        margin-top: 10px;
    }
    .activity_file_name{
        text-align:left;
        margin:0 150px 10px;
    }
</style>