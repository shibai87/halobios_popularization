<template>
    <div id="editKnowledge_detail">
        <el-header>
            <el-page-header @back="goBack" content="详情页面">
            </el-page-header>
        </el-header>
        <el-main>
            <el-form :model="ruleForm"  ref="ruleForm" label-width="220px" class="demo-ruleForm">
                <el-form-item label="科普知识名称" prop="knowledge_title" >
                    <el-input v-model="ruleForm.knowledge_title" placeholder="请输入该科普知识的生物学名称"></el-input>
                </el-form-item>
                <el-form-item label="(已上传的)科普知识所属分类名" v-if="this.ruleForm.knowledge_sort_id">
                    <div class="editKnowledge_sort">
                        {{sort.sort_type_0}}--{{sort.sort_type_1}}--{{sort.sort_type_2}}--{{sort.sort_type_3}}--{{sort.sort_type_4}}--{{sort.sort_type_5}}--{{sort.sort_type_6}} 
                    </div>
                </el-form-item>
                <el-form-item label="(新编辑的)科普知识所属分类名">
                     <Sort></Sort>
                </el-form-item>
                <el-form-item label="科普知识分类内容" prop="knowledge_content">
                    <el-input type="textarea" v-model="ruleForm.knowledge_content"></el-input>
                </el-form-item>
                  <el-form-item label="(已上传的)科普知识分类图片" this.ruleForm.knowledge_img>
                    <div v-show="judge_knowledge" class="knowledge_img"><img :src="Image()" /></div>
                </el-form-item>
                 <div class="knowledge_img_name">图片文件名： {{ruleForm.knowledge_img}}</div>
                <el-form-item label="(新编辑的)科普知识分类图片" prop="knowledge_img">
                    <el-upload class="upload-demo" ref="upload" action="http://192.168.1.105:8083/knowledge/updatasort"
                        :on-preview="handlePreview" :on-remove="handleRemove" :limit="1" :on-exceed="handleExceed"
                        :on-success="handleSuccess" :on-error="handleError" :before-remove="beforeRemove" :on-change="handleChange"
                        :before-upload="beforeUpload" :file-list="fileList" :data="ruleForm" name="file"
                        :auto-upload="false" accept="image/*">
                        <el-button v-if="judge_knowledge" slot="trigger" >重新上传图片</el-button>
                        <el-button v-else slot="trigger">选取文件</el-button>
                        <div slot="tip" class="el-upload__tip">只能上传一个文件</div>
                    </el-upload>
                </el-form-item>
                <el-alert title="提交前必须重新上传图片！" type="warning" :closable="false" >
  </el-alert>
                <el-form-item>
                    <el-button @click="keepForm('ruleForm')" v-show="!judge_knowledge">保存</el-button>
                    <el-button type="primary" @click="submitForm()">提交</el-button>
                    <el-button @click="resetForm('ruleForm')" v-show="!judge_knowledge">重置</el-button>
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
                  // 判断是否有图片上传
                knowledge_img_name: "",
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
                old_img_name:""
             
            };
        },
        methods: {
            goBack() {
                this.$router.push("/index");
            },
            // 获取需编辑的知识详情
              getLoadKnowledge() {
                this.axios.get("http://192.168.1.105:8083/knowledge/get_knowledge?knowledge_id=" + this.getKnowledge)
                    .then((res) => {
                         this.ruleForm=res.data[0]
                        this.getSort()
                    });
            },
            getSort(){
                if(this.ruleForm.knowledge_sort_id!=null){
                       this.axios.get("http://192.168.1.105:8083/knowledge/get_sort?knowledge_sort_id=" +this.ruleForm.knowledge_sort_id)
                    .then((res) => {
                         this.sort=res.data[0]
                    })
                }
                  
            },
            Image() {
                this.old_img_name=this.ruleForm.knowledge_img;
            var url = "http://192.168.1.105:8083/knowledge/" +this.ruleForm.knowledge_img
            return url
            },
            // 提交
             submitForm() {
                this.ruleForm.knowledge_sort_id = this.getSortListLastId
                this.ruleForm.knowledge_upload = 1
                this.ruleForm.old_img_name=this.old_img_name;
                if (this.ruleForm.knowledge_title && this.ruleForm.knowledge_sort_id != null && this.ruleForm
                    .knowledge_content && this.knowledge_img_name) {
                    this.$axios.get("http://192.168.1.105:8083/knowledge/get_knowledgeTitle?knowledge_title=" + this
                            .ruleForm.knowledge_title)
                        .then((res) => {
                            console.log(res.data)
                            if (res.data.msg == '查询成功'&&res.data.data!=this.getKnowledge) {
                                this.$message({
                                    message: "标题已存在，请重新为科普知识重新命名!",
                                    type: "warning",
                                });
                            } else {
                                this.$refs.upload.submit();
                                this.getLoadKnowledge();
                                 this.$router.push("/index")
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
                    // console.log(this.fileList)
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

            // 
            handleExceed() {
                this.$message.warning(`当前限制选择 1个文件`);
            },
            beforeRemove(file) {
                this.$confirm(`确定移除 ${file.name}？`);
                  this.knowledge_img_name=null
                console.log(this.knowledge_img_name);
            },

            handleRemove(file, fileList) {
                console.log(file, fileList);
            },
            handlePreview(file) {
                console.log(file);
                this.judge_img=1
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
               handleChange(file){
                this.knowledge_img_name=file.name
                console.log(this.knowledge_img_name);
            },
            beforeUpload(val) {
                    this.judge_img=1
                console.log(val);
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
        computed:{
            judge_knowledge(){
              return JSON.parse(sessionStorage.getItem('judgeKnowledge')); 
            },
            getKnowledge(){
              return JSON.parse(sessionStorage.getItem('getKnowledge')) 
            },
             getSortListLastId() {
                return this.$store.state.sort_id_list_last;
            },

        },
        mounted(){
            this.getLoadKnowledge()
        }
    }
</script>

<style>
/* 布局 */
 #editKnowledge_detail .el-header {
    background-color: rgb(99, 175, 255);
     padding: 20px 10px;
  }
#editKnowledge_detail  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    height: 660px;
  }
/* 头部 */
#editKnowledge_detail  .el-page-header__title{
    font-size: 25px;
}
#editKnowledge_detail  .el-icon-back{
    font-size: 30px;
}
#editKnowledge_detail .el-page-header__content{
    font-size: 25px;
}
#editKnowledge_detail .upload-demo {
    width: 100px;
}
/* 主体 */
 #editKnowledge_detail .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  #editKnowledge_detail .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  #editKnowledge_detail .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  #editKnowledge_detail .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
   #editKnowledge_detail .el-alert{
height: 30px;
width: 1200px;
margin:0 auto 2px;
  }
  #editKnowledge_detail  .el-upload-list__item-name{
      width: 150px;
      overflow-x:scroll;
  }
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
.knowledge_img_name{
     text-align:left;
        margin:0 220px 10px;
}
</style>