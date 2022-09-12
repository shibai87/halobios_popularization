import request from './axios' // 使用实例

export default {
    getclassroom(video_id){
        return request({
            url: `video/get_video`,
            method: 'GET',
            params:{
                video_id:video_id,
            }
        })
} ,
get_video_list(video_id){
    return request({
        url: `video/get_video_list`,
        method: 'GET',
        params:{
            video_id:video_id,
        }
    })
} ,
}
