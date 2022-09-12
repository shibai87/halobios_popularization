<template>
  <el-select v-model="value" placeholder="请选择" @change="getSort(value)">
    <el-option
      v-for="item in options"
      :key="item.value"
      :label="item.label"
      :value="item.value">
    </el-option>
  </el-select>
</template>

<script>
  export default {
    data() {
      return {
        options: [
         {
          value: '选项2',
          label: '双皮奶'
        }, {
          value: '选项3',
          label: '蚵仔煎'
        }, {
          value: '选项4',
          label: '龙须面'
        }, {
          value: '选项5',
          label: '北京烤鸭'
        }
        ],
        value: ''
      }
    },
    methods:{
        getSort(){
            console.log(this.value)
        }
    //      getSortList() {
    //             this.axios
    //                 .get("http://localhost:8083/sort/get_all")
    //                 .then((res) => {
    //                     console.log(res.data[0].sort_type_0);
    //                     // for(let i=0;i<res.data.length;i++){
    //                          console.log(res.data[0]);
    //                          this.options[0].value=res.data[0].sort_type_0;
    //                          this.options[0].label=res.data[0].sort_type_0;
                            
                           
    //                          console.log( this.options0);
    //                     // }
    //                 });
    //         }
    },
    //   mounted() {
         
    //         //   分类功能
    //         this.getSortList();
    //     }
  }
</script>