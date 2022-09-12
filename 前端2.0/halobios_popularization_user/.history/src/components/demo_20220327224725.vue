<template>
  <div>
<van-dropdown-menu>
  <van-dropdown-item v-model="value1" :options="option0" />
  <van-dropdown-item v-model="value2" :options="option2" />
</van-dropdown-menu>
  </div>
</template>

<script>
export default {
  name: '',
  data () {
    return {
       value1: 0,
      value2: 'a',
      // option1: [
      //   { text: '全部商品', value: 0 },
      //   { text: '新款商品', value: 1 },
      //   { text: '活动商品', value: 2 },
      // ],
      option2: [
        { text: '默认排序', value: 'a' },
        { text: '好评排序', value: 'b' },
        { text: '销量排序', value: 'c' },
      ],

       // 存储所有分类
                sorts: [],
                options0: [],
    }
  },
  methods: {
     // 分类功能
            getLoadSortList() {
                this.axios
                    .get("http://192.168.1.103:8083/knowledge/get_all_sort")
                    .then((res) => {
                      console.log(res.data)
                      this.sorts=res.data
                      console.log(this.sorts.length)
                        // this.sorts.push(res.data)
                        // console.log("所有分类")
                        // console.log(this.sorts)
                        this.sort()
                    });
            },
              sort() {
                this.options0=this.sorts[0].sort_type_0
                console.log(this.options0)
                // this.options0.push(this.sorts[0][0].sort_type_0)
                for (let i = 1; i < this.sorts.length; i++) {
                    for (let j = 0; j < this.options0.length; j++) {
                        if (this.sorts[i].sort_type_0 != this.options0[j] && j == this.options0.length -
                            1) {
                              this.options0=this.sorts[i].sort_type_0
                            // this.options0.push(this.sorts[0][i].sort_type_0)
                            break;
                        }
                        if (this.sorts[i].sort_type_0 == this.options0[j]) {
                            break;
                        }
                    }
                }
                 console.log(this.options0)
            },
  },
  mounted () {
    this.getLoadSortList()
  }
}
</script>

<style scoped>

</style>
