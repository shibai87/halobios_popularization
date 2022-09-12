<template>
  <div id="knowledge">
    <div v-if="!(msg=='查询列表为空')" class="knowledge_list">
      <!-- 分类 -->
      <!-- <div class="knowledge_list_top">
        <van-notice-bar left-icon="info-o" text="请通过点击弹出框的确定按钮进行每一级分类的选择！选择完所有分类选项才能进行分类功能！" />
        <Sort></Sort>
        <van-button type="primary" class="knowledge_query" size="small" @click="Knowledge">开始查询</van-button>
        <van-button type="warning" class="knowledge_query" size="small" @click="clear">重新选择分类</van-button>
        <van-divider :style="{ color: '#1989fa', borderColor: '#1989fa'}">以下为科普知识列表</van-divider>
      </div> -->

      <!-- 分类知识详情 -->
      <van-pull-refresh v-model="isLoading" @refresh="onRefresh">
        <!-- <div class="knowledge_list_main" v-if="knowledgeList.length">
          <div v-for="(item,index) in knowledgeList" :key="index" class="knowledge_list_details">
            <van-row>
              <van-col span="4">
                <div class="knowledge_img"><img style="width: 70px; height: 80px;" :src="Image(item)" /></div>
              </van-col>
              <van-col span="10" offset="2">
                <div class="knowledge_content">
                  <span class="knowledge_title">{{item.knowledge_title}}</span>
                  <div class="knowledge_info">{{item.knowledge_content}}</div>
                </div>
              </van-col>
              <van-col span="4" offset="3">
                <div class="knowledge_operation">
                  <van-button type="primary" @click="knowledge(item.knowledge_id)">查看</van-button>
                </div>
              </van-col>
            </van-row>
          </div>
        </div> -->
        <!-- <div v-else class="knowledge_list_main_empty">
          <van-empty image="error" description="未发现分类下的科普知识!" />
        </div> -->
      </van-pull-refresh>
    </div>
    <div v-else class="knowledge_list_empty">
       <van-pull-refresh v-model="isLoading" @refresh="onRefresh">
      <van-empty image="error" description="未发现任何科普知识!" />
      </van-pull-refresh>
    </div>
  </div>
</template>

<script>
  import {
    Toast
  } from 'vant';
  // import {
  //   Dialog
  // } from 'vant';
  // import Sort from "../../components/sort.vue"
  import KnowledgeService from "../../api/KnowledgeService.js"
  import Constant from "../../assets/constant.js"
  export default {
    name: 'knowledge',
    // components: {
    //   Sort
    // },
    data() {
      return {
        isLoading: false,
        knowledgeList: [1],
        msg: ""
      }
    },
    methods: {
      // 刷新
      onRefresh() {
        setTimeout(() => {
          Toast('刷新成功');
          this.isLoading = false;
        this. getLoadKnowledgeList()
        }, 1000);
      },

      // 获取知识列表+查询列表  有接口
      getLoadKnowledgeList() {
        KnowledgeService.get_knowledge_list(1).then((res) => {
          console.log(res)
          this.knowledgeList = res.data,
            this.msg = res.msg
        })
      },

      // 查询分类下的知识列表
      // Knowledge() {
      //     // console.log("this.getSortLastId")
      //     // console.log(this.getSortLastId)
      //   if (this.getSortLastId){
      //     console.log(this.getSortLastId)
      //     KnowledgeService.get_knowledge_list(1, this.getSortLastId).then((res) => {
      //       console.log(res)
      //       if (res.data.length) {
      //         this.knowledgeList = res.data
      //         Dialog.alert({
      //           message: '查询成功'
      //         })
      //       } else {
      //         this.knowledgeList = res.data
      //         Dialog.alert({
      //           message: '该分类下，无科普知识，请重新选择分类！'
      //         })
      //       }
      //     })
      //   }  else {
      //     Dialog.alert({
      //       message: '请确认已选完所有分类！\n（从第零级至第六级）',
      //     })
      //   }
      // },

      // 重新选择（刷新）
      clear() {
        this.$store.commit("getSortIdLast", null)
        this.$store.commit("judgeNewSort", true);
        this.getLoadKnowledgeList()
      },

      // 查看科普知识详情
      knowledge(knowledge_id) {
        sessionStorage.setItem('operation_source_type', 0)
        // 用户从个人中心跳转至知识详情时需要
        sessionStorage.setItem('source_id', knowledge_id)
        this.$router.push({
          path: '/knowledge_details',
          query: {
            knowledge_id: knowledge_id
          }
        })
      },

      //图片路径
      Image(row) {
        var url = Constant.URL + "/knowledge/" + row.knowledge_img
        return url
      },
    },
    computed: {
      getSortLastId() {
        return this.$store.state.sort_id_last
      },
    },

    mounted() {
      this.getLoadKnowledgeList();
    }
  }
</script>

<style>
  /*  */
  #knowledge .van-notice-bar {
    color: #1989fa;
    background-color: #ecf9ff;
    font-size: 12px;
    margin: 15px 0 10px;

  }

  #knowledge .van-notice-bar .van-icon::before {
    font-size: 20px;
  }

  /*详情  */
  .knowledge_list_other_all .van-col {
    padding: 10px;
  }

  .knowledge_query .van-button {
    display: inline-block;
    height: 30px;
    font-size: 20px;
    vertical-align: middle;
  }

  #knowledge .van-divider {
    margin: 5px 0;
  }
  
  /* 无科普知识情况 */
  .knowledge_list_empty .van-empty__image {
    width: 300px;
    height: 300px;
    margin-left: 50px;
    margin-top: 100px;
  }
</style>

<style scoped>
#knowledge{
  height: 700px;
}
  /* 分类 */
  .knowledge_list_top {
    background-color: #fff;
    z-index: 1;
    position: fixed;
    top: 160px;
    width: 400px;
  }

  .knowledge_query {
    display: inline-block;
    width: 196px;
  }

  /* 知识列表 */
  .knowledge_list_main {
    margin-top: 305px;
    height: 488px;
  }

  .knowledge_list_details {
    border: 1px solid rgb(102, 102, 102);
    border-radius: 6px;
    width: 385px;
    margin: 5px;
  }

  .knowledge_img img {
    margin: 10px;
  }

  .knowledge_title {
    height: 50px;
    font-size: 20px;
  }

  .knowledge_info {
    width: 200px;
    font-size: 15px;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 2;
    overflow: hidden;
  }

  .knowledge_operation {
    height: 100px;
    line-height: 100px;
  }
  .knowledge_list_main_empty{
     margin-top: 260px;
    margin-left: 60px;
  }
</style>