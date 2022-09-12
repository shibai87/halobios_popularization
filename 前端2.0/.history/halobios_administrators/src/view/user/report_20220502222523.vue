<template>
  <div id="report">
     <el-header>
            <el-page-header @back="goBack" content="评论详情页面">
            </el-page-header>
        </el-header>
        <div v-for="(item,index) in report" :key="index" class="infinite-list-item">
          <el-descriptions class="margin-top comment_user" :column="2" border>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-user"></i>
                被举报人昵称
              </template>
              {{item.user_name}}
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-tickets"></i>
                举报人
              </template>
              {{item.report_name}}
            </el-descriptions-item>
         
          </el-descriptions>
          <el-descriptions class="margin-top comment_Info" :column="1" border>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-tickets"></i>
                评论内容
              </template>
              <div class="comment_content">
                {{item.comment_content}}
              </div>

            </el-descriptions-item>

          </el-descriptions>
          <el-descriptions class="margin-top comment_report" :column="3" border>
              <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-tickets"></i>
                审核结果
              </template>
              {{item.comment_state}}
            </el-descriptions-item>
            <el-descriptions-item  labelStyle="width:100px" contentStyle="width:200px">
                
              <template slot="label">
                <i class="el-icon-tickets"></i>
                举报原因
              </template>
              <div class="comment_reportInfos">
                {{item.report_reason}}
              </div>
            </el-descriptions-item>
            <el-descriptions-item>
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
                  <el-option label="通过" value="0"></el-option>
                  <el-option label="不通过" value="2"></el-option>
                </el-select>
              </el-form-item>

              <el-form-item label="审核结果说明" :label-width="formLabelWidth">
                <el-input v-model="form.report_result" autocomplete="off"  placeholder="请为审核结果输入说明"></el-input>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible = false">取 消</el-button>
              <el-button type="primary" @click="dialogFormVisible = false;submit(item.comment_id)">确 定</el-button>
            </div>
          </el-dialog>
        </div>
  </div>
</template>

<script>
  export default {
    name: 'report',
    data() {
      return {
        report: [],
        dialogTableVisible: false,
        dialogFormVisible: false,
        formLabelWidth: '120px',
        form: {
          comment_state: "",
          report_result: ""
        }

      }
    },
    methods: {
      goBack() {
        this.$router.push("/index");
      },
      getLoadreport() {
        this.$axios.get("http://192.168.1.105:8083/report/get")
          .then((res) => {
            console.log(res.data.length)
            this.report = res.data
            for (let i = 0; i < this.report.length; i++) {
                            if (this.report[i].comment_state == 0) {
                                this.report[i].comment_state = "评论未被举报"
                            }else if (this.report[i].comment_state == 1) {
                                this.report[i].comment_state = "评论已通过"
                            } else
                            if (this.report[i].comment_state == 2) {
                                this.report[i].comment_state = "评论待审核"
                            } else
                            if (this.report[i].comment_state == 3) {
                                this.report[i].comment_state = "该评论未通过审核"
                            }
                        }
          })
      },
      submit(comment_id){
        console.log(this.form.comment_state,this.form.report_result)
        if(this.form.comment_state){
          this.$axios.post("http://192.168.1.105:8083/report/updata?comment_state="+this.form.comment_state+"&comment_id="+comment_id+"&report_result="+this.form.report_result)
          .then((res)=>{
            console.log(res.data)
            if(res.data.data==0){
                this.$message({
                  message: "提交成功！",
                  type: "danger",
                });
            }else{
               this.$message({
                  message: "提交失败！",
                  type: "danger",
                });
            }
            
          })
        }else{
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
      this.getLoadreport()
    }
  }
</script>

<style>
    #report .el-header {
        background-color: rgb(99, 175, 255);
        padding: 20px 10px;
    }

  #report .comment_report .is-bordered-label {
    color: #000;
    width: 40px;

  }

  #report .comment_report .el-descriptions-item__content {
    width: 50px;
  }

  #report .el-button--small {
    width: 100%;
  }
</style>
<style scoped>
  .infinite-list-item{
    width: 1000px;
    margin:40pxauto;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
  }

  .comment_content {
    display: -webkit-box;
    /**对象作为伸缩盒子模型展示**/
    -webkit-box-orient: vertical;
    /**设置或检索伸缩盒子对象的子元素的排列方式**/
    -webkit-line-clamp: 3;
    /**显示的行数**/
    overflow-x: scroll;
  }

  .comment_reportInfos {
    width: 350px;
    overflow-x: scroll;
    white-space: nowrap;
  }
</style>