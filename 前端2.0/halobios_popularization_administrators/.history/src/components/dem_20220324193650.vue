<template>
    <div id="knowledge_sort">
         <el-select v-model="options0_value" filterable placeholder="请选择">
            <el-option v-for="item in options0" :key="item" :value="item">
            </el-option>
        </el-select>
    </div>
</template>
<script>
    export default {
        name: "knowledge",
        data() {
            return {
                sorts: [],
                options0: [],
                list: "",
                options0_value: '',
            }
        },
        methods: {
            getLoadSortList() {
                var arr = this;
                arr.axios
                    .get("http://localhost:8083/knowledge/get_all_sort")
                    .then((res) => {
                        this.sorts.push(res.data)
                        // console.log(arr.sorts)
                        // console.log(typeof arr.sorts);
                      
                      
                        // arr.sorts = res.data;
                        // console.log(arr.sorts)
                        //  console.log(typeof arr.sorts);
                        

                        this.sort()
                    });

            },
            sort() {
                console.log("11111")
                // 获取第一级分类
            // this.options0.push(this.sorts[0][0].sort_type_0)
            //   console.log(this.sorts[0][0],this.options0)

 this.options0[0] = this.sorts[0].sort_type_0;
                console.log(this.options0[0])
                for (let i = 1; i < this.sorts.length; i++) {
                    for (let j = 0; j < this.options0.length; j++) {
                        if (this.sorts[i].sort_type_0 != this.options0[j] && j == this.options0.length -
                            1) {
                            this.options0[++j] = this.sorts[i].sort_type_0;
                            break;
                        }
                        if (this.sorts[i].sort_type_0 == this.options0[j]) {
                            break;
                        }
                    }
                }
                console.log(typeof this.options0);
                console.log(this.options0);

                // this.options0[0] = this.sorts[0].sort_type_0;
                // console.log(this.options0[0])
                // for (let i = 1; i < this.sorts.length; i++) {
                //     for (let j = 0; j < this.options0.length; j++) {
                //         if (this.sorts[i].sort_type_0 != this.options0[j] && j == this.options0.length -
                //             1) {
                //             this.options0[++j] = this.sorts[i].sort_type_0;
                //             break;
                //         }
                //         if (this.sorts[i].sort_type_0 == this.options0[j]) {
                //             break;
                //         }
                //     }
                // }
                // console.log(typeof this.options0);
                // console.log(this.options0);
            }
        },
        mounted() {
            //   分类功能
            this.getLoadSortList();
        }
    }
</script>