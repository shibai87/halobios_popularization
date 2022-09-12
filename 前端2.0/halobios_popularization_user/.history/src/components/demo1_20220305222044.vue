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
        var name="";
      this.$axios.get('sort1.json')
        .then(res=> {
            console.log(res.data,res.data.length)
            for(var i=0;i<res.data.length;i++){
              this.name=res.data[0].sort_type_0
            }

        
        })
        .catch((error) => {
          console.log(error)
        });
    }
  
  }
</script>


<style scoped>

</style>
