<template>
  <div>
<div class="sex"></div>
 <van-radio-group v-model="radio" direction="horizontal">
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
      radio: '1',
    }
  },
  methods: {

  },
  mounted () {

  }
}
</script>

<style scoped>
.sex{
  display: inline-block;
  width: 10px;
}
</style>
