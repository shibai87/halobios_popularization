import request from './axios' // 使用实例

export default {
    getLoadactivity(activity_id){
    return request({
        url: `activity/get_activity`,
        method: 'GET',
        params:{
            activity_id:activity_id,
        }
    })
} 
}
