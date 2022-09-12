<template>
  <div id="sort">
    <div>请选择各级分类（从左至右）：</div>
    <van-row gutter="1">
      <van-col span="4">
        <van-button type="primary" hairline plain @click="showPopup(1,null)" size="small">第一级</van-button>
      </van-col>
      <!-- <span v-if="!store.state.judge_new_sort"> -->
          <van-col span="4">
            <van-button type="primary" hairline plain @click="showPopup(2,options1_value)" v-show="options1_value"
              size="small">第二级</van-button>
          </van-col>
          <van-col span="4">
            <van-button type="primary" hairline plain @click="showPopup(3,options2_value)" v-show="options2_value"
              size="small">第三级</van-button>
          </van-col>
          <van-col span="4">
            <van-button type="primary" hairline plain @click="showPopup(4,options3_value)" v-show="options3_value"
              size="small">第四级</van-button>
          </van-col>
          <van-col span="4">
            <van-button type="primary" hairline plain @click="showPopup(5,options4_value)" v-show="options4_value"
              size="small">第五级</van-button>
          </van-col>
          <van-col span="4">
            <van-button type="primary" hairline plain @click="showPopup(6,options5_value)" v-show="options5_value"
              size="small">第六级</van-button>
          </van-col>
        <!-- </span> -->
      <van-popup v-model:show="show" position="bottom" :style="{ height: '30%' }">
        <van-picker title="请选择分类值" show-toolbar :columns="columns" @confirm="onConfirm" @cancel="onCancel" />
      </van-popup>
    </van-row>
    <div class="sort_change">
      <div>您选择的分类为：</div>
      <van-row v-if="!store.state.judge_new_sort&&options1_value">
        <van-col span="12">{{options1_value}}</van-col>
        <van-col span="12">{{options2_value}}</van-col>
        <van-col span="6">{{options3_value}}</van-col>
        <van-col span="6">{{options4_value}}</van-col>
        <van-col span="6">{{options5_value}}</van-col>
        <van-col span="6">{{options6_value}}</van-col>
      </van-row>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { ref, reactive } from "vue"
import { onMounted } from "vue";
import { useStore } from "vuex"
import { useRouter } from 'vue-router'
import KnowledgeService from "../api/KnowledgeService"
import { Toast } from 'vant';
const store = useStore()
const router = useRouter();

const isLoading = ref<boolean>(false)
const show = ref<boolean>(false)
const columns = ref<any>({})
// 分类
// 第几级
const step = ref<number>()
//       // 存储所有分类
const sorts = reactive<any>([])
//       // 分级存储各级分类
const options1 = reactive<any>([])
const options1_value = ref<string>()
const options2 = reactive<any>([])
const options2_value = ref<string>()
const options3 = reactive<any>([])
const options3_value = ref<string>()
const options4 = reactive<any>([])
const options4_value = ref<string>()
const options5 = reactive<any>([])
const options5_value = ref<string>()
const options6 = reactive<any>([])
const options6_value = ref<string>()

onMounted(() => { getLoadSortList() })
// 获取分类列表 有接口
const getLoadSortList = () => {
  KnowledgeService.get_all_sort()
  .then((res) => {
    sorts.push(res)
    sort()
  });
}
// 查询分类第一级的分类列表
const sort = () => {
  options1.push(sorts[0][0].sort_type_1)
  for (let i = 1; i < sorts[0].length; i++) {
    for (let j = 0; j < options1.length; j++) {
      if (sorts[0][i].sort_type_1 != options1[j] && j == options1.length - 1) {
        options1.push(sorts[0][i].sort_type_1)
        break;
      }
      if (sorts[0][i].sort_type_1 == options1[j]) {
        break;
      }
    }
  }
}
// 弹出下弹框
const showPopup = (stepvalue:number, value:any) => {
  // console.log(value)
  step.value = stepvalue;
  if (stepvalue == 1) {
    columns.value=options1
    getSort(stepvalue, value)
  } else {
    // console.log(getSort(stepvalue, value))
    columns.value=getSort(stepvalue, value)[0]
  }
  show.value = true;
}
// 查询其他级的分类列表
const getSort = (step: number, value:any) => {
  if (step == 1) {
    options1_value.value = ""
    options2_value.value = ""
    options3_value.value = ""
    options4_value.value = ""
    options5_value.value = ""
    options6_value.value = ""
  }
  if (step == 2) {
    let options = [];
    for (let i = 0; i < sorts[0].length; i++) {
      if (sorts[0][i].sort_type_1 == value) {
        options.push(sorts[0][i].sort_type_2)
      }
    }
    // 使数据能显示
    options2.push(Array.from(new Set(options)))
    options2_value.value = ""
    options3_value.value = ""
    options4_value.value = ""
    options5_value.value = ""
    options6_value.value = ""
    return options2
  }
  if (step == 3) {
    let options = [];
    let options_id = [];
    for (let i = 0; i < sorts[0].length; i++) {
      if (sorts[0][i].sort_type_2 == value) {
        options.push(sorts[0][i].sort_type_3)
        options_id.push(sorts[0][i].sort_id)
      }
    }
    options3.push(Array.from(new Set(options)))
    options3_value.value = ""
    options4_value.value = ""
    options5_value.value = ""
    options6_value.value = ""
    return options3
  }
  if (step == 4) {
    let options = [];
    let options_id = [];
    for (let i = 0; i < sorts[0].length; i++) {
      if (sorts[0][i].sort_type_3 == value) {
        options.push(sorts[0][i].sort_type_4)
        options_id.push(sorts[0][i].sort_id)
      }
    }
    options4.push(Array.from(new Set(options)))
    options4_value.value = ""
    options5_value.value = ""
    options6_value.value = ""
    return options4
  }
  if (step == 5) {
    let options = [];
    let options_id = [];
    for (let i = 0; i < sorts[0].length; i++) {
      if (sorts[0][i].sort_type_4 == value) {
        options.push(sorts[0][i].sort_type_5)
        options_id.push(sorts[0][i].sort_id)
      }
    }
    options5.push(Array.from(new Set(options)))
    options5_value.value = ""
    options6_value.value = ""
    return options5
  }
  if (step == 6) {
    let options = [];
    let options_id = [];
    for (let i = 0; i < sorts[0].length; i++) {
      if (sorts[0][i].sort_type_5 == value) {
        options.push(sorts[0][i].sort_type_6)
        options_id.push(sorts[0][i].sort_id)
      }
    }
    options6.push(Array.from(new Set(options)))

    options6_value.value = ""
    return options6
  }
}

// 确认弹框  存id
const onConfirm = (value:string) => {
  store.commit("getSortIdLast",)
  if (step.value == 1) {
    options1_value.value = value
    store.commit("judgeNewSort", false);
  }
  if (step.value == 2) {
    options2_value.value = value
  }
  if (step.value == 3) {
    options3_value.value = value
  }
  if (step.value == 4) {
    options4_value.value = value
  }
  if (step.value == 5) {
    options5_value.value = value
  }
  if (step.value == 6) {
    options6_value.value = value
    let options_id_last;
    for (let i = 0; i < sorts[0].length; i++) {
      if (sorts[0][i].sort_type_6 == value) {
        options_id_last = sorts[0][i].sort_id

        break;
      }
    }
    store.commit("getSortIdLast", options_id_last)
  }

  if (options6_value.value) {
    Toast(`请点击开始查询！`);
  } else {
    Toast(`当前选择分类值为：${value},请继续选择！`);
  }
  show.value = false
}

// 取消弹框
const onCancel = () => {
  Toast('取消');
  show.value = false
}
</script>

<style >
#sort .van-row {
  margin: 10px;
  font-size: 15px;
}

#sort .van-col {
  margin: 5px 0;
}

.sort_change .van-col {
  overflow: scroll;
  white-space: nowrap;
  text-align: center;
}
</style>
