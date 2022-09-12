<template>
  <div>
  <el-form-item  prop="phone">
    <el-input type="text" v-model="ruleForm.phone" autocomplete="off" placeholder="手机号码" class="phone">cvdgf</el-input>
  </el-form-item>	
  </div>
</template>

<script>
export default {
  name: '',
  data () {
    return {
      msg: '',
       ruleForm: {
                    // 
                   phone:""
                },
    }
  },
  methods: {

  },
  mounted () {

  }
}
</script>

<style>


</style>
