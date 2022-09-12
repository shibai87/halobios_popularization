<template>
  <div id="userList">
    <el-table
    :data="tableData"
    style="width:600px"
    max-height="610">
    <el-table-column
      prop="user_name"
      label="用户名"
      width="150">
    </el-table-column>
     <el-table-column
      prop="user_tel"
      label="用户电话"
      width="150">
    </el-table-column>
    <el-table-column
      prop="user_sex"
      label="性别"
      width="150">
    </el-table-column>
    <el-table-column
      label="操作"
      width="150">
      <template slot-scope="scope">
        <el-button @click="deleteRow(scope.$index, tableData)" size="small">
          注销该用户
        </el-button>
      </template>
    </el-table-column>
  </el-table>
  </div>
</template>

<script>
 export default {
    data() {
      return {
        tableData: [
          {
          user_name:"",
          user_sex:"",
        }
        ]
      }
    },
      methods: {
        getUserList(){
          this.$axios.get("http://192.168.1.105:8083/user/infoList")
          .then((res)=>{
            console.log(res.data)
            this.tableData=res.data
            for(let i=0;i<this.tableData.length;i++){
              if(this.tableData[i].user_sex==0){
                  this.tableData[i].user_sex="女"
              }else if(this.tableData[i].user_sex==1){
                  this.tableData[i].user_sex="男"
              }else{
                this.tableData[i].user_sex="用户尚未设置性别"
              }
            }
          })
        },
      deleteRow(index, rows) {
        console.log(rows[index].user_id)
        let user_id=rows[index].user_id
        this.$axios.get("http://192.168.1.105:8083/user/deleteUser?user_id="+user_id)
        .then((res)=>{
          console.log(res.data)
            if(res.data.msg=='删除成功'){
               this.$message({
                  message: "删除成功!",
                  type: "success",
                });
                this.getUserList()
            }else{
              this.$message({
                  message: "删除失败!",
                  type: "danger",
                });
            }
        })
      }
    },
    mounted() {
      this.getUserList()
    }
  }
</script>

<style>
#userList .el-table{
  margin: auto;
  
}
#userList .el-table__cell{
text-align: center;
}
</style>
<style scoped>

</style>
