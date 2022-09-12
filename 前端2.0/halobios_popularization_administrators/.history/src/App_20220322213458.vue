<template>
  <div id="app">
    <router-view v-if="isRouterAlive" ></router-view>
  </div>
</template>

<script>

export default {
  name: 'App',
   provide() {
    return {
      reload: this.reload,
    };
  },
  data() {
    return {
      isRouterAlive: true,
    };
  },
  methods: {
    reload() {
      // alert("0000000000000000000");
      this.isRouterAlive = false;
      this.$nextTick(function () {
        this.isRouterAlive = true;
      });
    },
  },
}
</script>

<style>
*{
  padding: 0;
margin: 0;
text-decoration:none;
color: #000;

}
</style>
