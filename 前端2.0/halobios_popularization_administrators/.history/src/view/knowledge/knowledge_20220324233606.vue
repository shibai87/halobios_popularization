<template>
    <div id="knowledge">
        <Sort></Sort>
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
import Sort from "../../components/sort.vue"
    export default {
        name: "knowledge",
        components:{
            Sort
        },
        data() {
            return {
             

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




            // 
            getLoadKnowledge() {

                //   if(getStep==1){

                //   }
                // this.axios.get()
            },
            //图片路径
            //   Image(index, row) {
            //       var url = "http://localhost:8188/"+row.imageId
            //       console.log(url)
            //       return url
            //   },
            // Image() {

            //     var url = "http://localhost:8083/" + "../../assets/img/login.jpeg"
            //     console.log(url)
            //     return url
            // },

        },
        computed: {

        },
        mounted() {
            // 分类功能
            this.getLoadSortList();

            this.getLoadKnowledge();
        }
    }
</script>
<style>
    /* 分类功能 */
    #knowledge_sort .el-alert {
        margin: 10px 0;
    }

    /* sort */
    #knowledge_sort .el-select {
        width: 180px;
    }
</style>
<style scoped>
</style>