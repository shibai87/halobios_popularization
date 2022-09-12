<template>
  <div id="reportList">
    <div class="reportList_select">
      <div class="reportList_select_title">评论查询:</div>
      <el-select v-model="comment_state" placeholder="请选择要查询的评论状态" clearable>
        <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
        </el-option>
      </el-select>
    </div>
    <div>
       <el-divider>以下为审核列表</el-divider>
      <div v-if="this.reportList.length">
        <ul class="infinite-list" v-infinite-scroll="load" style="overflow:auto">
          <li v-for="(item,index) in reportList" :key="index" class="infinite-list-item">
            <el-descriptions class="margin-top comment_user" :column="3" border>
              <el-descriptions-item labelStyle="width:150px" contentStyle="width:200px">
                <template slot="label">
                  <i class="el-icon-user"></i>
                  被举报人昵称
                </template>
                {{item.user_name}}
              </el-descriptions-item>
              <el-descriptions-item labelStyle="width:150px" contentStyle="width:200px">
                <template slot="label">
                  <i class="el-icon-tickets"></i>
                  举报人
                </template>
                {{item.report_name}}
              </el-descriptions-item>
              <el-descriptions-item labelStyle="width:150px" contentStyle="width:200px">

                <template slot="label">
                  <i class="el-icon-tickets"></i>
                  举报原因
                </template>
                <div class="comment_reportInfos">
                  {{item.report_reason}}
                </div>
              </el-descriptions-item>
            </el-descriptions>
            <el-descriptions class="margin-top comment_Info" :column="1" border>
              <el-descriptions-item labelStyle="width:150px" contentStyle="width:1000px">
                <template slot="label">
                  <i class="el-icon-tickets"></i>
                  评论内容
                </template>
                <div class="comment_content">
                  {{item.comment_content}}
                </div>

              </el-descriptions-item>

            </el-descriptions>
            <el-descriptions class="margin-top comment_report" :column="2" border>
              <el-descriptions-item labelStyle="width:150px" contentStyle="width:400px">
                <template slot="label">
                  <i class="el-icon-tickets"></i>
                  审核结果
                </template>
                {{item.comment_state}}
              </el-descriptions-item>

              <el-descriptions-item labelStyle="width:150px" contentStyle="width:400px">
                <template slot="label">
                  <i class="el-icon-tickets"></i>
                  审核结果说明
                </template>
                <div class="comment_reportInfos">
                  <div v-if="item.report_result">{{item.report_result}}</div>
                  <div v-else>无内容</div>
                </div>
              </el-descriptions-item>
            </el-descriptions>

            <el-button type="primary" @click="dialogFormVisible = true">编辑</el-button>
            <el-dialog title="编辑审核" :visible.sync="dialogFormVisible">
              <el-form v-model="form">
                <el-form-item label="审核结果" :label-width="formLabelWidth">
                  <el-select v-model="form.comment_state" placeholder="请选择">
                    <el-option label="通过" value="1"></el-option>
                    <el-option label="不通过" value="2"></el-option>
                  </el-select>
                </el-form-item>

                <el-form-item label="审核结果说明" :label-width="formLabelWidth">
                  <el-input v-model="form.report_result" autocomplete="off" placeholder="请为审核结果输入说明"></el-input>
                </el-form-item>
              </el-form>
              <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="dialogFormVisible = false;submit(item.comment_id)">确 定</el-button>
              </div>
            </el-dialog>
          </li>
        </ul>

      </div>
      <div v-else>
        <el-empty description="没有人举报！" v-if="this.comment_state==null"></el-empty>
      </div>
    </div>

  </div>
</template>

<script>
  export default {
    name: 'reportList',
    data() {
      return {
        reportList: [],
        dialogTableVisible: false,
        dialogFormVisible: false,
        formLabelWidth: '120px',
        form: {
          comment_state: "",
          report_result: ""
        },
        options: [{
          value: '1',
          label: '查询所有已通过审核的评论列表'
        }, {
          value: '2',
          label: '查询所有有待审核的评论列表'
        }, {
          value: '3',
          label: '查询所有未通过审核的评论列表'
        }, ],
        comment_state: '',

      }
    },
    methods: {
      goBack() {
        this.$router.push("/index");
      },
      getLoadreportList() {
        this.$axios.get("http://192.168.1.105:8083/report/get")
          .then((res) => {
            console.log(res.data.length)
            this.reportList = res.data
            for (let i = 0; i < this.reportList.length; i++) {
              if (this.reportList[i].comment_state == 0) {
                this.reportList[i].comment_state = "评论未被举报"
              } else if (this.reportList[i].comment_state == 1) {
                this.reportList[i].comment_state = "评论已通过"
              } else
              if (this.reportList[i].comment_state == 2) {
                this.reportList[i].comment_state = "评论待审核"
              } else
              if (this.reportList[i].comment_state == 3) {
                this.reportList[i].comment_state = "该评论未通过审核"
              }
            }
          })
      },
      submit(comment_id) {
        console.log(this.form.comment_state, this.form.report_result)
        if (this.form.comment_state) {
          this.$axios.post("http://192.168.1.105:8083/report/updata?comment_state=" + this.form.comment_state +
              "&comment_id=" + comment_id + "&report_result=" + this.form.report_result)
            .then((res) => {
              console.log(res.data)
              if (res.data.data == 0) {
                this.$message({
                  message: "提交成功！",
                  type: "danger",
                });
              } else {
                this.$message({
                  message: "提交失败！",
                  type: "danger",
                });
              }

            })
        } else {
          this.$message({
            message: "提交失败，请确认已选择审核结果！",
            type: "danger",
          });
        }


      },
      load() {
        this.count += 2
      }
    },
    mounted() {
      this.getLoadreportList()
    },
     watch:{
            comment_state(){
              console.log(this.comment_state)
                 this.$axios.get("http://192.168.1.105:8083/report/getByCommentState?comment_state="+this.comment_state)
                     .then((res) => {
            console.log(res.data)
            this.reportList = res.data
            for (let i = 0; i < this.reportList.length; i++) {
              if (this.reportList[i].comment_state == 0) {
                this.reportList[i].comment_state = "评论未被举报"
              } else if (this.reportList[i].comment_state == 1) {
                this.reportList[i].comment_state = "评论已通过"
              } else
              if (this.reportList[i].comment_state == 2) {
                this.reportList[i].comment_state = "评论待审核"
              } else
              if (this.reportList[i].comment_state == 3) {
                this.reportList[i].comment_state = "该评论未通过审核"
              }
            }
          })
            }
        }
  }
</script>

<style>
  #reportList .comment_report .is-bordered-label {
    color: #000;
    width: 40px;

  }

  #reportList .comment_report .el-descriptions-item__content {
    width: 50px;
  }

  #reportList .el-button--small {
    width: 100%;
  }
</style>
<style scoped>
  .infinite-list-item {
    list-style: none;
    width: 1150px;
    margin: 0 auto 40px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
  }
</style>