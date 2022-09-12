import request from './axios' // 使用实例

export default {
    getLoadclassroom(activity_id){
    return request({
        url: `video/get_video?`,
        method: 'GET',
        params:{
            activity_id:activity_id,
        }
    })
} 
}
