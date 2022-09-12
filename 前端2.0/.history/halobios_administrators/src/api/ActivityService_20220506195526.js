import request from './axios' // 使用实例

export default {
    // get_activity(activity_id) {
    //     return request({
    //         url: `activity/get_activity`,
    //         method: 'GET',
    //         params: {
    //             activity_id: activity_id,
    //         }
    //     })
    // },
    get_activity_list(activity_upload) {
        return request({
            url: `activity/get_activity_list`,
            method: 'GET',
            params: {
                activity_upload: activity_upload,
            }
        })
    },
    // get_activity_listByUserId(sign_up_user_id) {
    //     return request({
    //         url: `activity/get_activity_listByUserId`,
    //         method: 'GET',
    //         params: {
    //             sign_up_user_id: sign_up_user_id,
    //         }
    //     })
    // },
    // get_SignUp(sign_up_user_id, sign_up_activity_id) {
    //     return request({
    //         url: `activity/get_SignUp`,
    //         method: 'GET',
    //         params: {
    //             sign_up_user_id: sign_up_user_id,
    //             sign_up_activity_id: sign_up_activity_id
    //         }
    //     })
    // },
    // add_SignUp(sign_up_user_id, sign_up_activity_id) {
    //     return request({
    //         url: `activity/add_SignUp`,
    //         method: 'GET',
    //         params: {
    //             sign_up_user_id: sign_up_user_id,
    //             sign_up_activity_id: sign_up_activity_id
    //         }
    //     })
    // },
    // updata_SignUp(sign_up_id) {
    //     return request({
    //         url: `activity/updata_SignUp`,
    //         method: 'GET',
    //         params: {
    //             sign_up_id: sign_up_id,
    //         }
    //     })
    // },
    search() {
        return request({
            url: `activity/search`,
            method: `GET`,
        })
    },
    delete_activity(activity_id,activity_file_name) {
        return request({
            url: `activity/delete_activity`,
            method: 'GET',
            params: {
                activity_id: activity_id,
                activity_file_name:activity_file_name,
            }
        })
    },
    get_activityTitle(activity_title) {
        return request({
            url: `activity/get_activityTitle`,
            method: 'GET',
            params: {
                activity_title:activity_title,
            }
        })
    },
}