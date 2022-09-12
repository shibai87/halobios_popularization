<template>
  <div>
<van-cell is-link @click="showPopup">展示弹出层</van-cell>
<van-popup v-model="show">内容</van-popup>
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

</style>
