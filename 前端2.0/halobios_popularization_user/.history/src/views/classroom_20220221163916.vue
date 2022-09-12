<template>
  <div id="classroom">
        <!-- head -->
    <div id="classroom_head">
      <video src="../assets/images/多肉蛋糕.mp4">
      </video>
    </div>
  </div>
</template>

<script>
export default {
  name: 'classroom',
  data () {
    return {
      title: "海洋生物科普小课堂",
    }
  },
  methods: {

  },
  mounted () {

  }
}
</script>

<style scoped>
.classroom_head video{
   width:100%;
   height:20%;
}
</style>
