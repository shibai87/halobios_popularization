<template>
  <div id="index">
    <el-container style="height: 720px; border: 1px solid #eee">
      <el-aside width="320px" style="background-color: rgb(238, 241, 246)">
        <el-menu :default-openeds="['1', '2', '3', '4']">
          <el-sub-menu index="1">
            <template #title>科普知识管理模块</template>
            <el-menu-item-group>
              <el-menu-item index="1-1" @click="tabSelect(1)">查看科普知识列表（默认首页）</el-menu-item>
              <el-menu-item index="1-2" @click="tabSelect(2)">新增科普知识</el-menu-item>
              <el-menu-item index="1-3" @click="tabSelect(3)">未上传的科普知识列表</el-menu-item>
            </el-menu-item-group>
          </el-sub-menu>
          <el-sub-menu index="2">
            <template #title>保护海洋生物主题活动管理模块</template>
            <el-menu-item-group>
              <el-menu-item index="2-1" @click="tabSelect(4)">查看活动列表</el-menu-item>
              <el-menu-item index="2-2" @click="tabSelect(5)">新增活动</el-menu-item>
              <el-menu-item index="2-3" @click="tabSelect(6)">未发布的活动列表</el-menu-item>
            </el-menu-item-group>
          </el-sub-menu>
          <el-sub-menu index="3">
            <template #title>科普小课堂管理模块</template>
            <el-menu-item-group>
              <el-menu-item index="3-1" @click="tabSelect(7)">查看小课堂列表</el-menu-item>
              <el-menu-item index="3-2" @click="tabSelect(8)">新增小课堂</el-menu-item>
              <el-menu-item index="3-3" @click="tabSelect(9)">未发布的小课堂列表</el-menu-item>
            </el-menu-item-group>
          </el-sub-menu>
          <el-sub-menu index="4">
            <template #title>用户管理模块</template>
            <el-menu-item-group>
              <el-menu-item index="4-1" @click="tabSelect(10)">审核举报</el-menu-item>
              <el-menu-item index="4-2" @click="tabSelect(11)">管理用户</el-menu-item>
            </el-menu-item-group>
          </el-sub-menu>
        </el-menu>
      </el-aside>
      <el-container>
        <el-header>
          <el-row>
            <el-col :span="1" @click="refresh"><img src="../assets/icon/刷新.png" alt=""
                class="index_header_icon index_refresh">
            </el-col>
            <el-col :span="6" :offset="7">
              <h1 class="index_title">海洋生物科普系统管理平台</h1>
            </el-col>
            <el-col :span="1" :offset="9">
              <router-link to="/">
                <img src="../assets/icon/logout.png" alt="" class="index_header_icon index_icon_logout">
              </router-link>
            </el-col>
          </el-row>
        </el-header>
        <el-main>
          <component :is="interfaceView"></component>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script lang="ts" setup>
import { ref, reactive, inject, markRaw } from 'vue'
import Knowledge from "../views/knowledge/knowledge.vue"
import AddKnowledge from "../views/knowledge/addknowledge.vue"
import Editknowledge from "../views/knowledge/editknowledge.vue"
import Activity from "../views/activity/activity.vue"
import AddActivity from "../views/activity/addactivity.vue"
import EditActivity from "../views/activity/editactivity.vue"
import Class from "../views/class/class.vue"
import AddClass from "../views/class/addclass.vue"
import EditClass from "../views/class/editclass.vue"
import ReportList from "../views/user/reportList.vue"
import UserManage from "../views/user/user_management.vue"
//刷新
const refresh = inject("reload")
//主页面跳转
type Tab = {
  name: string,
  comName: any
}

// 从复杂数据类型中取出自己想要的几个属性
type Com = Pick<Tab, 'comName'>

const tabArr = reactive<Tab[]>([
  {
    name: 'Knowledge',
    comName: markRaw(Knowledge)
  },
  {
    name: 'AddKnowledge',
    comName: markRaw(AddKnowledge)
  },
  {
    name: 'Editknowledge',
    comName: markRaw(Editknowledge)
  },
  {
    name: 'Activity',
    comName: markRaw(Activity)
  },
  {
    name: 'AddActivity',
    comName: markRaw(AddActivity)
  },
  {
    name: 'EditActivity',
    comName: markRaw(EditActivity)
  },
  {
    name: 'Class',
    comName: markRaw(Class)
  },
  {
    name: 'AddClass',
    comName: markRaw(AddClass)
  },
  {
    name: 'EditClass',
    comName: markRaw(EditClass)
  },
  {
    name: 'ReportList',
    comName: markRaw(ReportList)
  },
  {
    name: 'UserManage',
    comName: markRaw(UserManage)
  },
])

const interfaceView = ref<Com>(tabArr[0].comName)
const tabSelect = (i: number) => {
  switch (i) {
    case 1: {
      // 查看科普分类知识
      interfaceView.value = tabArr[0].comName;
      // sessionStorage.setItem('search_type', 0)
      break;
    }
    case 2: {
      // 新增科普分类知识
      interfaceView.value = tabArr[1].comName;
      // this.$store.commit("getSortIdListLast",)
      break;
    }
    case 3: {
      //  编辑科普分类知识
      interfaceView.value = tabArr[2].comName;
      // this.$store.commit("getSortIdListLast",)
      break;
    }
    case 4: {
      // 查看科普分类知识
      interfaceView.value = tabArr[3].comName;
      // sessionStorage.setItem('search_type', 0)
      break;
    }
    case 5: {
      // 新增科普分类知识
      interfaceView.value = tabArr[4].comName;
      // this.$store.commit("getSortIdListLast",)
      break;
    }
    case 6: {
      //  编辑科普分类知识
      interfaceView.value = tabArr[5].comName;
      // this.$store.commit("getSortIdListLast",)
      break;
    }
    case 7: {
      // 查看科普分类知识
      interfaceView.value = tabArr[6].comName;
      // sessionStorage.setItem('search_type', 0)
      break;
    }
    case 8: {
      // 新增科普分类知识
      interfaceView.value = tabArr[7].comName;
      // this.$store.commit("getSortIdListLast",)
      break;
    }
    case 9: {
      //  编辑科普分类知识
      interfaceView.value = tabArr[8].comName;
      // this.$store.commit("getSortIdListLast",)
      break;
    }
    case 10: {
      // 新增科普分类知识
      interfaceView.value = tabArr[9].comName;
      // this.$store.commit("getSortIdListLast",)
      break;
    }
    case 11: {
      //  编辑科普分类知识
      interfaceView.value = tabArr[10].comName;
      // this.$store.commit("getSortIdListLast",)
      break;
    }
    default:
      break;
  }
}
</script>

<style>
/* 侧边 */
#index .el-aside {
  background-color: #D3DCE6;
  color: #333;
  line-height: 800px;
}

#index .el-sub-menu__title {
  text-align: center;
  font-size: 20px;
}

#index .el-sub-menu .el-menu-item {
  font-size: 17px;
  padding-left: 0;
  text-align: center;
}

#index .el-menu-item-group {
  background-color: #F2F6FC;
}

/* 头部 */
#index .el-header {
  background-color: #409EFF;
  color: #333;
  font-size: 25px;
  padding: 10px 10px;
}


/* 主体 */
#index .el-main {
  background-color: #E9EEF3;
}
</style>

<style scoped>
.index_title {
  display: inline-block;
  width: 450px;
  margin: 0;
}

/* 头部图标设置 */
.index_header_icon {
  width: 30px;
  height: 30px;
  padding: 5px;
}

@keyframes rotate {
  from {
    transform: rotate(0deg)
  }

  to {
    transform: rotate(360deg)
  }
}

.index_refresh:hover {
  animation: rotate 2s;

}
</style>