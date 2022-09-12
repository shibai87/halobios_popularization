<template>
    <div id="knowledge">
        <Search></Search>
        <div class="sort">
            <el-select v-model="value" placeholder="请第一级分类">
                <el-option label="海洋动物" value="海洋动物"></el-option>
                <el-option label="海洋植物" value="海洋植物"></el-option>
                <el-option label="微生物" value="微生物"></el-option>
                <el-option label="病毒" value="病毒"></el-option>
            </el-select>
            <el-select v-model="value" placeholder="请选择第二级分类" @change="getSort(value)">
                <el-option v-for="(item,index) in options0" :key="index" :value="item" :label="item">
                </el-option>
            </el-select>
        </div>
        <div>
            <img  style="width: 100%" :src="Image()"/>
          
        </div>
    </div>
</template>

<script>
    import Search from "../components/search.vue"
    export default {
        components: {
            Search
        },
        data() {
            return {
                // 存储接口数据
                sorts: {},

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
                        this.sorts = res.data;
                        console.log(this.sorts);

                        // 获取第一级分类
                        this.options0[0] = this.sorts[0].sort_type_0;
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
                        console.log(this.options0);
                    });


            },
            getSort() {
                console.log(this.value)
            },
            //图片路径
            //   Image(index, row) {
            //       var url = "http://localhost:8188/"+row.imageId
            //       console.log(url)
            //       return url
            //   },
            Image() {
                var url = "http://localhost:8083/" + "login.jpeg"
                console.log(url)
                return url
            },

        },
        mounted() {
            //   分类功能
            this.getSortList();
        }
    }
</script>
<style>
    /* sort */
    #knowledge_sort .el-select {
        width: 180px;
    }
</style>
<style scoped>
</style>