<template>
  <div>

 <van-radio-group v-model="radio">
  <van-radio name="1">单选框 1</van-radio>
  <van-radio name="2">单选框 2</van-radio>
</van-radio-group>

             
  </div>
</template>

<script>
export default {
  name: '',
  data () {
    return {
      value: '',
    }
  },
  methods: {

  },
  mounted () {

  }
}
</script>

<style scoped>
.van-cell{
  margin-top: 200px;
}
</style>
