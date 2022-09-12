<template>
    <div id="addSort">
        <el-form :model="ruleForm" ref="ruleForm" label-width="150px" class="demo-ruleForm">
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
                <el-upload class="upload-demo" ref="upload" action="http://192.168.1.105:8083/knowledge/newsort"
                    :on-preview="handlePreview" :on-remove="handleRemove" :limit="1" :on-exceed="handleExceed"
                    :on-success="handleSuccess" :on-error="handleError" :before-remove="beforeRemove"
                    :on-change="handleChange" :before-upload="beforeUpload" :file-list="fileList" :data="ruleForm"
                    name="file" :auto-upload="false" accept="image/*">
                    <el-button slot="trigger">选取图片</el-button>
                    <div slot="tip" class="el-upload__tip">只能上传一个图片</div>
                </el-upload>
            </el-form-item>
            <el-alert title="提交或保存前必须上传图片！" type="warning" :closable="false">
            </el-alert>
            <el-form-item>
                <el-button @click="keepForm()">保存</el-button>
                <el-button type="primary" @click="submitForm()">提交</el-button>
                <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    import Sort from "../../components/sort.vue"
    export default {
        name: "addSort",
        inject: ["reload"],
        components: {
            Sort
        },
        data() {
            return {
                // 判断是否有图片上传
                knowledge_img_name: "",
                ruleForm: {
                    // 
                    knowledge_title: '',
                    knowledge_sort_id: '',
                    knowledge_content: '',
                    knowledge_upload: ''
                },
                fileList: [], //文件存储中间量

            };
        },
        methods: {
            submitForm() {
                this.ruleForm.knowledge_sort_id = this.getSortListLastId
                this.ruleForm.knowledge_upload = 1
                if (this.ruleForm.knowledge_title && this.ruleForm.knowledge_sort_id != null && this.ruleForm
                    .knowledge_content && this.knowledge_img_name) {
                    this.$axios.get("http://192.168.1.105:8083/knowledge/get_knowledgeTitle?knowledge_title=" + this
                            .ruleForm.knowledge_title)
                        .then((res) => {
                            if (res.data.msg == '查询成功') {
                                this.$message({
                                    message: "标题已存在，请重新为科普知识重新命名!",
                                    type: "warning",
                                });
                            } else {
                                this.$refs.upload.submit();
                                this.reload();
                            }
                        })

                } else {
                    if (!this.knowledge_img_name) {
                        this.$message({
                            message: "请上传图片!",
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
                this.ruleForm.knowledge_sort_id = this.getSortListLastId
                this.ruleForm.knowledge_upload = 0
                if (this.knowledge_img_name) {
                    this.$refs.upload.submit();
                    this.reload();
                } else {
                    this.$message({
                        message: "请上传图片!",
                        type: "warning",
                    });
                }

            },

            resetForm(formName) {
                this.$refs[formName].resetFields();
            },

            //  图片
            handleExceed() {
                this.$message.warning(`当前限制选择 1个文件`);
            },
            beforeRemove(file) {
                this.$confirm(`确定移除 ${file.name}？`);
                this.knowledge_img_name = null
                console.log(this.knowledge_img_name);
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
            handleChange(file) {
                this.knowledge_img_name = file.name
                // console.log(this.knowledge_img_name);
            },
            handleSuccess() {
                if(this.ruleForm.knowledge_upload==0){
                    this.$message.success("保存成功！");
                }
                else{
                    this.$message.success("上传成功！");
                }
                


            },
            handleError() {
                 if(this.ruleForm.knowledge_upload==0){
                    this.$message.success("保存失败！");
                }
                else{
                    this.$message.success("上传失败！");
                }
            },
        },
        computed: {

            getSortListLastId() {
                return this.$store.state.sort_id_list_last;
            },

        },
    }
</script>

<style>
    #addSort .el-form-item__label::before {
        content: '*';
        color: #F56C6C;
        margin-right: 4px;
    }

    #addSort .upload-demo {
        width: 300px;
    }
</style>