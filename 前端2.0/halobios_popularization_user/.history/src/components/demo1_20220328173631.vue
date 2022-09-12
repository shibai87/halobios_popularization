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
    export default {
        name: 'comment',
        data() {
            return {
                my_message: ''
            }
        },
        methods: {
            addComment(){
                this.axios.get("http://192.168.1.103:8083/operation/addOperation?operation_user_id="+this.getUserId+"&operation_source_type="+0+
                                                        "&operation_source_id="+this.getKnowledge+"&operation_type="+2)
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