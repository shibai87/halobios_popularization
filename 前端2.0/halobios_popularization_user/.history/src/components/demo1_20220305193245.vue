<template>
  <div><!--一级菜单-->
<select name="province" id="province" class="classify" v-on:change="indexSelect01" v-model="indexId">
  <option value="选择一级菜单">选择一级菜单</option>
  <option :value="item.id" v-for="(item,index) in select01">{{item.name}}</option>  <!---->
</select>
<!--二级菜单-->
<select name="city" id="city" class="classify mt10" v-model="indexId2">
  <option value="选择二级菜单">选择二级菜单</option>
  <option :value="k.id" v-for="k in select02">{{k.name}}</option>
</select>
  </div>
</template>


<script>
  export default {
    data(){
      return{
        select01: [],//获取的一级数组数据
        select02: [],//获取的二级数组数据
        indexId:'选择一级菜单',//定义分类一的默认值
        indexId2:'选择二级菜单',
        indexNum:0,//定义一级菜单的下标
 
      }
    },
    mounted(){
        //获取json数据信息
      this.axios.get('../json/sort.json',{
        //xxxxxxxxxxxxxxxxxxx
      })
        .then((res) => {
          let mes = res.data;
          this.select01 = mes.subject;
          this.indexSelect01();
        })
        .catch((error) => {
          console.log(error)
        });
    },
    methods:{
      indexSelect01(){
        let i = 0;
        for (i = 0;i<this.select01.length;i++) {
          if (this.select01[i].id == this.indexId){
            this.indexNum = i;
            break
          }
        }
 
        this.select02 = this.select01[this.indexNum].obj;
      }
    }
  }
</script>


<style scoped>

</style>
