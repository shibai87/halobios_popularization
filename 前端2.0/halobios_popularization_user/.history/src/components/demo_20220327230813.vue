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
      show: false,
    }
  },
  methods: {
showPopup() {
      this.show = true;
    },
  },
  mounted () {

  }
}
</script>

<style scoped>

</style>
