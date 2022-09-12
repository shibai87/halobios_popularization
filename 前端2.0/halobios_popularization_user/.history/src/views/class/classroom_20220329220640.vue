<template>
  <div id="classroom">
    <!-- 分类知识详情 -->
    <div v-if="classroomList.length">
      <ul v-for="(item,index) in classroomList" :key="index" class="classroom_list">
        <li class="acitivity_item">
            <video controls :src="Video(item)" class="classroom_video"></video>
            <div class="classroom_title">
              <span>{{item.classroom_title}}</span>
            </div> 
            <div class="classroom_operation"> 
              <van-button type="primary" @click="edit_classroom(item.classroom_id)">查看</van-button>
            </div>
        </li>
      </ul>
    </div>
    <div v-else>
      <van-empty description="无内容！"></van-empty>
    </div>
  </div>
</template>

<script>
  export default {
    inject: ["reload"],
    name: "classroom",
    data() {
      return {
        classroomList: [],
      }
    },
    methods: {
      // 获取知识列表+查询列表
      getLoadclassroomList() {
        this.axios.get("http://localhost:8083/classroom/get_classroom_list?classroom_upload=" + 1)
          .then((res) => {
            // console.log(res.data,res.data.length)
            this.classroomList = res.data
            // console.log(this.classroomList.length)
          })

      },
      // 编辑
      edit_classroom(classroom_id) {
         localStorage.setItem('classroom_id', classroom_id);
        this.$store.commit("getclassroom", classroom_id)
        this.$router.push("/classroom_details");
      },
      //视频路径
      Video(row) {
        var url = "http://localhost:8083/classroom/" + row.classroom_file_name
        console.log(url)
        return url
      },
    },
    computed: {
    },

    mounted() {
      this.getLoadclassroomList();
    }
  }
</script>
<style>
  /*  */
  #classroom .van-alert {
    margin-bottom: 10px;
  }



  #classroom .van-button {
    display: block;
    width: 150px;
    margin: 10px auto;


  }


</style>
<style scoped>
  ul,
  li {
    list-style: none;
  }
.classroom_list{
 display: inline-flex;
 flex-wrap:wrap;
}
.acitivity_item{
  width: 380px;
  border: 1px solid #000;
}
  .classroom_video {
    width: 300px;
    height: 200px;
    margin-left: 40px;
  
  }

  .classroom_title {
    height: 50px;
    font-size: 25px;
    text-align: center;
  }

  .classroom_operation {
    height: 100px;
  }
</style>