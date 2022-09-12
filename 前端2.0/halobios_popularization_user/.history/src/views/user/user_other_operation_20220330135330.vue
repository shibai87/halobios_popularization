<template>
    <div id="user_comment">
        <van-nav-bar :title="this.getUserType" left-arrow fixed @click-left="pageBack" />
        <van-pull-refresh v-model="isLoading" @refresh="onRefresh">
            <van-tabs v-model="active">
  <van-tab title="科普知识区"></van-tab>
  <van-tab title="科普活动区"></van-tab>
  <van-tab title="科普小课堂区"></van-tab>
</van-tabs>
            <div class="comment_other" v-if="List.length">
                <div v-for="(item,index) in List" :key="index">
                    <van-row>
                        <van-col span="13" offset="2" class="comment_other_content">
                            <div v-show="active==0"> 标题：{{item.knowledge_title}}</div>
                             <div v-show="active==1"> 标题：{{item.activity_title}}</div>
                             <div v-show="active==2"> 标题：{{item.video_title}}</div>
                        </van-col>
                        <van-col span="5" offset="2" class="comment_other_operation">
                            <van-button size="small" type="primary" @click="user_operation_detail(item.operation_source_type,item.operation_source_id)">查看详情</van-button>
                        </van-col>
                    </van-row>
                </div>
            </div>
            <div v-else>
                <van-empty image="error" description="无数据" />
            </div>
        </van-pull-refresh>

    </div>
</template>

<script>
    import {
        Toast
    } from 'vant';
    export default {
        name: '',
        inject: ["reload"],
        data() {
            return {
                // 刷新
                isLoading: false,
                active: 0,
                List: [],
            }
        },
        methods: {
            pageBack() {
                this.$router.push("/user")
            },
            // 刷新
            onRefresh() {
                setTimeout(() => {
                    Toast('刷新成功');
                    this.isLoading = false;
                    this.reload()
                }, 1000);
            },
            getloadOperation() {
               console.log(this.getUserType,typeof this.getUserType)
                console.log("用户id"+this.getUserId)
                if(this.active==0){
                       this.axios.get("http://192.168.1.103:8083/operation/getUserOperationByKnowledge?operation_user_id="+this.getUserId+"&operation_type="+0)
                    .then((res) => {
                        console.log(res.data,res.data.data)
                        this.List = res.data.data
                    })
                }else if(this.active==2){
                       this.axios.get("http://192.168.1.103:8083/operation/getUserOperationByActivity?operation_user_id="+this.getUserId+"&operation_type="+1)
                    .then((res) => {
                        console.log(res.data,res.data)
                        this.List = res.data.data
                    })
                }else{
                       this.axios.get("http://192.168.1.103:8083/operation/getUserOperationByVideo?operation_user_id="+this.getUserId+"&operation_type="+2)
                    .then((res) => {
                        console.log(res.data)
                        this.List = res.data.data
                    })
                }
            },
            user_operation_detail(val,operation_source_id){
             
                if(val==0){
                    // 科普知识
                    
        localStorage.setItem('knowledge_id',operation_source_id);
        this.$store.commit("getKnowledge",operation_source_id)
                    this.$router.push("/knowledge_details")

                }
                if(val==1){
                    // 科普活动
                     localStorage.setItem('knowledge_id',operation_source_id);
        this.$store.commit("getKnowledge",operation_source_id)
                    this.$router.push("/activity_details")
                }
                if(val==2){
                    // 科普小课堂
                     localStorage.setItem('knowledge_id',operation_source_id);
        this.$store.commit("getKnowledge",operation_source_id)
                    this.$router.push("/classroom_details")
                }

            }
        },
        mounted() {
            this.getloadOperation()
        },
        computed: {

            getJudgeLogin() {
                return JSON.parse(localStorage.getItem('judge_login'));
            },
            getUserId() {
                return this.$store.state.user_id;
            },
            getUserType(){
                return localStorage.getItem('user_type');
            }
        },
    }
</script>
<style>
 /* user_infos_head */
  #user_comment .van-nav-bar__content {
    height: 70px;

  }

  #user_comment .van-nav-bar__title {
    height: 30px;
    line-height: 30px;
    font-size: 25px;

  }
    #user_comment .van-icon:before {
    color: #000;
    font-size: 35px;
  }
/*  */
.van-tabs{
    top: 80px;
}
/*  */
    #user_comment .van-row {
        border: 1px solid rgb(102, 102, 102);
        border-radius: 6px;
      
        margin: 5px;
        padding: 5px;
    }
    #user_comment .van-button {
        height: 30px;
        width: 100px;
        font-size: 20px;
        vertical-align: middle;
    }
</style>
<style scoped>
    .comment_other{
          margin-top: 90px;
    }
    .comment_other_content {
       text-align: left;
        font-size: 20px;
        display: -webkit-box;
        -webkit-box-orient: vertical;
        -webkit-line-clamp: 2;
        overflow: scroll;
          vertical-align: middle;

    }

    .comment_other_operation {
        height: 80px;
        line-height: 80px;
    }
</style>