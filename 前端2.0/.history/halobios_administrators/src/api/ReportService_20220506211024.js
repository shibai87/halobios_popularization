import request from './axios' // 使用实例

export default {
    // add(operation_id, comment_id, report_user_id) {
    //     return request({
    //         url: `report/add`,
    //         method: 'GET',
    //         params: {
    //             operation_id: operation_id,
    //             comment_id: comment_id,
    //             report_user_id: report_user_id
    //         }
    //     })
    // },
    getBycommentId(comment_id) {
        return request({
            url: `report/getBycommentId`,
            method: 'GET',
            params: {
                comment_id:comment_id
            }
        })
    },
    updata(comment_state,comment_id,report_result) {
        return request({
            url: `report/updata`,
            method: 'GET',
            params: {
                comment_state:comment_state,
                comment_id:comment_id,
                report_result:report_result
            }
        })
    },
    get(){
        return request({
            url: `report/get`,
            method: 'GET',
        })
    },
    getBySourceTypeAndCommentState(operation_source_type,comment_state) {
        return request({
            url: `report/getBySourceTypeAndCommentState`,
            method: 'GET',
            params: {
                operation_source_type:operation_source_type,
                comment_state:comment_state
            }
        })
    },
    getByCommentState(comment_state) {
        return request({
            url: `report/getByCommentState`,
            method: 'GET',
            params: {
                comment_state:comment_state
            }
        })
    },
}