import request from './axios' // 使用实例

export default {
    add(operation_id, comment_id, report_user_id) {
        return request({
            url: `report/add`,
            method: 'GET',
            params: {
                operation_id: operation_id,
                comment_id: comment_id,
                report_user_id: report_user_id
            }
        })
    },
}