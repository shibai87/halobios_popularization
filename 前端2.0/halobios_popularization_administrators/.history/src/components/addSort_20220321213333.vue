<template>
    <div>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="150px" class="demo-ruleForm">
            <el-form-item label="科普知识分类名称" prop="knowledge_title">
                <el-input v-model="ruleForm.knowledge_title"></el-input>
            </el-form-item>
            <el-form-item label="科普知识分类关键字" prop="knowledge_key">
                <el-select v-model="ruleForm.knowledge_key" placeholder="请第一级分类">
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
            <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
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
                    knowledge_title: '',
                    knowledge_key: '',
                   knowledge_content: ''
                },
                rules: {
                    knowledge_title: [
                        {
                            required: true,
                            message: '科普知识分类名称',
                            trigger: 'blur'
                        },
                        {
                            min: 3,
                            max: 5,
                            message: '长度在 3 到 5 个字符',
                            trigger: 'blur'
                        }
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
                    }]
                }
            };
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        alert('submit!');
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        }
    }
</script>

<style>

</style>