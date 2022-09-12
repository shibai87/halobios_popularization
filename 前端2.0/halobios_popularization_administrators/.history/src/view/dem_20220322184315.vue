<template>
  <div id="detail">
    <div v-if="this.ho_deadline == 0" class="warning">
      <span>作业已截止，不可提交！</span>
    </div>

    <div id="main">
      <div v-if="this.ho_deadline == 1">
        <div v-if="!submit" class="upfile">
          <el-upload
            class="upload-demo"
            ref="upload"
            action="http://10.197.43.62:8088/student/uploadFile"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :limit="1"
            :on-exceed="handleExceed"
            :on-success="handleSuccess"
            :on-error="handleError"
            :before-remove="beforeRemove"
            :before-upload="beforeUpload"
            :file-list="fileList"
            name="file"
            :data="filedata"
            :auto-upload="false"
          >
            <el-button slot="trigger" type="primary">选取文件</el-button>
            <div slot="tip" class="el-upload__tip">
              只能上传1个文件，且不超过100M
            </div>
          </el-upload>
          <div>
            <el-button type="success" @click="submitUpload">提交</el-button>
          </div>
        </div>

        <el-link
          :underline="false"
          :href="fileUrl"
          :download="filename"
          target="_blank"
          v-if="submit"
          class="downloadFile"
        >
          <div>{{ filename }}</div>
          <el-button type="primary">下载</el-button>
        </el-link>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  inject: ["reload"],
  name: "",

  data() {
    return {
      dataillist: [{}],
      fileList: [], //文件存储中间量
      filedata: {},
      submit: "",
      filename: "",
      fileUrl: "",
    };
  },
  methods: {
    loadhHomeworkDetailData() {
      let i;
      this.axios
        .get(
          "http://10.197.43.62:8088/student/topiclist?ho_id=" +
            this.ho_id +
            "&st_id=" +
            this.st_id
        )
        .then((res) => {
          this.dataillist = res.data;
          this.filedata.ho_id = this.ho_id;
          this.filedata.st_id = this.st_id;
          console.log("filedata");
          console.log(this.filedata);
        });
      this.axios
        .get("http://10.197.43.62:8088/student/stgrade?st_id=" + this.st_id)
        .then((res) => {
          for (i = 0; i < res.data.length; i++) {
            if (this.ho_id == res.data[i].ho_id && res.data[i].ho_submit == 1) {
              this.submit = true;
              break;
            } else {
              this.submit = false;
            }
          }
          // console.log(this.submit);
        });
      this.axios
        .get(
          "http://10.197.43.62:8088/student/getfileName?ho_id=" +
            this.ho_id +
            "&st_id=" +
            this.st_id
        )
        .then((res) => {
          this.filename = res.data;
          this.fileUrl =
            "http://10.197.43.62:8088/file/downloadFile?fileName=" +
            this.filename;
          //   console.log( this.fileUrl);
        });
    },
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
    submitUpload() {
      this.$refs.upload.submit();
      this.$router.push("/st_course");
    },
    handleSuccess() {
      this.$message.success("上传成功！");
    },
    handleError() {
      this.$message.error("上传失败！");
    },
  },
  mounted() {
    this.loadhHomeworkDetailData();
  },
  computed: {
    ho_id() {
      return this.$store.state.ho_id;
    },
    ho_deadline() {
      return this.$store.state.ho_deadline;
    },
    st_id() {
      return this.$store.state.st_id;
    },
  },
};
</script>

<style scoped>


#main {
  width: 600px;
  margin: 0 auto;
}

/*  */
#content {
  background-color: #ecf5ff8f;
  padding: 20px;
  font-size: 20px;
}

.explain {
  padding-right: 100px;
  text-indent: 60px;
  min-height: 250px;

  background-color: #ecf5ff8f;
  margin-bottom: 10px;
}

.require {
  min-height: 200px;
  background-color: #ecf5ff8f;
}

.explain_content,
.require_content {
  text-indent: 60px;
  padding: 20px;
}

h2 {
  font-size: 25px;
  text-align: center;
}
.upfile {
  margin: 10px auto;
  /* background-color: #fff; */
}
.upload-demo {
  width: 400px;
  font-size: 20px;
  color: #000;
}
.downloadFile {
  display: block;
  width: 150px;
  margin: 10px auto;
  padding: 10px 0 10px 30px;
  background-color: #ecf5ff8f;
}
.warning {
  width: 100%;
  text-align: center;
  margin: 20px;
}

.warning span {
  color: red;
  font-size: 40px;
  background-color: #fff;
}
</style>
<style>
#detail .el-upload__tip {
  font-size: 20px;
  color: rgb(212, 24, 24);
  background-color: #ecf5ff8f;
}
#detail .el-upload-list__item-name {
  color: #000;
  background-color: #ecf5ff8f;
}
#detail .el-link--inner {
  color: #000;
}
</style>