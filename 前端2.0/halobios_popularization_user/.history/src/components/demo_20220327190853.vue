<template>
  <div>
<li>
    <!-- <div class="item" v-for="(item,index) in hallImg" :key="index">
        <img :src="item" alt="">
    </div> -->
    <van-uploader :after-read="onRead2" class="upimg">
        <van-icon name="plus" />
    </van-uploader>
</li>
  </div>
</template>

<script>
export default {
  name: '',
  data () {
    return {
    //   hallImg:[],
    }
  },
  methods: {
       onRead2(file) {
        let content = file.file;
        let data = new FormData();
        data.append('img',content);
        console.log(file.file,typeof data,data)
         this.axios
          .get("http://192.168.1.103:8083/user/updataImg?user_tel=" + this.user_tel + "&user_pwd=" + this.user_pwd)
          .then((res) => {
              console.log(res)
        // this.axios.post('图片上传地址',data)
        // .then((res) => {
        //     let datas = res.data.datas.path;
        //     this.msg.hallImg.push(`api地址${datas}`);
        })
    },

  },
  mounted () {

  }
}
</script>

<style scoped>

</style>

