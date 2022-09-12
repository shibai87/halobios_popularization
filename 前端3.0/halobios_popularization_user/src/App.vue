<!-- App.vue -->
<template>
  <router-view v-if="isRouterAlive"></router-view>
</template>

<script lang="ts" setup>
import{ref,nextTick,provide} from 'vue'
//刷新
const isRouterAlive=ref(true);
const reload=()=>{
  isRouterAlive.value=false;
  nextTick(()=>{
    isRouterAlive.value=true;
  });
};
  provide("reload",reload);

</script>
<style>
  #app {
    padding: 0;
    margin: 0;
    text-decoration: none;
    color: #000;

  }
a{
  color: #000;
}
</style>