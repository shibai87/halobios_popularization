import request from './axios' // 使用实例

export default {
    getLoadclassroom(video_id){
    return request({
        url: `video/get_video`,
        method: 'GET',
        params:{
            video_id:video_id,
        }
    })
} 
}
