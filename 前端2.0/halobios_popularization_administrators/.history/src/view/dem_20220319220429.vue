<template>
  <el-select v-model="value" placeholder="请选择">
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
        options: [{
          value: '选项1',
          label: '黄金糕'
        }, {
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
        }],
        value: ''
      }
    },
    methods:{
         getSortList() {
                this.axios
                    .get("http://localhost:8083/sort/get_all")
                    .then((res) => {
                        console.log(res.data[0].sort_type_0);
                        for(let i=0;i<res.data.length;i++){
                             console.log(res.data[0]);
                             this.options0[0]=res.data[1].sort_type_0;
                            //  this.options1[0]=res.data[0].sort_type_1
                            //   this.options2[0]=res.data[0].sort_type_2
                            //  this.options3[0]=res.data[0].sort_type_3
                            //   this.options4[0]=res.data[0].sort_type_4
                            //   this.options5[0]=res.data[0].sort_type_5
                            //  this.options6[0]=res.data[0].sort_type_6;
                             console.log( this.options0);
                        }
                    });
            }
    },
      mounted() {
         
            //   分类功能
            this.getSortList();
        }
  }
</script>