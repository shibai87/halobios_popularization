<template>

    <div class="knowledge_sort">
        <Search></Search>
        <el-select v-model="value" placeholder="请选择第一级分类" @change="getSort(value)">
            <el-option v-for="(item,index) in options0" :key="index" :value="item">
            </el-option>
        </el-select>

        <!-- <div> -->
        <el-dropdown>
                <i class="el-icon-setting" style="margin-right: 15px"></i>
                <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item>查看</el-dropdown-item>
                    <el-dropdown-item>新增</el-dropdown-item>
                    <el-dropdown-item>删除</el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
        <!-- </div> -->
        <div>
             <el-button type="primary">添加新科普知识</el-button>
        </div>
        <div>
            左图右文
        </div>
    </div>

</template>

<script>
import Search from "../components/search.vue"
    export default {
         components:{
      Search
    },
        data() {
            return {
                // 存储接口数据
                sorts:{},

                options0: [],
                value: '',
            }
        },
        methods: {
            getSortList() {
                this.axios
                    .get("http://localhost:8083/sort/get_all")
                    .then((res) => {
                        // console.log(res.data);
                        this.sorts=res.data;
                        console.log(this.sorts);

                        // 获取第一级分类
                        this.options0[0] = this.sorts[0].sort_type_0;
                        for (let i = 1; i < this.sorts.length; i++) {
                            for (let j = 0; j < this.options0.length; j++) {
                                if (this.sorts[i].sort_type_0 != this.options0[j] && j == this.options0.length - 1) {
                                    this.options0[++j] = this.sorts[i].sort_type_0;
                                    break;
                                }
                                if (this.sorts[i].sort_type_0 == this.options0[j]) {
                                    break;
                                }
                            }
                        }
                        console.log(this.options0);



                    });
                    

            },
            getSort() {
                console.log(this.value)
            }
        },
        mounted() {

            //   分类功能
            this.getSortList();
        }
    }
</script>
<style>
    /* knowledge_sort */
    .knowledge_sort .el-select {
        width: 180px;
    }
</style>
<style scoped>

</style>