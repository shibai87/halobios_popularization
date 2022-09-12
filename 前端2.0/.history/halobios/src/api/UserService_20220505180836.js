import request from './axios' // 使用实例

export default {
    // 分类
    Login(user_tel,user_pwd) {
        return request({
            url: `user/login`,
            method: 'GET',
            params:{
                user_tel:user_tel,
                user_pwd:user_pwd,
            }
        })
    },
    register(user_name,user_tel,user_pwd){
        return request({
            url: `user/register`,
            method: 'GET',
            params:{
                user_name:user_name,
                user_tel:user_tel,
                user_pwd:user_pwd,
            }
        })
    },
    getUserActivityList(sign_up_user_id){
        return request({
            url: `activity/get_activity_listByUserId`,
            method: 'GET',
            params:{
                sign_up_user_id:sign_up_user_id,
            }
        })
    },
    getloadInfo(user_id){
        return request({
            url: `user/info`,
            method: 'GET',
            params:{
                user_id:user_id,
            }
        })
    }
}
