<template>
    <div id="comment">
        <div class="comment_title">
            <h1>评论</h1>
        </div>
        <div class="comment_content">
            <van-field v-model="my_message" rows="1" center clearable autosize label="我的留言" type="textarea"
                placeholder="请输入留言"><template #button>
                    <van-button size="small" type="primary" @click="addComment">提交</van-button>
                </template></van-field>
        </div>
    </div>
</template>

<script>
  import {
    Dialog
  } from 'vant';
    export default {
        name: 'comment',
        data() {
            return {
                my_message: '',
                operationId:"",
            }
        },
        methods: {
             addOpration(){
                 this.axios.get("http://192.168.1.103:8083/operation/addOperation?operation_user_id="+this.getUserId+"&operation_source_type="+0+
                                                        "&operation_source_id="+this.getKnowledge+"&operation_type="+2)
                            .then((res)=>{
                                console(res)
                                if(res.data.mes=="添加成功"){
                                     Dialog.alert({
                title: '登录成功',
                message: '恭喜你，登录成功!',
              }).then(() => {
              
              });
                                }else{
                                     Dialog.alert({
                title: '登录失败',
                message: '登录失败,用户名或密码错误!',
              }).then(() => {
                // on close
              });
                                }
                            })
            },
            getOperation(){
                this.axios.get("http://192.168.1.103:8083/operation/getOperationId?operation_user_id="+this.getUserId+"&operation_source_type="+0+
                                                "&operation_source_id="+this.getKnowledge+"&operation_type="+2)
                         .then((res)=>{
                                if(res.data.mes=="添加成功"){
                                     Dialog.alert({
                title: '登录成功',
                message: '恭喜你，登录成功!',
              }).then(() => {
                  this.operationId=res.data.data
              });
                                }else{
                                     Dialog.alert({
                title: '登录失败',
                message: '登录失败,用户名或密码错误!',
              }).then(() => {
                // on close
              });
                                }
                         })
            },
            addComment(){
                this.addOpration()
                let id=this.getOperation()
                this.axios.get("http://192.168.1.103:8083/operation/addComment?operation_id="+id+"&comment_content="+this.my_message)
            },
           
        },
        mounted() {

        },
          computed: {
      getUserId() {
        return JSON.parse(localStorage.getItem('user_id'));
      },
       getKnowledge() {
                return JSON.parse(localStorage.getItem('knowledge_id'));
            },
    },
    }
</script>

<style scoped>

</style>