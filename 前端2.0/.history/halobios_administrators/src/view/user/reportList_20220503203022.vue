<template>
  <div id="reportList">
    <div  class="reportList_select">
       <div class="reportList_select_by_comment_state">
      <div class="reportList_select_title">评论查询:</div>
      <el-select v-model="comment_state" placeholder="请选择要查询的审核状态" clearable>
        <el-option v-for="item in comment_state_options" :key="item.value" :label="item.label" :value="item.value">
        </el-option>
      </el-select>
    </div>
    <div>
       <div class="reportList_select_by_source_type">
      <div class="reportList_select_title">评论查询:</div>
      <el-select v-model="source_type" placeholder="请选择要查询的资源状态" clearable>
        <el-option v-for="item in source_type_options" :key="item.value" :label="item.label" :value="item.value">
        </el-option>
      </el-select>
    </div>
    </div>
   
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
        <el-empty description="没有人举报！" v-show="this.comment_state==null&&this.source_type==null"></el-empty>
        <el-empty description="无该审核状态下的评论！" v-show="this.comment_state!=null"></el-empty>
        <el-empty description="无该审核状态下的评论！" v-show="this.source_type!=null"></el-empty>
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
        comment_state_options: [{
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
           source_type_options: [{
          value: '0',
          label: '查询科普海洋生物知识模块的评论列表'
        }, {
          value: '1',
          label: '查询保护海洋生物活动模块的评论列表'
        }, {
          value: '2',
          label: '查询科普海洋生物小课堂模块的评论列表'
        }, ],
        source_type: '',

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
      },
      // seachBySourceTypeAndCommentState(){
      //    this.$axios.get("http://192.168.1.105:8083/report/getBySourceTypeAndCommentState?comment_state="+this.comment_state)
      //                .then((res) => {
      //                 console.log(res.data)
      //                 this.reportList = res.data
      //                 for (let i = 0; i < this.reportList.length; i++) {
      //                   if (this.reportList[i].comment_state == 0) {
      //                     this.reportList[i].comment_state = "评论未被举报"
      //                   } else if (this.reportList[i].comment_state == 1) {
      //                     this.reportList[i].comment_state = "评论已通过"
      //                   } else
      //                   if (this.reportList[i].comment_state == 2) {
      //                     this.reportList[i].comment_state = "评论待审核"
      //                   } else
      //                   if (this.reportList[i].comment_state == 3) {
      //                     this.reportList[i].comment_state = "该评论未通过审核"
      //                   }
      //                 }
      //               })
      // }
    },
    mounted() {
      this.getLoadreportList()
    },
     watch:{
            comment_state(){
              // if(!this.source_type){
                 this.$axios.get("http://192.168.1.105:8083/report/getByCommentState?operation_source_type="+this.source_type+"comment_state="+this.comment_state)
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
              // }else{
              //     this.seachBySourceTypeAndCommentState()
              // }
                
            },
          //   source_type(){
          //         if(!this.comment_state){
          //             this.$axios.get("http://192.168.1.105:8083/report/getBySourceType?operation_source_type="+this.source_type)
          //            .then((res) => {
          //             console.log(res.data)
          //             this.reportList = res.data
          //             for (let i = 0; i < this.reportList.length; i++) {
          //               if (this.reportList[i].comment_state == 0) {
          //                 this.reportList[i].comment_state = "评论未被举报"
          //               } else if (this.reportList[i].comment_state == 1) {
          //                 this.reportList[i].comment_state = "评论已通过"
          //               } else
          //               if (this.reportList[i].comment_state == 2) {
          //                 this.reportList[i].comment_state = "评论待审核"
          //               } else
          //               if (this.reportList[i].comment_state == 3) {
          //                 this.reportList[i].comment_state = "该评论未通过审核"
          //               }
          //             }
          // })
          //         }else{
          //           this.seachBySourceTypeAndCommentState
          //         }
               
          //   }
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