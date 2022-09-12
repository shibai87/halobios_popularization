<template>
    <div id="head">
      <van-nav-bar :title="change_title" left-text="返回" left-arrow fixed @click-left="pageBack" @click-right="search">
        <template #right>
          <van-icon name="search" size="20" />
        </template>
      </van-nav-bar>
    </div>
</template>

<script>
export default {
  name: 'head',
  data () {
    return {

    }
  },
   computed:{
       change_title(){
          return  this.$store.state.title
       }
      
    },
  methods: {
        pageBack() {
       
        window.history.back()
      },
      search() {
        this.$router.push("/search");
      },
  },
  mounted () {
     
  },

}
</script>

<style scoped>

</style>
