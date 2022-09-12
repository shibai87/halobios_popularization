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
    get_videoTitle(video_title) {
        return request({
            url: `video/get_videoTitle`,
            method: 'GET',
            params: {
                video_title: video_title,
            }
        })
    },
    delete_video(video_id,video_file_name) {
        return request({
            url: `video/delete_video`,
            method: 'GET',
            params: {
                video_id:video_id,
                video_file_name:video_file_name,
            }
        })
    },
}