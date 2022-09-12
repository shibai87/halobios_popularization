<template>
    <div id="report">
       <el-header>
              <el-page-header @back="goBack" content="评论详情页面">
              </el-page-header>
          </el-header>
          <div v-for="(item,index) in report[0]" :key="index" class="infinite-list-item">
            <el-descriptions class="margin-top comment_user" :column="3" border>
              <el-descriptions-item  labelStyle="width:150px"  contentStyle="width:250px">
                <template #label>
                  <i class="el-icon-user"></i>
                  被举报人昵称
                </template>
                {{item.user_name}}
              </el-descriptions-item>
              <el-descriptions-item  labelStyle="width:100px"  contentStyle="width:300px">
                <template #label>
                  <i class="el-icon-tickets"></i>
                  举报人
                </template>
                {{item.report_name}}
              </el-descriptions-item>
               <el-descriptions-item  labelStyle="width:100px" contentStyle="width:300px">
                <template #label>
                  <i class="el-icon-tickets"></i>
                  举报原因
                </template>
                <div class="comment_reportInfos">
                  {{item.report_reason}}
                </div>
              </el-descriptions-item>
            </el-descriptions>
            <el-descriptions class="margin-top comment_Info" :column="1" border>
              <el-descriptions-item   labelStyle="width:150px"  contentStyle="width:1050px">
                <template #label>
                  <i class="el-icon-tickets"></i>
                  评论内容
                </template>
                <div class="comment_content">
                  {{item.comment_content}}
                </div>
              </el-descriptions-item>
            </el-descriptions>
            <el-descriptions class="margin-top comment_report" :column="2" border>
                <el-descriptions-item labelStyle="width:150px" contentStyle="width:450px">
                <template #label>
                  <i class="el-icon-tickets"></i>
                  审核结果
                </template>
                {{item.comment_state}}
              </el-descriptions-item>
              <el-descriptions-item labelStyle="width:150px" contentStyle="width:450px">
                <template #label>
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
            <el-dialog title="编辑审核" v-model="dialogFormVisible">
              <el-form v-model="form">
                <el-form-item label="审核结果" :label-width="formLabelWidth">
                  <el-select v-model="form.comment_state" placeholder="请选择">
                    <el-option label="通过" value="1"></el-option>
                    <el-option label="不通过" value="3"></el-option>
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
  
  <script setup lang="ts">
    import {ref,reactive,onMounted} from "vue"
  import ReportService from "../../api/ReportService.js"
  import { ElMessage} from 'element-plus'
  import { useRoute } from "vue-router";
        const route = useRoute()
  const report=reactive<any>([])
   const dialogFormVisible=ref(false)
   const formLabelWidth=ref('120px')
   const form=reactive({
            comment_state: "",
            report_result: ""
          })

    onMounted(()=>{
        getLoadreport()
      })
const  goBack=()=> {
    window.history.go(-1);
  }
const getLoadreport=()=> {
      ReportService.getBycommentId(route.query.comment_id).then((res) => {
        console.log(res)
            if (res[0].comment_state == 1) {
              res[0].comment_state = "评论已通过"
            } else
            if (res[0].comment_state == 2) {
              res[0].comment_state = "评论待审核"
            } else
            if (res[0].comment_state == 3) {
              res[0].comment_state = "该评论未通过审核"
            }
            console.log("getLoadreport")
        console.log(res[0])
        report.push(res)
        console.log(report[0][0])
      })
  }
const  submit=(comment_id)=>{
    console.log(form.comment_state,form.report_result)
    if(form.comment_state){
      ReportService.updata(form.comment_state,comment_id,form.report_result).then((res)=>{
        if(res.data==0){
          ElMessage({
              message: "提交成功！",
              type: "success",
            });
            window.history.go(-1);
        }else{
          ElMessage({
              message: "提交失败！",
              type: "warning",
            });
        }

      })
    }else{
      ElMessage({
              message: "提交失败，请确认已选择审核结果！",
              type: "warning",
            });
    }
  }
  </script>

  <style>
      #report .el-header {
          background-color: rgb(99, 175, 255);
          padding: 20px 10px;
      }
  </style>

  <style scoped>
    .infinite-list-item{
      width: 1200px;
      margin:40px auto;
      box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
    }
  </style>