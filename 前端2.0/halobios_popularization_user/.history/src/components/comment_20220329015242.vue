<template>
    <div id="comment">
        <div class="comment_title">
            <h1>评论</h1>
        </div>
        <div class="comment_content">
            <van-field v-model="my_message" rows="1" center clearable autosize label="我的留言" type="textarea"
                placeholder="请输入留言">
                <template #button>
                    <div v-if="judge_comment">
                        <van-button size="small" type="primary">删除</van-button>
                    </div>
                    <div v-else>
                        <van-button size="small" type="primary" @click="addComment">提交</van-button>
                    </div>
                </template>
                </van-field>
        </div>
    </div>
</template>

<script>
export default {
  name: '',
  data () {
    return {
      msg: ''
    }
  },
  methods: {

  },
  mounted () {

  }
}
</script>

<style scoped>
    .comment_title h1 {
        font-size: 30px;
    }
</style>