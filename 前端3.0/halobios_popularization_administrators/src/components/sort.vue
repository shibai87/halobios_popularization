<template>
    <div id="knowledge_sort">
        <el-select v-model="options1_value" filterable placeholder="请选择第一级分类" clearable no-match-text="未有相关数据！"
            no-data-text="空空如也，请确保上级分类已全部选完！" @change="getSort(options1_value, 1)" @focus="clear(1)">
            <el-option v-for="item in options1" :key="item" :value="item">
            </el-option>
        </el-select>
        <el-select v-model="options2_value" filterable placeholder="请选择第二级分类" clearable no-match-text="未有相关数据！"
            no-data-text="空空如也，请确保上级分类已全部选完！" @change="getSort(options2_value, 2)" @focus="clear(2)">
            <el-option v-for="item in options2" :key="item" :value="item">
            </el-option>
        </el-select>
        <el-select v-model="options3_value" filterable placeholder="请选择第三级分类" clearable no-match-text="未有相关数据！"
            no-data-text="空空如也，请确保上级分类已全部选完！" @change="getSort(options3_value, 3)" @focus="clear(3)">
            <el-option v-for="item in options3" :key="item" :value="item">
            </el-option>
        </el-select>
        <el-select v-model="options4_value" filterable placeholder="请选择第四级分类" clearable no-match-text="未有相关数据！"
            no-data-text="空空如也，请确保上级分类已全部选完！" @change="getSort(options4_value, 4)" @focus="clear(4)">
            <el-option v-for="item in options4" :key="item" :value="item">
            </el-option>
        </el-select>
        <el-select v-model="options5_value" filterable placeholder="请选择第五级分类" clearable no-match-text="未有相关数据！"
            no-data-text="空空如也，请确保上级分类已全部选完！" @change="getSort(options5_value, 5)" @focus="clear(5)">
            <el-option v-for="item in options5" :key="item" :value="item">
            </el-option>
        </el-select>
        <el-select v-model="options6_value" filterable placeholder="请选择第六级分类" clearable no-match-text="未有相关数据！"
            no-data-text="空空如也，请确保上级分类已全部选完！" @change="getSort(options6_value, 6)" @focus="clear(6)">
            <el-option v-for="item in options6" :key="item" :value="item">
            </el-option>
        </el-select>
    </div>
</template>

<script setup lang="ts">
import KnowledgeService from "../api/KnowledgeService.js"
import { onMounted, ref, reactive } from "vue"
import {useStore} from "vuex"
const store = useStore()
// 存储所有分类
const sorts = reactive<any>([])
// 分级存储各级分类
const options1 = reactive<any>([])
const options1_value = ref("")
const options2 = reactive<any>([])
const options2_value = ref("")
const options3 = reactive<any>([])
const options3_value = ref("")
const options4 = reactive<any>([])
const options4_value = ref("")
const options5 = reactive<any>([])
const options5_value = ref("")
const options6 = reactive<any>([])
const options6_value = ref("")

// 分类功能
onMounted(() => {
    KnowledgeService.get_all_sort().then((res) => {
        sorts.push(res)
        sort()
    });
})

// 分类第一级
const sort = () => {
    options1.push(sorts[0][0].sort_type_1)
    for (let i = 1; i < sorts[0].length; i++) {
        for (let j = 0; j < options1.length; j++) {
            if (sorts[0][i].sort_type_1 != options1[j] && j == options1.length - 1) {
                options1.push(sorts[0][i].sort_type_1)
                break
            }
            if (sorts[0][i].sort_type_1 == options1[j]) {
                break
            }
        }
    }
}
const clear = (step) => {
    if (step == 1) {
        options2_value.value = ""
        options3_value.value = ""
        options4_value.value = ""
        options5_value.value = ""
        options6_value.value = ""
    }
    if (step == 2) {
        options3_value.value = ""
        options4_value.value = ""
        options5_value.value = ""
        options6_value.value = ""
    }
    if (step == 3) {
        options4_value.value = ""
        options5_value.value = ""
        options6_value.value = ""
    }
    if (step == 4) {
        options5_value.value = ""
        options6_value.value = ""
    }
    if (step == 5) {
        options6_value.value = ""
    }
    if (step == 6) {
        options6_value.value = ""
    }
}
//         // 查询其他级分类列表  选择了值后
const getSort = (value, step) => {
    if (step == 1) {
        options2_value.value = ""
        options3_value.value = ""
        options4_value.value = ""
        options5_value.value = ""
        options6_value.value = ""

        for (let i = 0; i < sorts[0].length; i++) {
            if (sorts[0][i].sort_type_1 == value) {
                if (options2.length == 0) {
                    options2.push(sorts[0][i].sort_type_2)
                } else {
                    for (let j = 0; j <= options2.length; j++) {
                        if (sorts[0][i].sort_type_2 != options2[j] && j == options2.length - 1) {
                            options2.push(sorts[0][i].sort_type_2)
                            break
                        }
                        if (sorts[0][i].sort_type_2 == options2[j]) {
                            break
                        }
                    }
                }
            }
        }
    }
    if (step == 2) {
        for (let i = 0; i < sorts[0].length; i++) {
            if (sorts[0][i].sort_type_2 == value) {
                if (options3.length == 0) {
                    options3.push(sorts[0][i].sort_type_3)
                } else {
                    for (let j = 0; j <= options3.length; j++) {
                        if (sorts[0][i].sort_type_3!= options3[j] && j == options3.length - 1) {
                            options3.push(sorts[0][i].sort_type_3)
                            break
                        }
                        if (sorts[0][i].sort_type_3 == options3[j]) {
                            break
                        }
                    }
                }
            }
        }
        options3_value.value = ""
        options4_value.value = ""
        options5_value.value = ""
        options6_value.value = ""
    }
    if (step == 3) {
        for (let i = 0; i < sorts[0].length; i++) {
            if (sorts[0][i].sort_type_3== value) {
                if (options4.length == 0) {
                    options4.push(sorts[0][i].sort_type_4)
                } else {
                    for (let j = 0; j <= options4.length; j++) {
                        if (sorts[0][i].sort_type_4!= options3[j] && j == options4.length - 1) {
                            options4.push(sorts[0][i].sort_type_4)
                            break
                        }
                        if (sorts[0][i].sort_type_4 == options4[j]) {
                            break
                        }
                    }
                }
            }
        }
        options4_value.value = ""
        options5_value.value = ""
        options6_value.value = ""
    }
    if (step == 4) {
        for (let i = 0; i < sorts[0].length; i++) {
            if (sorts[0][i].sort_type_4== value) {
                if (options5.length == 0) {
                    options5.push(sorts[0][i].sort_type_5)
                } else {
                    for (let j = 0; j <= options5.length; j++) {
                        if (sorts[0][i].sort_type_5!= options3[j] && j == options5.length - 1) {
                            options5.push(sorts[0][i].sort_type_5)
                            break
                        }
                        if (sorts[0][i].sort_type_5 == options5[j]) {
                            break
                        }
                    }
                }
            }
        }
        options5_value.value = ""
        options6_value.value = ""
    }
    if (step == 5) {
        for (let i = 0; i < sorts[0].length; i++) {
            if (sorts[0][i].sort_type_5== value) {
                if (options6.length == 0) {
                    options6.push(sorts[0][i].sort_type_6)
                } else {
                    for (let j = 0; j <= options6.length; j++) {
                        if (sorts[0][i].sort_type_6!= options3[j] && j == options6.length - 1) {
                            options6.push(sorts[0][i].sort_type_6)
                            break
                        }
                        if (sorts[0][i].sort_type_6 == options6[j]) {
                            break
                        }
                    }
                }
            }
        }
        options6_value.value = ""
    }
    if (step == 6) {
        for (let i = 0; i < sorts[0].length; i++) {
            if (sorts[0][i].sort_type_6== value) {
                store.commit("getSortIdListLast",sorts[0][i].sort_id)
            }
        }
    }
}


</script>
<style>
#knowledge_sort .el-select {
    width: 170px;
}
</style>