import request from './axios' // 使用实例

export default {
    getactivity(activity_id){
    return request({
        url: `activity/get_activity`,
        method: 'GET',
        params:{
            activity_id:activity_id,
        }
    })
},
get_activity_list(activity_upload){
    return request({
        url: `activity/get_activity`,
        method: 'GET',
        params:{
            activity_upload:activity_upload,
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
}
