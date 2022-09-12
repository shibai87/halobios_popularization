<template>
  <div id="userList">
    <el-table :data="tableData[0]" style="width:600px" max-height="610">
      <el-table-column prop="user_name" label="用户名" width="150">
      </el-table-column>
      <el-table-column prop="user_tel" label="用户电话" width="150">
      </el-table-column>
      <el-table-column prop="user_sex" label="性别" width="150">
      </el-table-column>
      <el-table-column label="操作" width="150">
        <template #default="scope">
          <el-button size="small" @click="deleteRow(scope.$index, tableData)">注销该用户</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script setup lang="ts">
import UserService from "../../api/UserService.js"
import { onMounted, reactive } from "vue"
import { ElMessage} from 'element-plus'
const tableData:Array<string> = reactive([
])
onMounted(() => {
  getUserList()
})
const getUserList = () => {
  UserService.infoList().then((res) => {
    console.log(res)
    for (let i = 0; i < res.length; i++) {
      if (res[i].user_sex == 0) {
        res[i].user_sex = "女"
      } else if (res[i].user_sex == 1) {
        res[i].user_sex = "男"
      } else {
        res[i].user_sex = "用户尚未设置性别"
      }
    }
    tableData.push(res)
    console.log(tableData)
  })
}
const deleteRow = (index, rows) => {
  let user_id = rows[index].user_id
  UserService.deleteUser(user_id).then((res) => {
    if (res.msg == '删除成功') {
      ElMessage({
        message: "删除成功!",
        type: "success",
      });
      getUserList()
    } else {
      ElMessage({
        message: "删除失败!",
        type: "warning",
      });
    }
  })
}
</script>

<style>
#userList .el-table {
  margin: auto;

}

#userList .el-table__cell {
  text-align: center;
}
</style>

