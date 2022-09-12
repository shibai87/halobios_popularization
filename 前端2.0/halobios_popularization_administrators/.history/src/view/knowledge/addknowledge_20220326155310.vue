// 问题：1、分类id表查询未完成，暂用id=1
<template>
    <div id="addSort">
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="150px" class="demo-ruleForm">
            <el-form-item label="科普知识分类名称" prop="knowledge_title">
                <el-input v-model="ruleForm.knowledge_title"></el-input>
            </el-form-item>
            <el-form-item label="科普知识分类" prop="knowledge_sort_id">
               <Sort></Sort>
            </el-form-item>
            <el-form-item label="科普知识分类内容" prop="knowledge_content">
                <el-input type="textarea" v-model="ruleForm.knowledge_content"></el-input>
            </el-form-item>

            <el-form-item label="科普知识分类图片" prop="knowledge_img">
                <el-upload class="upload-demo" ref="upload" action="http://localhost:8083/knowledge/newsort"
                    :on-preview="handlePreview" :on-remove="handleRemove" :limit="1" :on-exceed="handleExceed"
                    :on-success="handleSuccess" :on-error="handleError" :before-remove="beforeRemove"
                    :before-upload="beforeUpload" :file-list="fileList" :data="ruleForm" name="file"
                    :auto-upload="false" accept="image/*">
                    <el-button slot="trigger">选取文件</el-button>
                    <div slot="tip" class="el-upload__tip">只能上传一个文件</div>
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
import Sort from "../../components/sort.vue"
    export default {
        name:"addSort",
         inject: ["reload"],
             components:{
            Sort
        },
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

        }
    }
</script>

<style>
 #addSort .el-form-item__label::before{

 }
    #addSort .upload-demo {
        width: 300px;
    }
</style>