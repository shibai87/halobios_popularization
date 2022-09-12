// 问题：1、图片非必选项
<template>
    <div>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="150px" class="demo-ruleForm">
            <el-form-item label="科普知识分类名称" prop="knowledge_title">
                <el-input v-model="ruleForm.knowledge_title"></el-input>
            </el-form-item>
            <el-form-item label="科普知识分类关键字" prop="knowledge_key">
                <el-select v-model="ruleForm.knowledge_sort_id" placeholder="请选择第一级分类">
                    <el-option label="海洋动物" value="海洋动物"></el-option>
                    <el-option label="海洋植物" value="海洋植物"></el-option>
                    <el-option label="微生物" value="微生物"></el-option>
                    <el-option label="病毒" value="病毒"></el-option>
                </el-select>
                <el-select v-model="ruleForm.knowledge_key" placeholder="请第二级分类">
                    <el-option label="区域一" value="shanghai"></el-option>
                    <el-option label="区域二" value="beijing"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="科普知识分类内容" prop="knowledge_content">
                <el-input type="textarea" v-model="ruleForm.knowledge_content"></el-input>
            </el-form-item>

            <el-form-item label="科普知识分类图片" prop="knowledge_img">
                <el-input type="file" v-model="ruleForm.knowledge_img" accept="image/*"></el-input>
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
                ruleForm: {
                    // 
                    knowledge_title: '',
                    knowledge_sort_id: '',
                    knowledge_content: '',
                    knowledge_img:""

                },
              
                // 
                rules: {
                    knowledge_title: [{
                            required: true,
                            message: '科普知识分类名称',
                            trigger: 'blur'
                        },
                    ],
                    knowledge_key: [{
                        required: true,
                        message: '请选择科普知识分类关键字',
                        trigger: 'change'
                    }],
                    knowledge_content: [{
                        required: true,
                        message: '请选择科普知识分类内容',
                        trigger: 'blur'
                    }],
                     knowledge_img: [{
                        required: true,
                        message: '请选择科普知识分类内容',
                        trigger: 'blur'
                    }],
                  
                }
            };
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                         this.axios
                    .get("http://localhost:8083/sort/newsort?knowledge_sort_id=" + "1" +
                        "&knowledge_title=" + this.ruleForm.knowledge_title + "&knowledge_content=" + this.ruleForm
                        .knowledge_content + "&knowledge_img=" + "1")
                    .then((res) => {
                        console.log(res.data)
                    })
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
               
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
        }
    }
</script>

<style>
    /* 图片 */
    .avatar-uploader .el-upload {
        border: 1px dashed #d9d9d9;
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;

    }

    .avatar-uploader .el-upload:hover {
        border-color: #409EFF;
    }

    .avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 178px;
        height: 178px;
        line-height: 178px;
        text-align: center;
    }

    .avatar {
        width: 178px;
        height: 178px;
        display: block;
    }

    .el-upload {
        margin-right: 850px;
        background-color: #fff;
    }
</style>