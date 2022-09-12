<template>
  <div>
     <van-cell is-link @click="showPopup">展示弹出层</van-cell>
      <van-popup v-model="show" position="bottom"   closeable  round  close-icon="close" :style="{ height: '30%' }" :get-container="getContainer">内容</van-popup>
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
  // 显示弹出
       showPopup() {
      this.show = true;
    },
    // 返回一个特定的 DOM 节点，作为挂载的父节点
    getContainer() {
      return document.querySelector('.keyboard');
    },
  },
  mounted () {

  }
}
</script>

<style scoped>

</style>
