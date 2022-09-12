<template>
    <div id="addActivity">
        <el-form :model="ruleForm"  ref="ruleForm" label-width="150px" class="demo-ruleForm">
            <el-form-item label="活动主题" prop="activity_title">
                <el-input v-model="ruleForm.activity_title"></el-input>
            </el-form-item>
            <el-form-item label="活动介绍" prop="activity_content">
                <el-input type="textarea" v-model="ruleForm.activity_info"></el-input>
            </el-form-item>
            <el-form-item label="活动视频" prop="activity_video">
                <el-upload class="upload-demo" ref="upload" action="http://localhost:8083/activity/newactivity"
                    :on-preview="handlePreview" :on-remove="handleRemove" :limit="1" :on-exceed="handleExceed"
                    :on-success="handleSuccess" :on-error="handleError" :before-remove="beforeRemove" :on-change="handleChange"
                    :before-upload="beforeUpload" :file-list="fileList" :data="ruleForm" name="file"
                    :auto-upload="false" accept="video/*">
                    <el-button slot="trigger">选取视频</el-button>
                    <div slot="tip" class="el-upload__tip">只能上传一个视频</div>
                </el-upload>
            </el-form-item>
              <el-alert title="提交或保存前必须上传视频！" type="warning" :closable="false" >
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
    export default {
        name:"addActivity",
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
            submitForm() {
                this.ruleForm.activity_upload=1
                if(this.ruleForm.activity_title&&this.ruleForm.activity_info&&this.activity_video_name){
                        this.$refs.upload.submit();
                        this.reload();
                }else{
                     if(!this.activity_video_name){
                          this.$message({
                            message: "请上传视频!",
                            type: "warning",
                        });
                     }else{
                           this.$message({
                            message: "请确认信息填写完毕!",
                            type: "warning",
                        });
                     }
                    
                }
            },
            keepForm(){
                   // console.log(this.activity_video_name);
                  this.ruleForm.activity_upload=0
                if(this.activity_video_name){
                        this.$refs.upload.submit();
                        // this.reload();
                        // console.log(this.ruleForm)
                        console.log(this.fileList)
                }else{
                     console.log(this.ruleForm)
                 
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
                  this.activity_video_name=null
                console.log(this.activity_video_name);
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
                this.activity_video_name=val.name
                console.log(val);
            },
            handleChange(file){
                this.activity_video_name=file.name
                // console.log(this.activity_video_name);
            },
            handleSuccess() {
                if(this.ruleForm.activity_upload){
                        this.$message.success("提交成功！");
                }else{
                     this.$message.success("保存成功！");
                }
                
                

            },
            handleError() {
                this.$message.error("上传失败！");
             
            },

        },
           computed:{
           
             getSortListLastId() {
                return this.$store.state.sort_id_list_last;
            },

        },
    }
</script>

<style>
 #addSort .el-form-item__label::before{
content: '*';
    color: #F56C6C;
    margin-right: 4px;
 }
    #addSort .upload-demo {
        width: 300px;
    }
</style>