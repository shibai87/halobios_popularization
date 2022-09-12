<template>
  <div id="reportList">
    <div v-if="empty != 0">
      <el-row class="reportList_select">
        <el-col :span="2" :offset="5">
          <div class="reportList_select_by_comment_state reportList_select_title">查询方式一:</div>
        </el-col>
        <el-col :span="5">
          <el-select v-model="comment_state" class="m-2" placeholder="请选择要查询的审核状态" clearable>
            <el-option v-for="item in comment_state_options" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>

        </el-col>
        <el-col :span="2">
          <div class="reportList_select_by_source_type reportList_select_title">查询方式二:</div>
        </el-col>
        <el-col :span="5">
          <el-select v-model="source_type" placeholder="请选择要查询的资源状态" class="m-2" clearable>
            <el-option v-for="item in source_type_options" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </el-col>
        <el-col :span="2">
          <el-button @click="clear" type="success">清空选择</el-button>
        </el-col>
      </el-row>
      <el-divider>以下为审核列表</el-divider>
      <div v-if="reportList[0]">
        <ul class="infinite-list" style="overflow:auto">
          <li v-for="(item, index) in reportList[0]" :key="index" class="infinite-list-item">
            <el-descriptions class="margin-top comment_user" :column="3">
              <el-descriptions-item labelStyle="width:150px" contentStyle="width:200px">
                <template #label>
                  <i class="el-icon-user"></i>
                  被举报人昵称
                </template>
                {{ item.user_name }}
              </el-descriptions-item>
              <el-descriptions-item labelStyle="width:150px" contentStyle="width:200px">
                <template #label>
                  <i class="el-icon-tickets"></i>
                  举报人
                </template>
                {{ item.report_name }}
              </el-descriptions-item>
              <el-descriptions-item labelStyle="width:150px" contentStyle="width:200px">

                <template #label>
                  <i class="el-icon-tickets"></i>
                  举报原因
                </template>
                <div class="comment_reportInfos">
                  {{ item.report_reason }}
                </div>
              </el-descriptions-item>
            </el-descriptions>
            <el-descriptions class="margin-top comment_Info" :column="1" border>
              <el-descriptions-item labelStyle="width:150px" contentStyle="width:1000px">
                <template #label>
                  <i class="el-icon-tickets"></i>
                  评论内容
                </template>
                <div class="comment_content">
                  {{ item.comment_content }}
                </div>
              </el-descriptions-item>

            </el-descriptions>
            <el-descriptions class="margin-top comment_report" :column="2" border>
              <el-descriptions-item labelStyle="width:150px" contentStyle="width:400px">
                <template #label>
                  <i class="el-icon-tickets"></i>
                  审核结果
                </template>
                {{ item.comment_state }}
              </el-descriptions-item>
              <el-descriptions-item labelStyle="width:150px" contentStyle="width:400px">
                <template #label>
                  <i class="el-icon-tickets"></i>
                  审核结果说明
                </template>
                <div class="comment_reportInfos">
                  <div v-if="item.report_result">{{ item.report_result }}</div>
                  <div v-else>无内容</div>
                </div>
              </el-descriptions-item>
            </el-descriptions>
            <div>
              <el-button type="primary" @click="dialogFormVisible = true, comment_id_change = item.comment_id">编辑
              </el-button>
              <el-button type="primary" @click="getSource(item.operation_source_type, item.operation_source_id)">查看资源详情
              </el-button>
            </div>
            <!--审核举报  -->
            <el-dialog title="编辑审核" v-model="dialogFormVisible">
              <el-form v-model="form">
                <el-form-item label="审核结果" :label-width="formLabelWidth">
                  <el-select v-model="form.comment_state1" placeholder="请选择">
                    <el-option label="通过" value="1"></el-option>
                    <el-option label="不通过" value="3"></el-option>
                  </el-select>
                </el-form-item>

                <el-form-item label="审核结果说明" :label-width="formLabelWidth">
                  <el-input v-model="form.report_result" autocomplete="off" placeholder="请为审核结果输入说明"></el-input>
                </el-form-item>
              </el-form>
              <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="dialogFormVisible = false; submit()">确 定</el-button>
              </div>
            </el-dialog>
          </li>
        </ul>
      </div>
      <div v-else>
        <el-empty description="无该审核状态下的评论！" v-show="comment_state != ''"></el-empty>
        <el-empty description="无该类型下的评论！" v-show="source_type != ''"></el-empty>
      </div>
    </div>
    <div v-else>
      <el-empty description="没有需审核的举报！" v-show="!(comment_state && source_type)"></el-empty>
    </div>

  </div>
</template>

<script setup lang="ts">
import ReportService from "../../api/ReportService.js"
import { reactive, ref, inject, onMounted } from "vue"
import { ElMessage } from 'element-plus'
import { watch } from "vue"
import { useRouter } from "vue-router"

const router = useRouter()
const refresh: any = inject("reload")
const reportList = reactive<any>([])
const dialogFormVisible = ref(false)
const formLabelWidth = ref('120px')
const form = reactive({
  comment_state1: "",
  report_result: ""
})
const empty = ref(0)
const comment_state_options = reactive([{
  value: '1',
  label: '已通过审核'
}, {
  value: '2',
  label: '有待审核'
}, {
  value: '3',
  label: '未通过审核'
}])
const comment_state = ref("")
const source_type_options = reactive([{
  value: '0',
  label: '查询科普海洋生物知识模块的评论列表'
}, {
  value: '1',
  label: '查询保护海洋生物活动模块的评论列表'
}, {
  value: '2',
  label: '查询科普海洋生物小课堂模块的评论列表'
}])
const source_type = ref("")
const comment_id_change = ref("")
onMounted(() => {
  getLoadreportList()
})
const getLoadreportList = () => {
  ReportService.get().then((response) => {

    empty.value = response.length == 0 ? 0 : 1
    for (let i = 0; i < response.length; i++) {
      if (response[i].comment_state == 0) {
        response[i].comment_state = "评论未被举报"
      } else if (response[i].comment_state == 1) {
        response[i].comment_state = "评论已通过"
      } else
        if (response[i].comment_state == 2) {
          response[i].comment_state = "评论待审核"
        } else
          if (response[i].comment_state == 3) {
            response[i].comment_state = "该评论未通过审核"
          }
    }
    console.log(response)
    reportList.push(response)
    console.log(reportList[0])
  })
}

const submit = () => {
  if (form.comment_state1) {
    ReportService.updata(form.comment_state1, comment_id_change, form.report_result).then((res) => {
      if (res.data == 0) {
        ElMessage({
          message: "提交成功！",
          type: "success",
        });
        refresh()
      } else {
        ElMessage({
          message: "提交失败！",
          type: "warning",
        });
      }

    })
  } else {
    ElMessage({
      message: "提交失败，请确认已选择审核结果！",
      type: "warning",
    });
  }
}
const clear = () => {
  source_type.value = '',
    comment_state.value = '',
    getLoadreportList()
}
const seachBySourceTypeAndCommentState = () => {
  ReportService.getBySourceTypeAndCommentState(source_type.value, comment_state.value).then((res) => {
    for (let i = 0; i < res.length; i++) {
      if (res[i].comment_state == 0) {
        res[i].comment_state = "评论未被举报"
      } else if (res[i].comment_state == 1) {
        res[i].comment_state = "评论已通过"
      } else
        if (res[i].comment_state == 2) {
          res[i].comment_state = "评论待审核"
        } else
          if (res[i].comment_state == 3) {
            res[i].comment_state = "该评论未通过审核"
          }
    }
    console.log(res)
    reportList.push(res)
    console.log(reportList[0])
  })
}
const getSource = (operation_source_type: any, operation_source_id: any) => {
  if (operation_source_type == 0) {
    sessionStorage.setItem('judgeKnowledge', "true")
    sessionStorage.setItem('getKnowledge', operation_source_id)
    router.push("/editknowledge_detail");
  }
  if (operation_source_type == 1) {
    sessionStorage.setItem('judgeactivity', "true")
    sessionStorage.setItem('activity_id', operation_source_id)
    router.push("/editactivity_detail");
  }
  if (operation_source_type == 2) {
    sessionStorage.setItem('judgeclass', "true")
    sessionStorage.setItem('class_id', operation_source_id)
    router.push("/editclass_detail");
  }
}
watch(comment_state, (newValue, oldValue) => {
  console.log('watch 已触发', newValue)
  if (!source_type.value) {
    ReportService.getByCommentState(comment_state.value).then((res) => {
      for (let i = 0; i < res.length; i++) {
        if (res[i].comment_state == 0) {
          res[i].comment_state = "评论未被举报"
        } else if (res[i].comment_state == 1) {
          res[i].comment_state = "评论已通过"
        } else
          if (res[i].comment_state == 2) {
            res[i].comment_state = "评论待审核"
          } else
            if (res[i].comment_state == 3) {
              res[i].comment_state = "该评论未通过审核"
            }
      }
      console.log("watch:value")
      console.log(res)
      reportList.splice(0, reportList.length)
      reportList.push(res)
      console.log(reportList)
    })
  } else {
    seachBySourceTypeAndCommentState()
  }
})
watch(source_type, (newValue) => {
  console.log('watch 已触发', newValue)
  if (!comment_state.value) {
    ReportService.getBySourceType(source_type.value).then((res) => {
      for (let i = 0; i < res.length; i++) {
        if (res[i].comment_state == 0) {
          res[i].comment_state = "评论未被举报"
        } else if (res[i].comment_state == 1) {
          res[i].comment_state = "评论已通过"
        } else
          if (res[i].comment_state == 2) {
            res[i].comment_state = "评论待审核"
          } else
            if (res[i].comment_state == 3) {
              res[i].comment_state = "该评论未通过审核"
            }
      }
      console.log("watch:value")
      console.log(res)
      reportList.splice(0, reportList.length)
      reportList.push(res)
      console.log(reportList)
    })
  } else {
    seachBySourceTypeAndCommentState()
  }
})
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

.reportList_select_title {
  height: 40px;
  line-height: 40px;
}
</style>