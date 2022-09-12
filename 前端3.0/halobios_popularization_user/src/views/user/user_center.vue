<template>
    <div id="user_center">
        <!--user_center_head -->
        <van-nav-bar title="个人中心" left-arrow fixed @click-left="pageBack" />
        <!-- user_center_main -->
        <van-row type="flex" justify="space-around">
            <van-col span="6">
                <router-link to="/user_infos">
                    <div class="user_center_userName">{{user_name}}</div>
                </router-link>
            </van-col>
            <van-col span="6" offset="4">
                <div @click="log_off"> <button>注销</button></div>
            </van-col>
        </van-row>
        <ul>
            <li>
                <router-link to="/user_infos">
                    <van-icon name="manager-o" />
                    个人信息
                </router-link>
            </li>
            <li>
                <router-link to="/user_comment">
                    <van-icon name="comment-o" />
                    我的评论
                </router-link>
            </li>

            <li @click="details(1)">
                <van-icon name="like-o" />
                我的点赞
            </li>
            <li @click="details(2)">
                <van-icon name="star-o" />
                我的收藏
            </li>
            <li>
                <router-link to="/user_activity">
                    <van-icon name="friends-o" />
                    我参与的活动
                </router-link>
            </li>
            <!-- <li>
           <router-link to="/user_notice">
            <van-icon name="comment-circle-o" />审核与举报
          </router-link>
        </li> -->


        </ul>
    </div>
</template>
  
<script lang="ts" setup>
import { ref, reactive } from "vue"
import { onMounted } from "vue";
import { useStore } from "vuex"
import { useRouter } from 'vue-router'
import UserService from "../../api/UserService"
const store = useStore()
const router = useRouter()
const user_name = ref("")
const getUserId = ref(JSON.parse(sessionStorage.getItem('user_id') || ""))
onMounted(() => {
    getloadInfo()
})
const getloadInfo = () => {
    UserService.info(getUserId.value)
        .then((res) => {
            user_name.value = res.user_name
        })
}
const pageBack = () => {
    router.push("/")
}

const details = (val: any) => {
    let user_type;
    if (val == 1) {
        user_type = "我的点赞"
    } else {
        user_type = "我的收藏"
    }
    sessionStorage.setItem('user_type', user_type);
    store.commit("chioceUserType", user_type);
    router.push("/user_other_operation")
}
const log_off = () => {
    sessionStorage.setItem('judge_login', "false");
    sessionStorage.setItem('user_id', "");
    store.commit("getSortIdLast", "")
    router.push("/")
}
</script>
<style lang="less">
#user_center {
    /* user_center_head */
    .van-nav-bar__content {
        height: 70px;
        border-bottom: 1px solid #000;
    }

    .van-nav-bar__title {
        height: 30px;
        line-height: 30px;
        font-size: 25px;
    }

    .van-icon:before {
        color: #000;
        font-size: 35px;
    }

    /*  */
    .van-row {
        margin: 120px 0 40px;
        font-size: 30px;
    }

    .van-col--14 {
        height: 100px;
        line-height: 100px;
        text-align: left;
    }
}
</style>
  
<style scoped>
/* 头像 */
.img_title {
    text-align: center;
}

/*  */
.user_center_userName {
    overflow: hidden;
    width: 200px;
    text-overflow: ellipsis;
    white-space: nowrap;
}

ul {
    margin-top: 10px;
}

li {
    display: block;
    padding-left: 10px;
    height: 90px;
    line-height: 90px;
    border: 1px solid rgb(187, 183, 183);
}
</style>