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
        name: "knowledge",
        data() {
            return {
                // 存储所有分类
                sorts: [],
                // 分级存储各级分类
                options0: [],
                options0_id:[],
                options0_value: '',
                options1: [],
                options1_id:[],
                options1_value: '',
                options2: [],
                options2_id:[],
                options2_value: '',
                options3: [],
                options3_id:[],
                options3_value: '',
                options4: [],
                options4_id:[],
                options4_value: '',
                options5: [],
                options5_id:[],
                options5_value: '',
                options6: [],
                options6_id:[],
                options6_value: '',

            }
        },
        methods: {
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
                // console.log(this.options0[0], this.options0.length, this.sorts[0], this.sorts[0].length)
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
                this.$store.commit("getSortId",this.options0_id)
                if (step == 0) {
                    for (let i = 0; i < this.sorts[0].length; i++) {
                        if (this.sorts[0][i].sort_type_0 == value) {
                            this.options1.push(this.sorts[0][i].sort_type_1)
                           this.options1_id.push(this.sorts[0][i].sort_id)
                            // console.log(this.sorts[0][i].sort_type_1)
                        }
                    }
                    this.options1 = Array.from(new Set(this.options1))
                    // console.log(this.options1)
                    //  console.log("options1_id")
                    // console.log(this.options1_id)
                }
                if (step == 1) {
                    for (let i = 0; i < this.sorts[0].length; i++) {
                        if (this.sorts[0][i].sort_type_1 == value) {
                            this.options2.push(this.sorts[0][i].sort_type_2)
                        }
                    }
                    this.options2 = Array.from(new Set(this.options2))
                    // console.log(this.options2)
                }
                if (step == 2) {
                    for (let i = 0; i < this.sorts[0].length; i++) {
                        if (this.sorts[0][i].sort_type_2 == value) {
                            this.options3.push(this.sorts[0][i].sort_type_3)
                        }
                    }
                    this.options3 = Array.from(new Set(this.options3))
                    // console.log(this.options3)
                }
                if (step == 3) {
                    for (let i = 0; i < this.sorts[0].length; i++) {
                        if (this.sorts[0][i].sort_type_3 == value) {
                            this.options4.push(this.sorts[0][i].sort_type_4)
                        }
                    }
                    this.options4 = Array.from(new Set(this.options4))
                    // console.log(this.options1)
                }
                if (step == 4) {
                    for (let i = 0; i < this.sorts[0].length; i++) {
                        if (this.sorts[0][i].sort_type_4 == value) {
                            this.options5.push(this.sorts[0][i].sort_type_5)
                        }
                    }
                    this.options5 = Array.from(new Set(this.options5))
                    // console.log(this.options2)
                }
                if (step == 5) {
                    for (let i = 0; i < this.sorts[0].length; i++) {
                        if (this.sorts[0][i].sort_type_5 == value) {
                            this.options6.push(this.sorts[0][i].sort_type_6)
                        }
                    }
                    this.options6 = Array.from(new Set(this.options6))
                    // console.log(this.options3)
                }
                //   if(step==6){

                // }
            }
        },
        mounted() {
            this.getLoadSortList();
        }
    }
</script>
<style>
#knowledge_sort .el-alert{
margin: 10px 0;
}
</style>