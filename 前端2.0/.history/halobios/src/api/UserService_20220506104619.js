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
    getloadInfo(user_id){
        return request({
            url: `user/info`,
            method: 'GET',
            params:{
                user_id:user_id,
            }
        })
    },
    updateuser(user_name,user_pwd,user_sex,user_id){
        return request({
            url: `user/updateuser`,
            method: 'GET',
            params:{
                user_name:user_name,
                user_pwd:user_pwd,
                user_sex:user_sex,
                user_id:user_id
            }
        })
    },
    getloadComment(operation_user_id){
        return request({
            url: `operation/getUserCommentList`,
            method: 'GET',
            params:{
                operation_user_id:operation_user_id,
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
    getloadOperation(sign_up_user_id){
        return request({
            url: `activity/get_activity_listByUserId`,
            method: 'GET',
            params:{
                sign_up_user_id:sign_up_user_id,
            }
        })
    },
    
   
}
