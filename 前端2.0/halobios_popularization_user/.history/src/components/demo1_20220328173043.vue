<template>
  <div id="comment">
      <div class="comment_title"><h1>评论</h1></div>
      <div class="comment_content">
          <van-field v-model="my_message" rows="1"  center
  clearable autosize label="我的留言" type="textarea" placeholder="请输入留言" ><template #button>
    <van-button size="small" type="primary">发送验证码</van-button>
  </template></van-field>
      </div>
  </div>
</template>

<script>
export default {
  name: 'comment',
  data () {
    return {
      my_message: ''
    }
  },
  methods: {

  },
  mounted () {

  }
}
</script>

<style scoped>

</style>
