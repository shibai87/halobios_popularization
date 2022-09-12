<template>
    <div id="knowledge_sort">
         <el-alert title=" 请按从左至右的顺序选择分类！" type="success" :closable="false"></el-alert>
  
        <el-select v-model="options0_value" filterable placeholder="请选择第一级分类" clearable no-match-text="未有相关数据！" no-data-text="空空如也！" @change="getSort(options0_value,0)">
            <el-option v-for="item in options0" :key="item" :value="item">
            </el-option>
        </el-select>
         <el-select v-model="options1_value" filterable placeholder="请选择第二级分类" clearable no-match-text="未有相关数据！" no-data-text="空空如也！" @change="getSort(options1_value,1)">
            <el-option v-for="item in options1" :key="item" :value="item">
            </el-option>
        </el-select>
         <el-select v-model="options2_value" filterable placeholder="请选择第三级分类" clearable no-match-text="未有相关数据！" no-data-text="空空如也！" @change="getSort(options2_value,2)">
            <el-option v-for="item in options2" :key="item" :value="item">
            </el-option>
        </el-select>
         <el-select v-model="options3_value" filterable placeholder="请选择第四级分类" clearable no-match-text="未有相关数据！" no-data-text="空空如也！" @change="getSort(options3_value,3)">
            <el-option v-for="item in options3" :key="item" :value="item">
            </el-option>
        </el-select>
         <el-select v-model="options4_value" filterable placeholder="请选择第五级分类" clearable no-match-text="未有相关数据！" no-data-text="空空如也！" @change="getSort(options4_value,4)">
            <el-option v-for="item in options4" :key="item" :value="item">
            </el-option>
        </el-select>
         <el-select v-model="options5_value" filterable placeholder="请选择第六级分类" clearable no-match-text="未有相关数据！" no-data-text="空空如也！" @change="getSort(options5_value,5)">
            <el-option v-for="item in options5" :key="item" :value="item">
            </el-option>
        </el-select>
         <el-select v-model="options6_value" filterable placeholder="请选择第七级分类" clearable no-match-text="未有相关数据！" no-data-text="空空如也！" @change="getSort(options6_value,6)">
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
                sorts: [],
                options0: [],
                options0_value: '',
                  options1: [],
                options1_value: '',
                  options2: [],
                options2_value: '',
                  options3: [],
                options3_value: '',
            }
        },
        methods: {
            getLoadSortList() {
                this.axios
                    .get("http://localhost:8083/knowledge/get_all_sort")
                    .then((res) => {
                        this.sorts.push(res.data)
                        console.log("所有分类")
                        console.log(this.sorts)
                        this.sort()
                    });

            },
            // 分类第一级
            sort() {
                this.options0.push(this.sorts[0][0].sort_type_0)
                console.log(this.options0[0], this.options0.length, this.sorts[0], this.sorts[0].length)
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
            getSort(value,step){
                if(step==0){
                    for (let i = 1; i < this.sorts[0].length; i++) {
                        if(this.sorts[0][i].sort_type_0==value){
                             this.options1.push(this.sorts[0][i].sort_type_1)
                        }
                    }
                    this.options1=Array.from(new Set(this.options1))
                    // console.log(this.options1)
                }
                 if(step==1){
                    for (let i = 1; i < this.sorts[0].length; i++) {
                        if(this.sorts[0][i].sort_type_1==value){
                             this.options2.push(this.sorts[0][i].sort_type_2)
                        }
                    }
                    this.options2=Array.from(new Set(this.options2))
                    // console.log(this.options2)
                }
                  if(step==2){
                    for (let i = 1; i < this.sorts[0].length; i++) {
                        if(this.sorts[0][i].sort_type_2==value){
                             this.options3.push(this.sorts[0][i].sort_type_3)
                        }
                    }
                    this.options3=Array.from(new Set(this.options3))
                    // console.log(this.options3)
                }
            }



        },
        mounted() {
            this.getLoadSortList();
        }
    }
</script>
<style>

</style>