<template>
  <div>
      <div>以下一级分类按生活方式分类</div>
     
     
    
  </div>
</template>


<script>
  export default {
    data(){
      return{
       sort_type:"",
      }
    },
    methods: {
      loadSortData(){
        let i,j;
        //获取json数据信息
      this.$axios.get('sort1.json')
        .then((res)=> {
            console.log(res.data,res.data.length);
            for(i=0;i<res.data.length;i++){
                this.sort_type[0]=res.data[0].sort_type_0;
                for(j=0,j<res.data.length-1;j++){
                  this.sort_type[0]=res.data[0].sort_type_0;
                }
            }

        
        })
        .catch((error) => {
          console.log(error)
        });
    
    },
    mounted() {
    this.loadSortData();
    
  },
  
  }
  }
</script>


<style scoped>

</style>
