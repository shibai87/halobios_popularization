// 问题：1、分类id表查询未完成，暂用id=1
<template>
    <div>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="150px" class="demo-ruleForm">
            <el-form-item label="科普知识分类名称" prop="knowledge_title">
                <el-input v-model="ruleForm.knowledge_title"></el-input>
            </el-form-item>
            <el-form-item label="科普知识分类关键字" prop="knowledge_sort_id">
                <el-select v-model="ruleForm.knowledge_sort_id" placeholder="请选择第一级分类">
                    <el-option label="1" value="1"></el-option>
                    <el-option label="2" value="2"></el-option>
                    <el-option label="3" value="3"></el-option>
                    <el-option label="4" value="4"></el-option>
                </el-select>
                <el-select v-model="ruleForm.knowledge_sort_id" placeholder="请第二级分类">
                    <el-option label="区域一" value="shanghai"></el-option>
                    <el-option label="区域二" value="beijing"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="科普知识分类内容" prop="knowledge_content">
                <el-input type="textarea" v-model="ruleForm.knowledge_content"></el-input>
            </el-form-item>

            <el-form-item label="科普知识分类图片" prop="knowledge_img">
                <!-- <el-upload
            class="upload-demo"
            ref="upload"
            action="http://localhost:8083/sort/newsort"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :limit="1"
            :on-exceed="handleExceed"
            :on-success="handleSuccess"
            :on-error="handleError"
            :before-remove="beforeRemove"
            :before-upload="beforeUpload"
            :file-list="fileList"
            :data="ruleForm"
            name="file"
            :auto-upload="false"
             accept="image/*"
          >
            <el-button slot="trigger" type="primary">选取文件</el-button>
          </el-upload> -->
                <el-upload action="http://localhost:8083/sort/newsort" list-type="picture-card" :auto-upload="false">
                    <i slot="default" class="el-icon-plus"></i>
                    <div slot="file" slot-scope="{file}">
                        <img class="el-upload-list__item-thumbnail" :src="file.url" alt="">
                        <span class="el-upload-list__item-actions">
                            <span class="el-upload-list__item-preview" @click="handlePictureCardPreview(file)">
                                <i class="el-icon-zoom-in"></i>
                            </span>
                            <span v-if="!disabled" class="el-upload-list__item-delete" @click="handleDownload(file)">
                                <i class="el-icon-download"></i>
                            </span>
                            <span v-if="!disabled" class="el-upload-list__item-delete" @click="handleRemove(file)">
                                <i class="el-icon-delete"></i>
                            </span>
                        </span>
                    </div>
                </el-upload>
                <el-dialog :visible.sync="dialogVisible">
                    <img width="100%" :src="dialogImageUrl" alt="">
                </el-dialog>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
                <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                knowledge_img: "",
                ruleForm: {
                    // 
                    knowledge_title: '',
                    knowledge_sort_id: '',
                    knowledge_content: '',
                },
                fileList: [], //文件存储中间量
                rules: {
                    knowledge_title: [{
                        required: true,
                        message: '科普知识分类名称',
                        trigger: 'blur'
                    }, ],
                    knowledge_sort_id: [{
                        required: true,
                        message: '请选择科普知识分类关键字',
                        trigger: 'change'
                    }],
                    knowledge_content: [{
                        required: true,
                        message: '请选择科普知识分类内容',
                        trigger: 'blur'
                    }],
                    //   
                    dialogImageUrl: '',
                    dialogVisible: false,
                    disabled: false

                },

            };
        },
        methods: {
            submitForm(formName) {

                console.log(this.ruleForm)
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.$refs.upload.submit();
                        // this.axios
                        //     .get("http://localhost:8083/sort/newsort?knowledge_sort_id=" + "1" 
                        //     +"&knowledge_title=" + this.ruleForm.knowledge_title 
                        //     + "&knowledge_content=" +this.ruleForm.knowledge_content+ "&knowledge_img=" +this.ruleForm.knowledge_img )
                        //     // + "&knowledge_img=" + this.ruleForm.knowledge_img
                        //     .then((res) => {
                        //         console.log(res.data)
                        //     })
                    } else {
                        console.log('error submit!!');
                        return false;
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
                    this.$message.error("上传头像图片大小不能超过 100MB!");
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
            // 

            handlePictureCardPreview(file) {
                this.dialogImageUrl = file.url;
                this.dialogVisible = true;
            },
            handleDownload(file) {
                console.log(file);
            }
        }
    }
</script>

<style>
    /* .el-upload {
        display: inline-block;
        
    }
    .el-upload-list{
        display: inline-block;
        width: 70%;
        height: 40px;
    } */
</style>