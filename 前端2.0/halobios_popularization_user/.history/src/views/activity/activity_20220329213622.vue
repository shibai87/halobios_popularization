<template>
  <div id="activity">
    <!-- 分类知识详情 -->
    <div v-if="activityList.length">
      <ul v-for="(item,index) in activityList" :key="index" class="activity_list">
        <li class="acitivity_item">
            <video controls :src="Video(item)" class="activity_video"></video>
            <div class="activity_title">
              <span>{{item.activity_title}}</span>
            </div> 
            <div class="activity_operation"> 
              <van-button type="primary" @click="edit_activity(item.activity_id)">查看</van-button>
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
    name: "activity",
    data() {
      return {
        activityList: [],
      }
    },
    methods: {
      // 获取知识列表+查询列表
      getLoadactivityList() {
        this.axios.get("http://localhost:8083/activity/get_activity_list?activity_upload=" + 1)
          .then((res) => {
            // console.log(res.data,res.data.length)
            this.activityList = res.data
            // console.log(this.activityList.length)
          })

      },
      // 编辑
      edit_activity(activity_id) {
        this.$store.commit("judgeactivity", true)
         localStorage.setItem('activity_id', activity_id);
        this.$store.commit("getactivity", activity_id)
        this.$router.push("/editactivity_detail");
      },
      delete_activity(activity_id){
        this.axios.get("http://localhost:8083/activity/delete_activity?activity_id="+activity_id)
          .then((res) => {
            console.log(res.data)
            if (res.data.msg === "删除成功") {
              this.$message({
                message: "删除成功!",
                type: "success",
              });
            } else {
              this.$message({
                message: "删除失败!",
                type: "danger",
              });
            }
            this.reload()
          });
      },

      //视频路径
      Video(row) {
        var url = "http://localhost:8083/activity/" + row.activity_file_name
        console.log(url)
        return url
      },
    },
    computed: {
    },

    mounted() {
      this.getLoadactivityList();
    }
  }
</script>
<style>
  /*  */
  #activity .van-alert {
    margin-bottom: 10px;
  }



  #activity .van-button {
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
.activity_list{
 display: inline-flex;
 flex-wrap:wrap;
}
.acitivity_item{
margin: 10px;
  width: 300px;
  border: 1px solid #000;
}
  .activity_video {
    width: 200px;
    height: 200px;
    margin-left: 20px;
  }

  .activity_title {
    height: 50px;
    font-size: 25px;
    text-align: center;
  }

  .activity_operation {
    height: 100px;
  }
</style>