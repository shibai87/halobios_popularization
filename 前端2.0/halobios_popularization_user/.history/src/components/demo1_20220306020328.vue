<template>
  <div>

     <ul class="sort">
       <!-- <li>q</li> -->
  <li v-for="(value,index) in sort_type_0" :key="index" class="sort_type">{{sort_type_0[0]}}{{sort_type_0[1]}}</li>
 
  
      </ul>
  </div>
</template>


<script>
  export default {
    data(){
      return{
       sort_type_0:[],
     
      }
    },
     mounted() {
    this.loadSortData();
    
  },
    methods: {
      loadSortData(){
        let i,j;
        //获取json数据信息
      this.$axios.get('sort1.json')
        .then((res)=> {
            console.log(res.data,res.data.length);
            // 未设置为空情况

  // 第零级
            this.sort_type_0[0]=res.data[0].sort_type_0;
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
            
            }
            console.log("数组："+this.sort_type_0);
                 


        
        })
        .catch((error) => {
          console.log(error)
        });
    
    },
    },
   
  
  
  }
</script>


<style scoped>

.sort{
 display: inline-flex;
  margin: 0 auto;
  overflow-y: hidden;
  overflow-x: scroll;
  
}
.sort_type{
  font-size: 10px;
  width: 70px;
  height: 20px;
  
}
</style>
