<template>
  <div class="maintain">
      <div class="trans">
        <div class="contain">
          <ul>
            <li>
              <span>背诵题目：</span>
              <div class="put">
                <el-input
                  v-model="saveForm.ho_name"
                  :placeholder="ruleForm.ho_name"
                ></el-input>
              </div>
            </li>
            <li>
              <span>背诵要求：</span>
              <div class="put">
                <el-input
                  v-model="saveForm.to_problem"
                  :placeholder="ruleForm.to_problem"
                ></el-input>
              </div>
            </li>

            <li>
              <span>截止时间：</span>
              <div class="put">
                <div class="block">
                  <el-date-picker
                    v-model="saveForm.ho_end_time"
                    type="date"
                    :placeholder="ruleForm.ho_end_time | formatDate"
                  >
                  </el-date-picker>
                </div>
              </div>
            </li>
          </ul>
        </div>
      </div>
        <div class="button">
        <el-button type="success" @click="updateForm('saveForm'), back()"
          >保存</el-button
        >
        <el-button type="primary" @click="PublishForm(), back()"
          >发布</el-button
        >
      </div>
  </div>
</template>
<script>
// import { formatDate } from "../common/common";
export default {
  inject: ["reload"],
  name: "test",
  components: {},
  data() {
    return {
      ruleForm: "",
      saveForm: {
        ho_name: "",
        to_content: "",
        to_problem: "",
        ho_end_time: "",
      },
    };
  },
  methods: {
    loadReciteMsg() {
      let ho_id = this.$route.params.ho_id;
      this.axios
        .get("http://10.197.43.62:8088/teacher/getbeisongxx?ho_id=" + ho_id)
        .then((res) => {
          this.ruleForm = res.data[0];
          console.log(this.ruleForm);
        });
    },
    PublishForm() {
      let ho_id = this.$route.params.ho_id;
      this.axios
        .get("http://10.197.43.62:8088/teacher/fabubeisong2?ho_id=" + ho_id)
        .then((res) => {
          if (res.data.data === 1) {
            this.$message({
              message: "恭喜你，发布成功",
              type: "success",
            });
            this.reload();
          } else {
            this.$message.error("发布失败");
          }
        });
    },
    updateForm() {
      let ho_id = this.$route.params.ho_id;
      let ho_name = this.saveForm.ho_name;
      let to_problem = this.saveForm.to_problem;
      let ho_end_time = this.saveForm.ho_end_time;
      this.axios
        .get(
          "http://10.197.43.62:8088/teacher/upzuowen?ho_name=" +
            ho_name +
            "&to_problem=" +
            to_problem +
            "&ho_end_time=" +
            ho_end_time +
            "&ho_id=" +
            ho_id
        )
        .then((res) => {
          if (res.data.data === 1) {
            this.$message({
              message: "恭喜你，修改成功",
              type: "success",
            });
            this.reload();
          } else {
            this.$message.error("修改失败");
          }
        });
    },
  },
  computed: {},

  mounted() {
    this.loadReciteMsg();
  },
//   filters: {
//     formatDate(time) {
//       let date = new Date(time);
//       return formatDate(date, "yyyy年MM月dd日");
//     },
//   },
};
</script>

<style scoped>
body {
  margin: 0;
  padding: 0;
}

.icon-fanhui {
  width: 4em;
  height: 4em;
  vertical-align: -0.15em;
  fill: currentColor;
  overflow: hidden;
}
.left {
  position: absolute;
  top: 1vh;
}
.maintain {
  z-index: -1;
  width: 100%;
 
  min-height: 170vh;
  position: absolute;
}
.bgc {
  display: flex;
  flex-wrap: wrap;
  position: relative;
}
.el-button--primary {
  background-color: #4169e1 !important;
  border-color: #4169e1;
  font-weight: 700;
}
/* .button {
  position: absolute;
  top: 45%;
  right: 45%;
  z-index: 2;
} */
.button {
  position: absolute;
  top: 5%;
  right: 5%;
}
.el-button--success {
  font-weight: 700;
}

.bgc h1 {
  font-size: 50px;
  text-align: center;
  vertical-align: middle;
  height: 110px;
  line-height: 100px;
  width: 100%;
}

.trans {
  position: relative;
  width: 100vw;
  min-height: 150vh;
  background: rgba(255, 255, 255, 0.5);
  padding-top: 50px;
}
.contain {
  margin-left: 30%;
}
.trans ul li {
  list-style: none;
  padding: 20px;
}

.trans ul li span {
  display: inline-block;
  font-size: 25px;
  font-weight: 700;
}
.put {
  display: inline-block;
  width: 500px;
}
/* .icon {
  width: 2em;
  height: 2em;
  vertical-align: -0.15em;
  fill: currentColor;
  overflow: hidden;
} */
</style>