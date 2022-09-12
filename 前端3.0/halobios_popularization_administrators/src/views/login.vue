<template>
  <div id="login">
    <h1>海洋生物科普系统管理平台</h1>
    <div class="login_content">
      <h2>管理员登录</h2>
      <el-form ref="ruleFormRef" :model="ruleForm" status-icon :rules="rules" label-width="100px" class="demo-ruleForm">
        <el-form-item label="特殊编号:" prop="number">
          <el-input type="text" v-model="ruleForm.number" placeholder="请输入特殊编号" clearable>
          </el-input>
        </el-form-item>
        <el-form-item label="密码:" prop="password">
          <el-input :type="pwdType" v-model="ruleForm.password" placeholder="请输入密码" clearable>
            <i slot="suffix" class="el-icon-view" @click="showPwd" v-if="ruleForm.password" />
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm(ruleFormRef)">
            登录
          </el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script setup lang="ts">
import UserService from "../api/UserService.js"
import {ref,reactive} from "vue"
import { useRouter } from 'vue-router'
import { ElMessage} from 'element-plus'
const router = useRouter();
//   import type { FormInstance } from 'element-plus'
const ruleFormRef = ref()
const checkmobile = (rule, value, callback) => {
  if (value === "") {
    callback(new Error("特殊编号不能为空"));
  } else {
    callback();
  }
}
const validatePassword = (rule, value, callback) => {
  if (value === "") {
    callback(new Error("密码不能为空"));
  } else {
    callback();
  }
}

const ruleForm=reactive({
    number: "",
    password: "",
  })
const  rules=reactive({
    number: [{
      required: true,
      validator: checkmobile,
      trigger: "change",
    }],
    password: [{
      required: true,
      validator: validatePassword,
      trigger: "change",
    }]
  })

const submitForm = (formEl) => {
    if (!formEl) return
    formEl.validate((valid) => {
    if (valid) {
        UserService.login(ruleForm.number,ruleForm.password).then((res) => {
          if (res.code == 2) {
            ElMessage({
              message: "恭喜你，登录成功!",
              type: "success",
            });
            // 跳到默认主页时，搜索需要
            sessionStorage.setItem('search_type', "0")
            router.push("/index");
          } else {
            ElMessage({
              message: "登录失败,请确认信息填写是否正确!",
              type:  "warning"
            });
          }
        });
    }
  })
}
  const pwdType=ref("password")
const showPwd=()=> {
  pwdType.value === "password" ?
    (pwdType.value= "") :
    (pwdType.value= "password");
  let e = document.getElementsByClassName("el-icon-view")[0];
  pwdType.value == "" ?
    e.setAttribute("style", "color: #409EFF") :
    e.setAttribute("style", "color: #c0c4cc");
}
</script>

<style>
  /* 去除elementUI对勾图标 */
  #login .el-form-item--feedback .el-input__validateIcon {
    display: none;
  }
</style>

<style scoped>
  /*全局  */
  #login {
    z-index: -1;
    width: 100%;
    background-image: url("../assets/img/login.jpeg");
    background-repeat: no-repeat;
    background-attachment: fixed;
    overflow: hidden;
    background-size: cover;
    min-height: 100vh;
    position: absolute;
  }

  /*  */
  h1 {
    font-size: 50px;
    text-align: center;
    padding-top: 100px;
  }

  h2 {
    text-align: center;
    margin: 20px 0;
  }

  .login_content {
    z-index: 1;
    background-color: #fff;
    padding: 20px;
    padding-right: 70px;
    border-radius: 10px;
    /* div居中 */
    width: 300px;
    margin: 100px auto;
  }
</style>