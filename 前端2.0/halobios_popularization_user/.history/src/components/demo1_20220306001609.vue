<template>
  <div>
      <div>以下一级分类按生活方式分类</div>
      <div  class="sort">
  <div v-for="(value,index) in sort_type_0" :key="index" class="sort_type">{{value}}</div>
   <div v-for="(value,index) in sort_type_1" :key="index" class="sort_type">{{value}}</div>
   <div v-for="(value,index) in sort_type_2" :key="index" class="sort_type">{{value}}</div>
      </div>
        
       
        
     
        
     
    
  </div>
</template>


<script>
  export default {
    data(){
      return{
       sort_type_0:[],
       sort_type_1:[],
        sort_type_2:[],
       sort_type_3:[],
      }
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

// 第一级
            this.sort_type_1[0]=res.data[0].sort_type_1;         
            for(i=0;i<res.data.length;i++){
               for(j=0;j<this.sort_type_1.length;j++){
                // 遍历完已有数组，有新成员需添加
                 if(res.data[i].sort_type_1!=this.sort_type_1[j]&&this.sort_type_1.length==j+1){
                  
                     this.sort_type_1[++j]=res.data[i].sort_type_1;
                     break;
                   }
                    // 遍历完已有数组，未有新成员需添加
                    if(res.data[i].sort_type_1==this.sort_type_1[j]){
                     break;
                   }    
            }
            }
            console.log("数组："+this.sort_type_1);

// 第二级
            this.sort_type_2[0]=res.data[0].sort_type_2;         
            for(i=0;i<res.data.length;i++){
               for(j=0;j<this.sort_type_2.length;j++){
                // 遍历完已有数组，有新成员需添加
                 if(res.data[i].sort_type_2!=this.sort_type_2[j]&&this.sort_type_2.length==j+1){
                  
                     this.sort_type_2[++j]=res.data[i].sort_type_2;
                     break;
                   }
                    // 遍历完已有数组，未有新成员需添加
                    if(res.data[i].sort_type_2==this.sort_type_2[j]){
                     break;
                   }    
            }
            }
            console.log("数组："+this.sort_type_2);           


        
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
.sort{
 display: flex;
  margin: 0 auto;
  overflow-y: hidden;
  overflow-x: scroll;
}
.sort_type{
  
  width: 60px;
  
  
  flex-direction: column;
}
</style>
