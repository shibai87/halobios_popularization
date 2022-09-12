<template>
    <div id="knowledge_sort">
        <el-select v-model="options0_value" filterable  placeholder="请第一级分类">
            <el-option v-for="(item,index) in options0" :key="index" :value="item" value-key>
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
                options0: [
                   
                    

                ],
                options0_value: '',
            }
        },
        methods: {
            getLoadSortList() {
                this.axios
                    .get("http://localhost:8083/knowledge/get_all_sort")
                    .then((res) => {
                        this.sorts = res.data;
                        console.log(this.sorts)
                        this.sort()
                    });
            },
            sort() {
                console.log("11111")
                // 获取第一级分类
                 this.$set(this.options0, 'sort_id', "")
                // this.options0[0].sort_type_0 = this.sorts[0].sort_type_0;
                // for (let i = 1; i < this.sorts.length; i++) {
                //     for (let j = 0; j < this.options0.length; j++) {
                //         if (this.sorts[i].sort_type_0 != this.options0[j].sort_type_0 && j == this.options0.length -
                //             1) {
                //             this.options0[++j].sort_type_0 = this.sorts[i].sort_type_0;
                //             break;
                //         }
                //         if (this.sorts[i].sort_type_0 == this.options0[j].sort_type_0) {
                //             break;
                //         }
                //     }
                // }
                console.log(this.options0);
            }
        },
        mounted() {
            //   分类功能
            this.getLoadSortList();
        }
    }
</script>