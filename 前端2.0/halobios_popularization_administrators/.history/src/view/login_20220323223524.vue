<template>
  <div id="login">
    <h1>海洋生物科普系统管理平台</h1>
    <div class="login_content">
      <h2>管理员登录</h2>
      <el-form
        :model="ruleForm"
        status-icon
        :rules="rules"
        ref="ruleForm"
        label-width="100px"
        class="demo-ruleForm"
      >
        <el-form-item label="特殊编号:" prop="number">
          <el-input
            type="text"
            v-model="ruleForm.number"
            placeholder="请输入特殊编号"
            clearable
          >
          </el-input>
        </el-form-item>
        <el-form-item label="密码:" prop="password">
          <el-input
            :type="pwdType"
            v-model="ruleForm.password"
            placeholder="请输入密码"
            clearable
          >
            <i
              slot="suffix"
              class="el-icon-view"
              @click="showPwd"
              v-if="ruleForm.password"
            />
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">
            登录
          </el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    let checkmobile = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("特殊编号不能为空"));
      } else {
        callback();
      }
    };

    let validatePassword = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("密码不能为空"));
      } else {
        if (this.ruleForm.checkPassword !== "") {
          this.$refs.ruleForm.validateField("checkPassword");
        }
        callback();
      }
    };
    return {
      pwdType: "password",
      ruleForm: {
        number: "",
        password: "",
      },
      rules: {
        number: [
          {
            required: true,
            validator: checkmobile,
            trigger: "change",
          },
        ],
        password: [
          {
            required: true,
            validator: validatePassword,
            trigger: "change",
          },
        ],
      },
    };
  },
  methods: {
    isCellPhone(val) {
      if (!/^1(3|4|5|6|7|8)\d{9}$/.test(val)) {
        return false;
      } else {
        return true;
      }
    },
    submitForm() {
      this.$refs["ruleForm"].validate((valid) => {
        if (valid) {
          let number = this.ruleForm.number;
          let password = this.ruleForm.password;

          this.axios
            .get(
              " http://localhost:8083/user/login?user_tel=" +
                number +
                "&user_pwd=" +
                password
            )
            .then((res) => {
              console.log("登录提示:");
              console.log(res.data);
              if (res.data.code ==2) {
                this.$message({
                  message: "恭喜你，登录成功!",
                  type: "success",
                });

                this.$router.push("/index");
              } else {
                this.$message({
                  message: "登录失败,用户名或密码错误!",
                  type: "danger",
                });
              }
            });
        }
      });
    },
   

    showPwd() {
      this.pwdType === "password"
        ? (this.pwdType = "")
        : (this.pwdType = "password");
      let e = document.getElementsByClassName("el-icon-view")[0];
      this.pwdType == ""
        ? e.setAttribute("style", "color: #409EFF")
        : e.setAttribute("style", "color: #c0c4cc");
    },
  },
  created() {},
};
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
h2{
  text-align: center;
  margin: 10px;
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
