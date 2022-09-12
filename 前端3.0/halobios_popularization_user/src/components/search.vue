<template>
  <div id="search">
    <van-row>
      <!--搜索类型 -->
      <van-col span="8" offset="1">
        <van-dropdown-menu>
          <van-dropdown-item v-model="options1_value" :options="options1" @open="open" />
        </van-dropdown-menu>
      </van-col>
      <!-- 搜索标题 -->
      <van-col span="10">
        <div @click="showPopup" size="medium" class="search_key_title">
          <div v-if="search_value">{{search_value}}</div>
          <div v-else>请选择搜索的标题</div>
        </div>
        <van-popup v-model:show="show" position="bottom" :style="{ height: '30%' }">
          <van-picker title="请选择搜索的标题" show-toolbar :columns="columns" @confirm="onConfirm" @cancel="onCancel" />
        </van-popup>
      </van-col>
      <!-- 搜索按钮 -->
      <van-col span="4" offset=".5" class="search_button">
        <van-button type="primary" icon="el-icon-search" @click="getsearch" size="small" class="search_button_begin">搜索
        </van-button>
      </van-col>
    </van-row>
  </div>
</template>

<script lang="ts" setup>
import { ref, reactive } from "vue"
import { onMounted } from "vue";
import { useStore } from "vuex"
import { useRouter } from 'vue-router'
import { Toast } from 'vant';
import { Dialog } from 'vant';
// api
import KnowledgeService from "../api/KnowledgeService.js"
import ActivityService from "../api/ActivityService.js"
import VideoService from "../api/VideoService.js"
const store = useStore()
const router = useRouter();
const isLoading = ref<boolean>(false)
const show = ref<boolean>(false)
const columns = reactive<any>([])
// 存储标题及相应id信息列表
const infos = ref<any>([])
// 选择搜索模块---默认搜索科普知识模块
const options1_value = ref<number>(0)
const options1 = ref<any>([
  {
    text: '科普知识',
    value: 0
  },
  {
    text: '科普保护活动',
    value: 1
  },
  {
    text: '科普小课堂',
    value: 2
  }
])
// 确定搜索标题
const search_value_id = ref<any>("")
const search_value = ref<any>("")
// 功能：重新选择搜索模块
const open = () => { search_value.value = null }
// 功能：弹出下弹框---搜索内容
const showPopup = () => {
  columns.splice(0, columns.length)
  // console.log("用户选择的类型值："+options1_value.value)
  if (options1_value.value == 0) {
    KnowledgeService.search()
      .then((res) => {
        infos.value = res
        if (res.length == 0) {
          Dialog.alert({
            title: '查询科普知识模块',
            message: '该模块无资源！请选择其他模块！',
          })
        } else {
          show.value = true;
          for (let i = 0; i < res.length; i++) {
            columns.push(res[i].knowledge_title)
          }
        }
      });
  } else if (options1_value.value == 1) {
    ActivityService.search()
      .then((res) => {
        infos.value = res
        if (res.length == 0) {
          Dialog.alert({
            title: '查询科普保护活动模块',
            message: '该模块无资源！请选择其他模块！',
          })
        } else {
          show.value = true;
          for (let i = 0; i < res.length; i++) {
            columns.value[i] = res[i].activity_title
          }
        }
      });
  } else {
    VideoService.search()
      .then((res) => {
        infos.value = res
        if (res.length == 0) {
          Dialog.alert({
            title: '查询科普小课堂模块',
            message: '该模块无资源！请选择其他模块！',
          })
        } else {
          show.value = true;
          for (let i = 0; i < res.length; i++) {
            columns.value[i] = res[i].video_title
          }
        }
      });
  }
  // console.log("可选标题列表")
  // console.log(columns)
}
// 功能： 取消弹框
const onCancel = () => {
  Toast('取消');
  show.value = false
}
// 功能：确认弹框--搜索标题==存id
const onConfirm = (value: any) => {
  search_value.value = value
  Toast(`当前选择搜索的标题名为：${value},请点击搜索，进行搜索！`);
  for (let i = 0; i < infos.value.length; i++) {
    if (options1_value.value == 0 && infos.value[i].knowledge_title == value) {
      search_value_id.value = infos.value[i].knowledge_id
    }
    if (options1_value.value == 1 && infos.value[i].activity_title == value) {
      search_value_id.value = infos.value[i].activity_id
    }
    if (options1_value.value == 2 && infos.value[i].video_title == value) {
      search_value_id.value = infos.value[i].video_id
    }
  }
  show.value = false
}
//功能： 开始搜索
const getsearch = () => {
  if (search_value_id.value) {
    if (options1_value.value == 0) {
      sessionStorage.setItem('operation_source_type', "0")
      // 用户从个人中心跳转至知识详情时需要
      sessionStorage.setItem('source_id', search_value_id.value)
      router.push({
        path: '/knowledge_details',
        query: {
          knowledge_id: search_value_id.value
        }
      })
    } else if (options1_value.value == 1) {
      sessionStorage.setItem('operation_source_type', "1")
      sessionStorage.setItem('source_id', search_value_id.value);
      router.push("/activity_details");
    } else {
      sessionStorage.setItem('operation_source_type', "2")
      sessionStorage.setItem('source_id', search_value_id.value);
      router.push({
        path: '/classroom_details',
        query: {
          classroom_id: search_value_id.value
        }
      })
    }
  } else {
    Dialog.alert({
      title: '搜索失败',
      message: '请确认已选择了所要搜索的标题!',
    })
  }
}
</script>

<style lang="less">
#search .van-row {
  height: 32px;
}

#search .van-dropdown-menu__bar {
  height: 32px;
}
</style>

<style scoped>
#search {
  top: 75px;
  width: 100%;
  position: fixed;
  z-index: -3;
}

.search_key_title {
  height: 32px;
  font-size: 15px;
  line-height: 32px;
  text-align: center;
}

.search_button_begin {
  height: 32px;
  display: block;
  font-size: 15px;
}
</style>