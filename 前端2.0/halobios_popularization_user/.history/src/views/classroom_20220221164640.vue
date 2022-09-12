<template>
  <div id="classroom">
        <!-- head -->
    <div id="classroom_head">
        <video-player class="video-player vjs-custom-skin" 
            ref="videoPlayer" 
            :playsinline="true" 
            :options="playerOptions">
      </video-player>
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
