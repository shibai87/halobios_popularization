<template>
  <div id="search">
    <van-row>
      <!--搜索类型 -->
      <van-col span="8" offset="1">
        <van-dropdown-menu>
          <van-dropdown-item v-model="options1_value" :options="options1" @click="change" @open="open" />
        </van-dropdown-menu>
      </van-col>
      <!-- 搜索标题 -->
      <van-col span="8">
        <van-button @click="showPopup" size="medium">
          <div v-if="this.search_value">{{this.search_value}}</div>
          <div v-else>请选择搜索的标题</div>
        </van-button>
        <van-popup v-model="show" position="bottom" :style="{ height: '30%' }">
          <van-picker title="请选择搜索的标题" show-toolbar :columns="columns" @confirm="onConfirm" @cancel="onCancel" />
        </van-popup>
      </van-col>
      <!-- 搜索按钮 -->
      <van-col span="5" offset="1">
        <van-button type="primary" icon="el-icon-search" @click="getsearch" size="small">搜索</van-button>
      </van-col>
    </van-row>
  </div>
</template>

<script>
  import {
    Toast
  } from 'vant';
  import {
    Dialog
  } from 'vant';
  export default {
    name: 'search',
    data() {
      return {
        isLoading: false,
        show: false,
        columns: [],
        infos: [],
        options1_value: 0,
        options1: [{
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
          },
        ],
        search_value_id: "",
        search_value: "",
      }
    },
    methods: {
      // 功能：搜索类型
        // 功能： 弹出下弹框
        showPopup() {
          console.log(this.options1_value)
          if (this.options1_value == 0) {
            this.axios
              .get("http://192.168.1.105:8083/knowledge/search")
              .then((res) => {

                this.infos = res.data
                for (let i = 0; i < res.data.length; i++) {
                  this.columns.push(res.data[i].knowledge_title)
                }
                //  console.log(this.infos)
              });
          } else if (this.options1_value == 1) {
            this.axios
              .get("http://192.168.1.105:8083/activity/search")
              .then((res) => {
                this.infos = res.data
                for (let i = 0; i < res.data.length; i++) {
                  this.columns.push(res.data[i].activity_title)
                }
              });
          } else {
            this.axios
              .get("http://192.168.1.105:8083/video/search")
              .then((res) => {
                this.infos = res.data
                for (let i = 0; i < res.data.length; i++) {
                  this.columns.push(res.data[i].video_title)
                }
              });
          }
          this.show = true;

        },
        // 功能： 取消弹框
        onCancel() {
          Toast('取消');
          this.show = false
        },
        // 功能：重新选择
        open() {
          this.search_value = null
        },
      // 功能：搜索标题
        //功能：确认弹框  存id
        onConfirm(value) {
          this.search_value = value
          Toast(`当前选择搜索的标题名为：${value},请点击搜索，进行搜索！`);
          for (let i = 0; i < this.infos.length; i++) {
            if (this.options1_value == 0 && this.infos[i].knowledge_title == value) {
              this.search_value_id = this.infos[i].knowledge_id
            }
            if (this.options1_value == 1 && this.infos[i].activity_title == value) {
              this.search_value_id = this.infos[i].activity_id
            }
            if (this.options1_value == 2 && this.infos[i].video_title == value) {
              this.search_value_id = this.infos[i].video_id
            }
          }
          this.show = false
        },

      //功能： 开始搜索
      getsearch() {
        if (this.search_value_id) {
          if (this.options1_value == 0) {
            sessionStorage.setItem('operation_source_type', 0)
            // 用户从个人中心跳转至知识详情时需要
            sessionStorage.setItem('source_id', this.search_value_id)
            this.$router.push({
              path: '/knowledge_details',
              query: {
                knowledge_id: this.search_value_id
              }
            })
          } else if (this.options1_value == 1) {
            sessionStorage.setItem('operation_source_type', 1)
            sessionStorage.setItem('source_id', this.search_value_id);
            this.$router.push("/activity_details");
          } else {
            sessionStorage.setItem('operation_source_type', 2)
            sessionStorage.setItem('source_id', this.search_value_id);
            this.$router.push({
              path: '/classroom_details',
              query: {
                classroom_id: this.search_value_id
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
    },
  }
</script>

<style>
  #search {
    top: 75px;
    width: 100%;
    position: fixed;
    z-index: 0;
  }
  #search .van-dropdown-menu__bar{
    height: 44px;
  }
</style>
<style scoped>

</style>