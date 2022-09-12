<template>
  <div id="sort">
      <van-row gutter="1">
        <div>请选择各级分类（从左至右）：</div>
        <van-col span="4">
          <van-button type="primary" hairline plain @click="showPopup(1,null)" size="small">第一级 </van-button>
        </van-col>
        <div v-if="!this.$store.state.judge_new_sort">
          <van-col span="4">
            <van-button type="primary" hairline plain @click="showPopup(2,options1_value)" v-show="options1_value"
              size="small">第二级 </van-button>
          </van-col>
          <van-col span="4">
            <van-button type="primary" hairline plain @click="showPopup(3,options2_value)" v-show="options2_value"
              size="small">第三级 </van-button>
          </van-col>
          <van-col span="4">
            <van-button type="primary" hairline plain @click="showPopup(4,options3_value)" v-show="options3_value"
              size="small">第四级 </van-button>
          </van-col>
          <van-col span="4">
            <van-button type="primary" hairline plain @click="showPopup(5,options4_value)" v-show="options4_value"
              size="small">第五级 </van-button>
          </van-col>
          <van-col span="4">
            <van-button type="primary" hairline plain @click="showPopup(6,options5_value)" v-show="options5_value"
              size="small">第六级 </van-button>
          </van-col>
        </div>
        <van-popup v-model="show" position="bottom" :style="{ height: '30%' }">
          <van-picker title="请选择分类值" show-toolbar :columns="columns" @confirm="onConfirm" @cancel="onCancel" />
        </van-popup>
      </van-row>
      <div class="sort_change">
        <van-row v-if="!this.$store.state.judge_new_sort&&options1_value">
          <div>您选择的分类为：</div>
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

<script>
  import {
    Toast
  } from 'vant';
  import KnowledgeService from "../api/KnowledgeService"
  export default {
    name: 'sort',
    data() {
      return {
        isLoading: false,

        show: false,

        columns: [],
        // 分类
        // 第几级
        step: "",
        // 存储所有分类
        sorts: [],
        // 分级存储各级分类
        options1: [],
        options1_value: '',
        options2: [],
        options2_value: '',
        options3: [],
        options3_value: '',
        options4: [],
        options4_value: '',
        options5: [],
        options5_value: '',
        options6: [],
        options6_value: '',
      }
    },
    methods: {
      // 获取分类列表 有接口
      getLoadSortList() {
        KnowledgeService.getSortListRequest()
          .then((res) => {
            // console.log(res)
            this.sorts.push(res)
            // console.log(this.sorts)
            this.sort()
          });
      },
      // 查询分类第一级的分类列表
      sort() {
        this.options1.push(this.sorts[0][0].sort_type_1)
        for (let i = 1; i < this.sorts[0].length; i++) {
          for (let j = 0; j < this.options1.length; j++) {
            if (this.sorts[0][i].sort_type_1 != this.options1[j] && j == this.options1.length - 1) {
              this.options1.push(this.sorts[0][i].sort_type_1)
              break;
            }
            if (this.sorts[0][i].sort_type_1 == this.options1[j]) {
              break;
            }
          }
        }
      },

      // 查询其他级的分类列表
      getSort(step, value) {
        if (step == 1) {
          this.options1_value = ""
          this.options2_value = ""
          this.options3_value = ""
          this.options4_value = ""
          this.options5_value = ""
          this.options6_value = ""
        }
        if (step == 2) {
          let options = [];
          for (let i = 0; i < this.sorts[0].length; i++) {
            if (this.sorts[0][i].sort_type_1 == value) {
              options.push(this.sorts[0][i].sort_type_2)
            }
          }
          // 使数据能显示
          this.options2 = Array.from(new Set(options))
          this.options2_value = ""
          this.options3_value = ""
          this.options4_value = ""
          this.options5_value = ""
          this.options6_value = ""
          return this.options2
        }
        if (step == 3) {
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
          return this.options3
        }
        if (step == 4) {
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
          return this.options4
        }
        if (step == 5) {
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
          return this.options5
        }
        if (step == 6) {
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
          return this.options6
        }
      },

      // 弹出下弹框
      showPopup(step, value) {
        this.step = step;
        if (step == 1) {
          this.columns = this.options1
          this.getSort(step, value)
        } else {
          this.columns = this.getSort(step, value)
        }
        this.show = true;
      },

      // 确认弹框  存id
      onConfirm(value) {
        if (this.step == 1) {
          this.options1_value = value
          this.$store.commit("judgeNewSort", false);
        }
        if (this.step == 2) {
          this.options2_value = value
        }
        if (this.step == 3) {
          this.options3_value = value
        }
        if (this.step == 4) {
          this.options4_value = value
        }
        if (this.step == 5) {
          this.options5_value = value
        }
        if (this.step == 6) {
          this.options6_value = value
          let options_id_last;
          for (let i = 0; i < this.sorts[0].length; i++) {
            if (this.sorts[0][i].sort_type_6 == value) {
              options_id_last = this.sorts[0][i].sort_id
              break;
            }
          }
          // console.log(options_id_last)
          
          this.$store.commit("getSortIdLast", options_id_last)
        }

        if (this.options6_value) {
          Toast(`请点击开始查询！`);
        } else {
          Toast(`当前选择分类值为：${value},请继续选择！`);
        }
        this.show = false
      },

      // 取消弹框
      onCancel() {
        Toast('取消');
        this.show = false
      },

      clear() {
        this.options1_value = ""
        this.options2_value = ""
        this.options3_value = ""
        this.options4_value = ""
        this.options5_value = ""
        this.options6_value = ""
      }
    },
    mounted() {
      // 分类功能
      this.getLoadSortList();
    }
  }
</script>

<style lang="less">
  #sort{
     .van-row {
    margin: 10px;
    font-size: 15px;
  }

  .van-col {
    margin: 5px 0;
  }
  .sort_change .van-col {
    overflow: scroll;
    white-space: nowrap;
    text-align: center;
  }
  }
</style>