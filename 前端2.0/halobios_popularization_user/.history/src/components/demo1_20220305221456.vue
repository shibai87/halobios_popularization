<template>
  <div>
      <div>以下一级分类按生活方式分类</div>
     
     
    
  </div>
</template>


<script>
  export default {
    data(){
      return{
       
      }
    },
    mounted(){
        //获取json数据信息
      this.$axios.get('sort1.json')
        .then(res=> {
            console.log(res.data),
            

        
        })
        .catch((error) => {
          console.log(error)
        });
    },
    methods:{
     
    }
  }
</script>


<style scoped>

</style>
