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
    // import {
    //     Dialog
    // } from 'vant';
    export default {
        name: 'comment',
        data() {
            return {
                my_message: '',
                operationId:null,
            }
        },
        methods: {
            addComment(){
             
                console.log(this.getUserId,this.getKnowledge)
                // this.axios.get("http://192.168.1.103:8083/operation/addOperation?operation_user_id=2&operation_source_type=2&operation_source_id=2&operation_type=2")
                this.axios.get("http://192.168.1.103:8083/operation/getOperationId?operation_user_id=2&operation_source_type=2&operation_source_id=2&operation_type=2")
                .then((res)=>{
                   this.operationId=res.data.data
                  this.a(this.operationId)

                 

                })
            },
            a(val){
                    console.log(val,typeof val)
            }
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