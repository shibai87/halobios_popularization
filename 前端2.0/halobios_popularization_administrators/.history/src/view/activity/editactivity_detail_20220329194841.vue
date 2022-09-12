<template>
    <div id="editactivity">
        <el-header>
            <el-page-header @back="goBack" content="详情页面">
            </el-page-header>
        </el-header>
        <el-main>
            <el-form :model="ruleForm"  ref="ruleForm" label-width="220px" class="demo-ruleForm">
                <el-form-item label="活动主题" prop="activity_title" >
                    <el-input v-model="ruleForm.activity_title" placeholder="请输入该科普知识的生物学名称">{{}}</el-input>
                </el-form-item>
              
                <el-form-item label="活动介绍" prop="activity_content">
                    <el-input type="textarea" v-model="ruleForm.activity_info"></el-input>
                </el-form-item>
                  <el-form-item label="(已上传的)选取视频" this.ruleForm.activity_video>
                    <div v-show="judge_activity" class="activity_video"> <video controls :src="Video()" class="activity_video"></video></div>
                </el-form-item>
                <el-form-item label="(新编辑的)活动视频" prop="activity_video">
                    <el-upload class="upload-demo" ref="upload" action="http://localhost:8083/activity/updatasort"
                        :on-preview="handlePreview" :on-remove="handleRemove" :limit="1" :on-exceed="handleExceed"
                        :on-success="handleSuccess" :on-error="handleError" :before-remove="beforeRemove" :on-change="handleChange"
                        :before-upload="beforeUpload" :file-list="fileList" :data="ruleForm" name="file"
                        :auto-upload="false" accept="image/*">
                        <el-button v-if="judge_activity" slot="trigger" >重新选取视频</el-button>
                        <el-button v-else slot="trigger">选取视频</el-button>
                        <div slot="tip" class="el-upload__tip">只能上传一个视频</div>
                    </el-upload>
                </el-form-item>
                <el-alert title="提交前必须重新上传图片！" type="warning" :closable="false" >
  </el-alert>
                <el-form-item>
                    <el-button @click="keepForm('ruleForm')" v-show="!judge_activity">保存</el-button>
                    <el-button type="primary" @click="submitForm()">提交</el-button>
                    <el-button @click="resetForm('ruleForm')" v-show="!judge_activity">重置</el-button>
                </el-form-item>
            </el-form>
        </el-main>
    </div>
</template>

<script>
    export default {
        name: "editactivity",
        inject: ["reload"],
        data() {
            return {
                // 判断是否有视频上传
                activity_video_name: "",
                ruleForm: {
                    activity_title: '',
                    activity_info: '',
                },
                fileList: [], //文件存储中间量
             
            };
        },
        methods: {
            goBack() {
                this.$router.push("/index");
            },
            // 获取需编辑的知识详情
              getLoadactivity() {
                  console.log(this.getactivity)
                this.axios.get("http://localhost:8083/activity/get_activity?activity_id="+this.getactivity)
                    .then((res) => {
                        console.log(res.data)
                         this.ruleForm=res.data[0]
                        this.getSort()
                    });
            },
            getSort(){
                     this.axios.get("http://localhost:8083/activity/get_sort?activity_sort_id=" +this.ruleForm.activity_sort_id)
                    .then((res) => {
                        // console.log(res.data[0])
                         this.sort=res.data[0]
                        //   console.log(this.ruleForm)
                    })
            },
             Video() {
        var url = "http://localhost:8083/activity/" + this.ruleForm.activity_file_name
        console.log(url)
        return url
      },
           
            // 提交
            submitForm() {
                if(this.getSortListLastId)(this.ruleForm.activity_sort_id=this.getSortListLastId)
                // console.log(this.ruleForm.activity_video,this.activity_video_name)
                if(this.ruleForm.activityactivity_video!=this.activity_video_name&&this.activity_video_name){
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
                  this.activity_video_name=null
                console.log(this.activity_video_name);
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
                this.activity_video_name=file.name
                console.log(this.activity_video_name);
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
            judge_activity(){
              return  this.$store.state.judge_activity
            },
            getactivity(){
              return  this.$store.state.activity_id
            },
        },
        mounted(){
            this.getLoadactivity()
        }
    }
</script>

<style>
/* 布局 */
 #editactivity .el-header {
    background-color: #B3C0D1;
     padding: 20px 10px;
  }
#editactivity  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 160px;
    height: 740px;
  }
/* 头部 */
/* #editactivity  .el-page-header{
   
} */
#editactivity  .el-page-header__title{
    font-size: 25px;
}
#editactivity  .el-icon-back{
    font-size: 30px;
}
#editactivity .el-page-header__content{
    font-size: 25px;
}
    #editactivity .upload-demo {
        width: 300px;
    }
/* 主体 */
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
   #editactivity .el-alert{
height: 30px;
width: 1200px;
margin:0 auto 2px;
  }
</style>
<style scoped>
/* 分类 */
.editactivity_sort{
    background-color: #fff;
    text-align: left;
    padding: 0 10px;
}
/* 图片 */
.activity_video{
   
    width: 100px; 
    margin-left: 0;
     float: left;
}
.activity_video video{
   
     width: 100px; 
     height: 100px;
}
</style>