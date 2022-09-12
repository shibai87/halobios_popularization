<template>
    <div id="user_comment">
        <van-nav-bar :title="this.getUserType" left-arrow fixed @click-left="pageBack" />
        <van-pull-refresh v-model="isLoading" @refresh="onRefresh">
            <div class="comment_other" v-if="commentList.length">
                <div v-for="(item,index) in commentList" :key="index">
                    <van-row>
                        <van-col span="13" offset="2" class="comment_other_content">
                           留言：{{item.comment_content}}
                        </van-col>
                        <van-col span="5" offset="2" class="comment_other_operation">
                            <van-button size="small" type="primary" @click="user_operation_detail(item.operation_source_type,item.operation_source_id)">查看详情</van-button>
                        </van-col>
                    </van-row>
                </div>
            </div>
            <div v-else>
                <van-empty image="error" description="未发现有人评论" />
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
                tilte:"",
                commentList: [],
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
                this.axios.get("http://192.168.1.103:8083/operation/getUserCommentList?operation_user_id="+this.getUserId)
                    .then((res) => {
                        console.log(res.data)
                        this.commentList = res.data
                    })
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
          margin-top: 80px;
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