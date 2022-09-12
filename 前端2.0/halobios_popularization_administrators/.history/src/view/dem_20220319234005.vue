<template>
  <el-select v-model="value" placeholder="请选择" @change="getSort(value)">
    <el-option
      v-for="(item,index) in options"
      :key="index"
      :value="item">
    </el-option>
  </el-select>
</template>

<script>
  export default {
    data() {
      return {
          options:[],
        value: ''
      }
    },
    methods:{
        getSort(){
            console.log(this.value)
        },
      getSortList() {
                this.axios
                    .get("http://localhost:8083/sort/get_all")
                    .then((res) => {
                        console.log(res.data);
                        this.options0[0]= res.data[0].sort_type_0;
                       
                        for (let i = 1; i < res.data.length; i++) {
                            for (let j = 0; j <this.options0.length; j++) {
                                if (res.data[i].sort_type_0 != this.options0[j] && j ==this.options0.length - 1) {
                                    this.options0[++j]= res.data[i].sort_type_0;
                                    break;
                                }
                                if (res.data[i].sort_type_0 == this.options0[j]) {
                                    break;
                                }
                            }
                        }
                         console.log(this.options0);
                       
                    });
            },
    },
    //   mounted() {
         
    //         //   分类功能
    //         this.getSortList();
    //     }
  }
</script>