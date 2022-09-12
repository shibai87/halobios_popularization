<template>

        <div id="knowledge_sort">
            <el-alert title=" 请按从左至右的顺序选择分类（分类分别表示：域、界、门、纲、目、科、属）！" type="success" :closable="false"></el-alert>
            <el-select v-model="options0_value" filterable placeholder="请选择第零级分类" clearable no-match-text="未有相关数据！"
                no-data-text="空空如也！" @change="getSort(options0_value,0)">
                <el-option v-for="item in options0" :key="item" :value="item">
                </el-option>
            </el-select>
            <el-select v-model="options1_value" filterable placeholder="请选择第一级分类" clearable no-match-text="未有相关数据！"
                no-data-text="空空如也！" @change="getSort(options1_value,1)">
                <el-option v-for="item in options1" :key="item" :value="item">
                </el-option>
            </el-select>
            <el-select v-model="options2_value" filterable placeholder="请选择第二级分类" clearable no-match-text="未有相关数据！"
                no-data-text="空空如也！" @change="getSort(options2_value,2)">
                <el-option v-for="item in options2" :key="item" :value="item">
                </el-option>
            </el-select>
            <el-select v-model="options3_value" filterable placeholder="请选择第三级分类" clearable no-match-text="未有相关数据！"
                no-data-text="空空如也！" @change="getSort(options3_value,3)">
                <el-option v-for="item in options3" :key="item" :value="item">
                </el-option>
            </el-select>
            <el-select v-model="options4_value" filterable placeholder="请选择第四级分类" clearable no-match-text="未有相关数据！"
                no-data-text="空空如也！" @change="getSort(options4_value,4)">
                <el-option v-for="item in options4" :key="item" :value="item">
                </el-option>
            </el-select>
            <el-select v-model="options5_value" filterable placeholder="请选择第五级分类" clearable no-match-text="未有相关数据！"
                no-data-text="空空如也！" @change="getSort(options5_value,5)">
                <el-option v-for="item in options5" :key="item" :value="item">
                </el-option>
            </el-select>
            <el-select v-model="options6_value" filterable placeholder="请选择第六级分类" clearable no-match-text="未有相关数据！"
                no-data-text="空空如也！" @change="getSort(options6_value,6)">
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

                // 

            }
        },
        methods: {
            // 分类功能
            getLoadSortList() {
                this.axios
                    .get("http://localhost:8083/knowledge/get_all_sort")
                    .then((res) => {
                        this.sorts.push(res.data)
                        // console.log("所有分类")
                        // console.log(this.sorts)
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
                // console.log(typeof this.options0);
                // console.log(this.options0);
            },
            getSort(value, step) {
                console.log(step)
                if (step == 0) {
                    let options = [];
                    let options_id = [];
                    for (let i = 0; i < this.sorts[0].length; i++) {
                        if (this.sorts[0][i].sort_type_0 == value) {
                            options.push(this.sorts[0][i].sort_type_1)
                            options_id.push(this.sorts[0][i].sort_id)

                        }
                    }
                    this.options1 = Array.from(new Set(options))
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

                    this.options6_value = ""
                }
                if (step == 6) {
                    let options_id = [];
                    for (let i = 0; i < this.sorts[0].length; i++) {
                        if (this.sorts[0][i].sort_type_6 == value) {
                            options_id.push(this.sorts[0][i].sort_id)
                        }
                    }

                }
            },




            

        },
        computed: {

        },
        mounted() {
            // 分类功能
            this.getLoadSortList();
        }
    }
</script>
<style>
    /* 分类功能 */
    #knowledge_sort .el-alert {
        margin: 10px 0;
    }
</style>
<style scoped>
</style>