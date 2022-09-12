<template>
    <div id="editKnowledge">
        <el-header>
            <el-page-header @back="goBack" content="详情页面">
            </el-page-header>
        </el-header>
        <el-main>
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="220px" class="demo-ruleForm">
                <el-form-item label="科普知识名称" prop="knowledge_title">
                    <el-input v-model="ruleForm.knowledge_title" placeholder="请输入该科普知识的生物学名称"></el-input>
                </el-form-item>
                <el-form-item label="(已上传的)科普知识所属分类名">
                    <div class="editKnowledge_sort">
                        {{sort.sort_type_0}}--{{sort.sort_type_1}}--{{sort.sort_type_2}}--{{sort.sort_type_3}}--{{sort.sort_type_4}}--{{sort.sort_type_5}}--{{sort.sort_type_6}} 
                    </div>
                </el-form-item>
                <el-form-item label="(新编辑的)科普知识所属分类名" prop="knowledge_sort_id">
                     <Sort></Sort>
                </el-form-item>
                <el-form-item label="科普知识分类内容" prop="knowledge_content">
                    <el-input type="textarea" v-model="ruleForm.knowledge_content"></el-input>
                </el-form-item>
                  <el-form-item label="(已上传的)科普知识分类图片">
                    <div v-show="judge_knowledge" class="knowledge_img"><img :src="Image()" /></div>
                </el-form-item>
                <el-form-item label="(新编辑的)科普知识分类图片" prop="knowledge_img">
                    <el-upload class="upload-demo" ref="upload" action="http://localhost:8083/knowledge/newsort"
                        :on-preview="handlePreview" :on-remove="handleRemove" :limit="1" :on-exceed="handleExceed"
                        :on-success="handleSuccess" :on-error="handleError" :before-remove="beforeRemove"
                        :before-upload="beforeUpload" :file-list="fileList" :data="ruleForm" name="file"
                        :auto-upload="false" accept="image/*">
                        <el-button v-if="judge_knowledge" slot="trigger">重新上传图片</el-button>
                        <el-button v-else slot="trigger">选取文件</el-button>
                        <div slot="tip" class="el-upload__tip">只能上传一个文件</div>
                    </el-upload>
                </el-form-item>
                <el-form-item>
                    <el-button @click="keepForm('ruleForm')" :disabled="judge_knowledge">保存</el-button>
                    <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
                    <el-button @click="resetForm('ruleForm')">重置</el-button>
                </el-form-item>
            </el-form>
        </el-main>
    </div>
</template>

<script>
import Sort from "../../components/sort.vue"
    export default {
        name: "editKnowledge",
        inject: ["reload"],
          components:{
            Sort
        },
        data() {
            return {
                // 分类
                sort:[],
                // 详情
                ruleForm: {
                 knowledge_sort_id:"",
                    knowledge_title: '',
                    knowledge_content: '',
                    knowledge_img: "",
                },
                fileList: [], //文件存储中间量
                rules: {
                    knowledge_title: [{
                        required: true,
                        message: '请输入科普知识名称',
                        trigger: 'blur'
                    }, ],
                    knowledge_sort_id: [{
                        required: true,
                        message: '请输入科普知识所属分类名',
                        trigger: 'blur'
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
            goBack() {
                this.$router.go(-1);
            },
            // 获取需编辑的知识详情
              getLoadKnowledge() {
                this.axios.get("http://localhost:8083/knowledge/get_knowledge?knowledge_id=" + this.getKnowledge)
                    .then((res) => {
                        // console.log(res.data[0])
                         this.ruleForm=res.data[0]
                        //   console.log(this.ruleForm.knowledge_sort_id)
                        this.getSort()
                    });
            },
            getSort(){
                     this.axios.get("http://localhost:8083/knowledge/get_sort?knowledge_sort_id=" + this.ruleForm.knowledge_sort_id)
                    .then((res) => {
                        console.log(res.data[0])
                         this.sort=res.data[0]
                        //   console.log(this.ruleForm)
                    })
            },
             
            Image() {
                console.log(ruleForm.knowledge_img)
            var url = "http://localhost:8083/knowledge/" +ruleForm.knowledge_img
            console.log(url)
            return url
            },

            // 提交
            submitForm(formName) {
                console.log(this.file,formName)
                this.$refs[formName].validate((valid) => {
                    if (!valid) {
                        this.$message({
                            message: "请确认信息填写完毕!",
                            type: "warning",
                        });
                    }
                    if (valid) {
                        // this.$refs.upload.submit();
                        // this.reload();
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

        },
        computed:{
            judge_knowledge(){
              return  this.$store.state.judge_knowledge
            },
            getKnowledge(){
              return  this.$store.state.knowledge_id
            }
        },
        mounted(){
            this.getLoadKnowledge()
        }
    }
</script>

<style>
/* 布局 */
 #editKnowledge .el-header {
    background-color: #B3C0D1;
     padding: 20px 10px;
  }
#editKnowledge  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 160px;
    height: 740px;
  }
/* 头部 */
/* #editKnowledge  .el-page-header{
   
} */
#editKnowledge  .el-page-header__title{
    font-size: 25px;
}
#editKnowledge  .el-icon-back{
    font-size: 30px;
}
#editKnowledge .el-page-header__content{
    font-size: 25px;
}
    #editKnowledge .upload-demo {
        width: 300px;
    }
/* 主体 */

</style>
<style scoped>
/* 分类 */
.editKnowledge_sort{
    background-color: #fff;
    text-align: left;
    padding: 0 10px;
}
/* 图片 */
.knowledge_img{
   
    width: 100px; 
    margin-left: 0;
     float: left;
}
.knowledge_img img{
   
     width: 100px; 
     height: 100px;
}
</style>