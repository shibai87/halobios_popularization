<template>
    <div id="editclass">
        <el-header>
            <el-page-header @back="goBack" content="详情页面">
            </el-page-header>
        </el-header>
        <el-main>
            <el-form :model="ruleForm"  ref="ruleForm" label-width="220px" class="demo-ruleForm">
                <el-form-item label="活动主题" prop="class_title" >
                    <el-input v-model="ruleForm.video_title" placeholder="请输入该科普知识的生物学名称">{{}}</el-input>
                </el-form-item>
              
                <el-form-item label="活动介绍" prop="class_content">
                    <el-input type="textarea" v-model="ruleForm.video_info"></el-input>
                </el-form-item>
                  <el-form-item label="(已上传的)选取视频" this.ruleForm.class_video>
                    <div v-show="judge_class" class="class_video"> <video controls :src="Video()" class="class_video"></video></div>
                </el-form-item>
                <el-form-item label="(新编辑的)活动视频" prop="class_video">
                    <el-upload class="upload-demo" ref="upload" action="http://localhost:8083/video/updatavideo"
                        :on-preview="handlePreview" :on-remove="handleRemove" :limit="1" :on-exceed="handleExceed"
                        :on-success="handleSuccess" :on-error="handleError" :before-remove="beforeRemove" :on-change="handleChange"
                        :before-upload="beforeUpload" :file-list="fileList" :data="ruleForm" name="file"
                        :auto-upload="false" accept="video/*">
                        <el-button v-if="judge_class" slot="trigger" >重新选取视频</el-button>
                        <el-button v-else slot="trigger">选取视频</el-button>
                        <div slot="tip" class="el-upload__tip">只能上传一个视频</div>
                    </el-upload>
                </el-form-item>
                <el-alert title="提交前必须重新上传图片！" type="warning" :closable="false" >
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
                    video_file_name:"",
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
                  console.log(this.getvideo)
                this.axios.get("http://localhost:8083/video/get_video?video_id="+this.getclass)
                    .then((res) => {
                        console.log(res.data)
                         this.ruleForm=res.data
                        
                    });
            },
     
             Video() {
        var url = "http://localhost:8083/class/" + this.ruleForm.class_file_name
        console.log(url)
        return url
      },
           
            // 提交
            submitForm() {
               
                // console.log(this.ruleForm.class_video,this.class_video_name)
                if(this.ruleForm.classclass_video!=this.class_video_name&&this.class_video_name){
                        this.$refs.upload.submit();
                        this.reload();
                       
                }else{
                      this.$message({
                            message: "请确认重新上传了新的图片!",
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
                  this.class_video_name=null
                console.log(this.class_video_name);
            },

            handleRemove(file, fileList) {
                console.log(file, fileList);
            },
            handlePreview(file) {
                console.log(file);
                this.judge_video=1
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
                this.class_video_name=file.name
                console.log(this.class_video_name);
            },
            beforeUpload(val) {
                    this.judge_video=1
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
            judge_class(){
              return  this.$store.state.judge_class
            },
            getclass(){
              return  JSON.parse(localStorage.getItem('class_id'))
            },
        },
        mounted(){
            this.getLoadclass()
        }
    }
</script>

<style>
/* 布局 */
 #editclass .el-header {
    background-color: #B3C0D1;
     padding: 20px 10px;
  }
#editclass  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 160px;
    height: 740px;
  }
/* 头部 */
/* #editclass  .el-page-header{
   
} */
#editclass  .el-page-header__title{
    font-size: 25px;
}
#editclass  .el-icon-back{
    font-size: 30px;
}
#editclass .el-page-header__content{
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
   #editclass .el-alert{
height: 30px;
width: 1200px;
margin:0 auto 2px;
  }
</style>
<style scoped>
/* 分类 */
.editclass_sort{
    background-color: #fff;
    text-align: left;
    padding: 0 10px;
}
/* 图片 */
.class_video{
   
    width: 100px; 
    margin-left: 0;
     float: left;
}
.class_video video{
   
     width: 100px; 
     height: 100px;
}
</style>