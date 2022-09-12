import request from './axios' // 使用实例

export default {
    get_video(video_id) {
        return request({
            url: `video/get_video`,
            method: 'GET',
            params: {
                video_id: video_id,
            }
        })
    },
    get_video_list(video_upload) {
        return request({
            url: `video/get_video_list`,
            method: 'GET',
            params: {
                video_upload: video_upload,
            }
        })
    },
    search() {
        return request({
            url: `video/search`,
            method: `GET`,
        })
    },
}