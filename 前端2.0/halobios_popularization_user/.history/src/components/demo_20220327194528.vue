<template>
  <div>
    <van-row>
  <van-col span="4">span: 8</van-col>
  <van-col span="10"> <van-radio-group v-model="radio" direction="horizontal"  label="性别">
  <van-radio name="1">单选框 1</van-radio>
  <van-radio name="2">单选框 2</van-radio>
</van-radio-group></van-col>
 
</van-row>
             
  </div>
</template>

<script>
export default {
  name: '',
  data () {
    return {
       result:1,
    }
  },
  methods: {

  },
  mounted () {

  }
}
</script>

<style scoped>

</style>
