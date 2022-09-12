<template>
  <div>
      <div>以下一级分类按生活方式分类</div>

     
    
  </div>
</template>


<script>
  export default {
    data(){
      return{
       sort_type_0:[],
      }
    },
    methods: {
      loadSortData(){
        let i,j;
        //获取json数据信息
      this.$axios.get('sort1.json')
        .then((res)=> {
            console.log(res.data,res.data.length);
            
            this.sort_type_0[0]=res.data[0].sort_type_0;
            // 未设置为空情况
            for(i=0;i<res.data.length;i++){
               for(j=0;j<this.sort_type_0.length;j++){
                // 遍历完已有数组，有新成员需添加
                 if(res.data[i].sort_type_0!=this.sort_type_0[j]&&this.sort_type_0.length==j+1){
                  
                     this.sort_type_0[++j]=res.data[i].sort_type_0;
                     break;
                   }
                    // 遍历完已有数组，未有新成员需添加
                    if(res.data[i].sort_type_0==this.sort_type_0[j]){
                     break;
                   }    
            }
            console.log("数组长度："+this.sort_type_0.length+"数组："+this.sort_type_0);
            }
           


        
        })
        .catch((error) => {
          console.log(error)
        });
    
    },
    },
    mounted() {
    this.loadSortData();
    
  },
  
  
  }
</script>


<style scoped>

</style>
