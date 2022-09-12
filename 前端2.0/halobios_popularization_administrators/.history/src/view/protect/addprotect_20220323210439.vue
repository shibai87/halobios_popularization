// 问题：1、分类id表查询未完成，暂用id=1
<template>
    <div id="addSort">
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="150px" class="demo-ruleForm">
            <el-form-item label="活动名称" prop="activity_title">
                <el-input v-model="ruleForm.activity_title"></el-input>
            </el-form-item>
           <el-form-item label="活动关键字" prop="activity_key">
                <el-input v-model="ruleForm.activity_key"></el-input>
            </el-form-item>
            <el-form-item label="活动介绍" prop="activity_intro">
                <el-input type="textarea" v-model="ruleForm.activity_intro"></el-input>
            </el-form-item>
            <el-form-item label="活动时间">
                <el-col :span="11">
                    <el-date-picker type="date" placeholder="选择开始日期" v-model="ruleForm.activity_begin_time"
                        style="width: 100%;">
                    </el-date-picker>
                </el-col>
                <el-col class="line" :span="2">-</el-col>
                <el-col :span="11">
                     <el-date-picker type="date" placeholder="选择结束日期" v-model="ruleForm.activity_end_time"
                        style="width: 100%;">
                    </el-date-picker>
                    <!-- <el-time-picker placeholder="选择时间" v-model="ruleForm.activity_end_time" style="width: 100%;">
                    </el-time-picker> -->
                </el-col>
            </el-form-item>
             <el-form-item label="活动视频" prop="activity_file">
                <el-upload class="upload-demo" ref="upload" action="http://localhost:8083/activity/newactivity"
                    :on-preview="handlePreview" :on-remove="handleRemove" :limit="1" :on-exceed="handleExceed"
                    :on-success="handleSuccess" :on-error="handleError" :before-remove="beforeRemove"
                    :before-upload="beforeUpload" :file-list="fileList" :data="ruleForm" name="file"
                    :auto-upload="false" accept="image/*">
                    <el-button slot="trigger">请上传一个视频文件</el-button>
                </el-upload>
            </el-form-item>
            <el-form-item>
                 <el-button @click="keepForm('ruleForm')">保存</el-button>
                <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
                <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    export default {
        name: "addSort",
        inject: ["reload"],
        data() {
            return {
                ruleForm: {
                    activity_title: '',
                    activity_key: '',
                    activity_intro: '',
                    activity_begin_time: '',
                    activity_end_time: '',
                },
                fileList: [], //文件存储中间量
                rules: {
                    activity_title: [{
                        required: true,
                        message: '活动名称',
                        trigger: 'blur'
                    }, ],
                    activity_key: [{
                        required: true,
                        message: '活动关键字',
                        trigger: 'change'
                    }],
                    activity_intro: [{
                        required: true,
                        message: '活动介绍',
                        trigger: 'blur'
                    }],
                    activity_begin_time: [{
                        type: 'date',
                        required: true,
                        message: '请开始选择日期',
                        trigger: 'change'
                    }],
                    activity_end_time: [{
                        type: 'date',
                        required: true,
                        message: '请结束选择时间',
                        trigger: 'change'
                    }],
                },

            };
        },
        methods: {
            submitForm(formName) {
                console.log(this.file)
                this.$refs[formName].validate((valid) => {
                    if (!valid) {
                        this.$message({
                            message: "请确认信息填写完毕!",
                            type: "warning",
                        });
                    }
                    if (valid) {
                        this.$refs.upload.submit();
                        this.reload();
                    }

                });

            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },

            // 
            handleExceed() {
                this.$message.warning(`当前限制选择 1个文件`);
            },
            beforeRemove(file) {
                return this.$confirm(`确定移除 ${file.name}？`);
            },

            handleRemove(file, fileList) {
                console.log(file, fileList);
            },
            handlePreview(file) {
                console.log(file);
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
                    this.$message.error("上传文件大小不能超过 100MB!");
                }
                return isLt100M;
            },
            beforeUpload(val) {
                console.log(val);
            },
            handleSuccess() {
                this.$message.success("上传成功！");
            },
            handleError() {
                this.$message.error("上传失败！");
            },

        }
    }
</script>

<style>
    #addSort .upload-demo {
        width: 300px;
    }
</style>