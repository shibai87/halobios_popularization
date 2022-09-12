<template>
  <div>
      <van-cell is-link @click="showPopup">展示弹出层</van-cell>
 <van-popup v-model="show" position="bottom" :style="{ height: '30%' }">
          <van-picker title="请选择搜索的标题" show-toolbar :columns="columns" @confirm="onConfirm" @cancel="onCancel" />
        </van-popup>
  </div>
</template>

<script>
export default {
  data() {
    return {
      show: false,
    };
  },

  methods: {
    showPopup() {
      this.show = true;
    },
  },
};
</script>

<style scoped>

</style>
