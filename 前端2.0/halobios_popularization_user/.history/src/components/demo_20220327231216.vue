<template>
  <div>
<van-cell is-link @click="showPopup">展示弹出层</van-cell>
<van-popup v-model="show"  position="bottom" :style="{ height: '30%' }" >内容</van-popup>
  </div>
</template>

<script>
export default {
  name: '',
  data () {
    return {
      show: false,
        // 分类
                // 存储所有分类
                sorts: [],
                // 分级存储各级分类
                options0: [],
                options0_value: '',
                options1: [],
                options1_value: '',
                options2: [],
                options2_value: '',
                options3: [],
                options3_value: '',
                options4: [],
                options4_value: '',
                options5: [],
                options5_value: '',
                options6: [],
                options6_value: '',
    }
  },
  methods: {
showPopup() {
      this.show = true;
    },
       // 分类功能
            getLoadSortList() {
                this.axios
                    .get("http://localhost:8083/knowledge/get_all_sort")
                    .then((res) => {
                        this.sorts.push(res.data)
                        // console.log("所有分类")
                        console.log(this.sorts)
                        this.sort()
                    });
            },
            // 分类第一级
            sort() {
                this.options0.push(this.sorts[0][0].sort_type_0)
                for (let i = 1; i < this.sorts[0].length; i++) {
                    for (let j = 0; j < this.options0.length; j++) {
                        if (this.sorts[0][i].sort_type_0 != this.options0[j] && j == this.options0.length -
                            1) {
                            this.options0.push(this.sorts[0][i].sort_type_0)
                            break;
                        }
                        if (this.sorts[0][i].sort_type_0 == this.options0[j]) {
                            break;
                        }
                    }
                }
            },
            // 查询其他级分类列表
            getSort(value, step) {
                // console.log(step)
                if (step == 0) {
                    let options = [];
                    let options_id = [];
                    for (let i = 0; i < this.sorts[0].length; i++) {
                        if (this.sorts[0][i].sort_type_0 == value) {
                            options.push(this.sorts[0][i].sort_type_1)
                            options_id.push(this.sorts[0][i].sort_id)

                        }
                    }
                    // 使数据能显示
                    this.options1 = Array.from(new Set(options))
                    // this.$store.commit("getSortIdList", options)
                    // this.$store.commit("getSortIdListLength",options.length)
                    this.options1_value = ""
                    this.options2_value = ""
                    this.options3_value = ""
                    this.options4_value = ""
                    this.options5_value = ""
                    this.options6_value = ""
                }
                if (step == 1) {
                    let options = [];
                    let options_id = [];
                    for (let i = 0; i < this.sorts[0].length; i++) {
                        if (this.sorts[0][i].sort_type_1 == value) {
                            options.push(this.sorts[0][i].sort_type_2)
                            options_id.push(this.sorts[0][i].sort_id)
                        }
                    }
                    this.options2 = Array.from(new Set(options))
                    //   this.$store.commit("getSortIdList", options)
                    // this.$store.commit("getSortIdListLength",options.length)
                    this.options2_value = ""
                    this.options3_value = ""
                    this.options4_value = ""
                    this.options5_value = ""
                    this.options6_value = ""
                }
                if (step == 2) {
                    let options = [];
                    let options_id = [];
                    for (let i = 0; i < this.sorts[0].length; i++) {
                        if (this.sorts[0][i].sort_type_2 == value) {
                            options.push(this.sorts[0][i].sort_type_3)
                            options_id.push(this.sorts[0][i].sort_id)
                        }
                    }
                    this.options3 = Array.from(new Set(options))
                    //     this.$store.commit("getSortIdList", options)
                    // this.$store.commit("getSortIdListLength",options.length)
                    this.options3_value = ""
                    this.options4_value = ""
                    this.options5_value = ""
                    this.options6_value = ""
                }
                if (step == 3) {
                    let options = [];
                    let options_id = [];
                    for (let i = 0; i < this.sorts[0].length; i++) {
                        if (this.sorts[0][i].sort_type_3 == value) {
                            options.push(this.sorts[0][i].sort_type_4)
                            options_id.push(this.sorts[0][i].sort_id)
                        }
                    }
                    this.options4 = Array.from(new Set(options))
                    //    this.$store.commit("getSortIdList", options)
                    // this.$store.commit("getSortIdListLength",options.length)
                    this.options4_value = ""
                    this.options5_value = ""
                    this.options6_value = ""
                }
                if (step == 4) {
                    let options = [];
                    let options_id = [];
                    for (let i = 0; i < this.sorts[0].length; i++) {
                        if (this.sorts[0][i].sort_type_4 == value) {
                            options.push(this.sorts[0][i].sort_type_5)
                            options_id.push(this.sorts[0][i].sort_id)
                        }
                    }
                    this.options5 = Array.from(new Set(options))
                    //    this.$store.commit("getSortIdList", options)
                    // this.$store.commit("getSortIdListLength",options.length)
                    this.options5_value = ""
                    this.options6_value = ""
                }
                if (step == 5) {
                    let options = [];
                    let options_id = [];
                    for (let i = 0; i < this.sorts[0].length; i++) {
                        if (this.sorts[0][i].sort_type_5 == value) {
                            options.push(this.sorts[0][i].sort_type_6)
                            options_id.push(this.sorts[0][i].sort_id)
                        }
                    }
                    this.options6 = Array.from(new Set(options))
                    //    this.$store.commit("getSortIdList", options)
                    // this.$store.commit("getSortIdListLength",options.length)
                    this.options6_value = ""
                }
                if (step == 6) {
                    let options_id_last;
                //    let options_id = [];
                    for (let i = 0; i < this.sorts[0].length; i++) {
                        if (this.sorts[0][i].sort_type_6 == value) {
                            // options_id=this.sorts[0][i].sort_id
                            options_id_last=this.sorts[0][i].sort_id
                        }
                    }
                    //    this.$store.commit("getSortIdList",options_id)
                    // this.$store.commit("getSortIdListLength",options_id.length)
                      this.$store.commit("getSortIdListLast",options_id_last)

                }
            },
  },
  mounted () {
 // 分类功能
            this.getLoadSortList();
  }
}
</script>

<style scoped>

</style>
