<template>
  <div>
  <el-form-item  prop="phone" class="login_content_phone">
    <el-input type="text" v-model="ruleForm.phone" autocomplete="off" placeholder="手机号码" class="phone"></el-input>
  </el-form-item>	

  </div>
</template>

<script>
export default {
  name: '',
  data () {
    return {
      msg: ''
    }
  },
  methods: {

  },
  mounted () {

  }
}
</script>

<style scoped>
.phone  >>> .el-input__inner{
	background:url(../../../../static/img/手机号.png) no-repeat 0 0;
	background-size: 10px 10px;
	background-position: 15px 12px; 
	text-indent: 20px;
}

</style>
