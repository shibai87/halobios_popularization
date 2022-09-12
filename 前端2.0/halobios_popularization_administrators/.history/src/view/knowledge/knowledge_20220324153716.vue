<template>
    <div id="knowledge">
        <div class="sort">
            <el-select v-model="options0_value" placeholder="请第一级分类" @change="get_options0_value(options0_value)">
                <el-option v-for="(item,index) in options0" :key="index" :value="item" :label="item">
                </el-option>
            </el-select>
            <el-select v-model="options1_value" placeholder="请选择第二级分类" @change="get_options1_value(options1_value)">
                <el-option v-for="(item,index) in options1" :key="index" :value="item" :label="item">
                </el-option>
            </el-select>
        </div>
        <!-- 分类知识详情 -->
        <div>
            <el-row>
                <el-col :span="4">
                    <!-- <div class="grid-content bg-purple"><img style="width: 130px; height: 130px;" :src="Image()" /> -->
                    <img style="width: 130px; height: 130px;" src="../../assets/img/login.jpeg" class="knowledge_img">
                </el-col>
                <el-col :span="12">
                    <div class="knowledge_content">
                        <div class="knowledge_title"></div>
                        <div class="knowledge_info"></div>
                    </div>
                </el-col>
                <el-col :span="8">
                    <div class="grid-content bg-purple-light"></div>
                </el-col>
            </el-row>
        </div>
    </div>
</template>

<script>
    export default {
        name: "knowledge",
        data() {
            return {
                // 存储接口数据
                sorts: {},
                options0: [],
                options0_value: '',
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
            getKnowledge() {
                this.axios.get()
            },
            //图片路径
            //   Image(index, row) {
            //       var url = "http://localhost:8188/"+row.imageId
            //       console.log(url)
            //       return url
            //   },
            Image() {

                var url = "http://localhost:8083/" + "../../assets/img/login.jpeg"
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