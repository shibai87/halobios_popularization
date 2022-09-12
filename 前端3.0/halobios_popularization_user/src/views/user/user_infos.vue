<template>
  <div id="user_infos">
    <van-nav-bar title="个人信息" left-arrow fixed @click-left="pageBack" class="user_infos_title_icon" />
    <van-pull-refresh v-model="isLoading" @refresh="onRefresh">
      <van-form @submit="onSubmit">
        <van-field v-model="info.user_name" label="用户名" placeholder="请输入用户名" class="name" clearable />
        <van-field v-model="info.user_tel" type="tel" label="手机号" placeholder="请输入手机号码" disabled />
        <van-field v-model="info.user_pwd" type="password" label="密码" clearable />
        <van-row class="sex">
          <van-col span="6">
            <div class="sex_title">性别</div>
          </van-col>
          <van-col span="18">
            <van-radio-group v-model="info.user_sex" direction="horizontal">
              <van-radio name="0">男</van-radio>
              <van-radio name="1">女</van-radio>
            </van-radio-group>
          </van-col>
        </van-row>
        <van-button type="primary" size="large" native-type="submit">提交</van-button>
      </van-form>
    </van-pull-refresh>
  </div>
</template>

<script lang="ts" setup>
import { ref, reactive } from "vue"
import { onMounted } from "vue";
import { useStore } from "vuex"
import { useRouter } from 'vue-router'
import { inject } from "vue"
import { Toast } from 'vant';
import { Dialog } from 'vant';
import UserService from "../../api/UserService.js"
const store = useStore()
const router = useRouter()
const refresh = inject<any>("reload")

//              // 刷新
const isLoading = ref<boolean>(false)
const info = ref<any>([])
const getUserId = ref<any>(JSON.parse(sessionStorage.getItem('user_id') || ""))
const pageBack = () => {
  router.push("/user_center")
}
onMounted(() => {
  getloadInfo()
})

const getloadInfo = () => {
  UserService.info(getUserId.value)
    .then((res) => {
      info.value = res
      info.value.user_sex = JSON.stringify(info.value.user_sex)
    })
}
// 刷新
const onRefresh = () => {
  setTimeout(() => {
    Toast('刷新成功');
    isLoading.value = false;
    refresh()
  }, 1000);
}

const onSubmit = () => {
  info.value.user_sex = JSON.parse(info.value.user_sex);
  UserService.updateuser(info.value.user_name, info.value.user_pwd, info.value.user_sex,
    getUserId.value)
    .then((res) => {
      if (res.msg == '修改成功') {
        Dialog.alert({
          title: '修改成功',
          message: '恭喜你，修改成功!',
        })
        getloadInfo()
      } else {
        Dialog.alert({
          title: '修改失败',
          message: '修改失败!',
        })
      }
    })
    .catch(() => {
      // err
    });
}
</script>

<style>
/* user_infos_head */
#user_infos .van-nav-bar__content {
  height: 70px;

}

#user_infos .van-nav-bar__title {
  height: 30px;
  line-height: 30px;
  font-size: 25px;

}

/*  */
#user_infos .van-radio-group--horizontal {
  height: 50px;
}

.user_infos_title_icon .van-icon:before {
  color: #000;
  font-size: 35px;
}
</style>

<style scoped>
#user_infos {
  z-index: -1;
  width: 100%;
  background-image: url("../../assets/images/5.jpeg");
  background-repeat: no-repeat;
  background-attachment: fixed;
  overflow: hidden;
  background-size: cover;
  min-height: 100vh;
  position: absolute;
  background-position: center;

}

/*  */
.name {
  margin-top: 200px;
}

/* 性别 */
.sex {
  background-color: #fff;
  height: 50px;
}

.sex_title {
  padding: 10px;
  font-size: 26px;
}
</style>