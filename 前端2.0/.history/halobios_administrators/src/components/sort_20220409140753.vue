<template>
        <div id="knowledge_sort">
            <el-select v-model="options1_value" filterable placeholder="请选择第一级分类" clearable no-match-text="未有相关数据！"
                no-data-text="空空如也，请确保上级分类已全部选完！" @change="getSort(options1_value,1)" @focus="clear(1)">
                <el-option v-for="item in options1" :key="item" :value="item">
                </el-option>
            </el-select>
            <el-select v-model="options2_value" filterable placeholder="请选择第二级分类" clearable no-match-text="未有相关数据！"
                no-data-text="空空如也，请确保上级分类已全部选完！" @change="getSort(options2_value,2)"  @focus="clear(2)">
                <el-option v-for="item in options2" :key="item" :value="item">
                </el-option>
            </el-select>
            <el-select v-model="options3_value" filterable placeholder="请选择第三级分类" clearable no-match-text="未有相关数据！"
                no-data-text="空空如也，请确保上级分类已全部选完！" @change="getSort(options3_value,3)"  @focus="clear(3)">
                <el-option v-for="item in options3" :key="item" :value="item">
                </el-option>
            </el-select>
            <el-select v-model="options4_value" filterable placeholder="请选择第四级分类" clearable no-match-text="未有相关数据！"
                no-data-text="空空如也，请确保上级分类已全部选完！" @change="getSort(options4_value,4)"  @focus="clear(4)">
                <el-option v-for="item in options4" :key="item" :value="item">
                </el-option>
            </el-select>
            <el-select v-model="options5_value" filterable placeholder="请选择第五级分类" clearable no-match-text="未有相关数据！"
                no-data-text="空空如也，请确保上级分类已全部选完！" @change="getSort(options5_value,5)"  @focus="clear(5)">
                <el-option v-for="item in options5" :key="item" :value="item">
                </el-option>
            </el-select>
            <el-select v-model="options6_value" filterable placeholder="请选择第六级分类" clearable no-match-text="未有相关数据！"
                no-data-text="空空如也，请确保上级分类已全部选完！" @change="getSort(options6_value,6)"  @focus="clear(6)">
                <el-option v-for="item in options6" :key="item" :value="item">
                </el-option>
            </el-select>
        </div>
</template>

<script>
    export default {
        name: "sort",
        data() {
            return {
                // 存储所有分类
                sorts: [],
                // 分级存储各级分类
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
            // 分类功能
            getLoadSortList() {
                this.axios
                    .get("http://192.168.1.105:8083/knowledge/get_all_sort")
                    .then((res) => {
                        this.sorts.push(res.data)
                        this.sort()
                    });
            },
            // 分类第一级
            sort() {
                this.options1.push(this.sorts[0][0].sort_type_1)
                for (let i = 1; i < this.sorts[0].length; i++) {
                    for (let j = 0; j < this.options1.length; j++) {
                        if (this.sorts[0][i].sort_type_1!= this.options1[j] && j == this.options1.length -
                            1) {
                            this.options1.push(this.sorts[0][i].sort_type_1)
                            break;
                        }
                        if (this.sorts[0][i].sort_type_1 == this.options1[j]) {
                            break;
                        }
                    }
                }
            },
            clear(step){
                  if (step == 1) {
                    this.options2_value = ""
                    this.options3_value = ""
                    this.options4_value = ""
                    this.options5_value = ""
                    this.options6_value = ""
                }
                if (step == 2) {
                    this.options3_value = ""
                    this.options4_value = ""
                    this.options5_value = ""
                    this.options6_value = ""
                }
                if (step == 3) {
                    this.options4_value = ""
                    this.options5_value = ""
                    this.options6_value = ""
                }
                if (step == 4) {
                    this.options5_value = ""
                    this.options6_value = ""
                }
                if (step == 5) {
                    this.options6_value = ""
                }
                if (step == 6) {
                  this.options6_value = ""
                }
            },
            // 查询其他级分类列表  选择了值后
            getSort(value, step) {
                if (step == 1) {
                    this.options2_value = ""
                    this.options3_value = ""
                    this.options4_value = ""
                    this.options5_value = ""
                    this.options6_value = ""
                    let options = [];
                    for (let i = 0; i < this.sorts[0].length; i++) {
                        if (this.sorts[0][i].sort_type_1 == value) {
                            options.push(this.sorts[0][i].sort_type_2)
                        }
                    }
                    // 使数据能显示
                    this.options2 = Array.from(new Set(options))

                }
                if (step == 2) {
                    let options = [];
                    for (let i = 0; i < this.sorts[0].length; i++) {
                        if (this.sorts[0][i].sort_type_2 == value) {
                            options.push(this.sorts[0][i].sort_type_3)
                        }
                    }
                    this.options3= Array.from(new Set(options))
                    this.options3_value = ""
                    this.options4_value = ""
                    this.options5_value = ""
                    this.options6_value = ""
                }
                if (step == 3) {
                    let options = [];
                    for (let i = 0; i < this.sorts[0].length; i++) {
                        if (this.sorts[0][i].sort_type_3 == value) {
                            options.push(this.sorts[0][i].sort_type_4)
                        }
                    }
                    this.options4 = Array.from(new Set(options))
                    this.options4_value = ""
                    this.options5_value = ""
                    this.options6_value = ""
                }
                if (step == 4) {
                    let options = [];
                    for (let i = 0; i < this.sorts[0].length; i++) {
                        if (this.sorts[0][i].sort_type_4 == value) {
                            options.push(this.sorts[0][i].sort_type_5)
                        }
                    }
                    this.options5 = Array.from(new Set(options))
                    this.options5_value = ""
                    this.options6_value = ""
                }
                if (step == 5) {
                    let options = [];
                    for (let i = 0; i < this.sorts[0].length; i++) {
                        if (this.sorts[0][i].sort_type_5 == value) {
                            options.push(this.sorts[0][i].sort_type_6)
                        }
                    }
                    this.options6 = Array.from(new Set(options))
                    this.options6_value = ""
                }
                if (step == 6) {
                    let options_id_last;
                    for (let i = 0; i < this.sorts[0].length; i++) {
                        if (this.sorts[0][i].sort_type_6 == value) {
                            options_id_last=this.sorts[0][i].sort_id
                        }
                    }
                      this.$store.commit("getSortIdListLast",options_id_last)
                }
            },
        },
        mounted() {
            // 分类功能
            this.getLoadSortList();
        }
    }
</script>
<style>
#knowledge_sort .el-select {
    width: 170px;
}
</style>