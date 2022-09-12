import request from './axios' // 使用实例

export default {
    add(operation_id, comment_id, report_user_id) {
        return request({
            url: `report/add`,
            method: 'GET',
            params: {
                operation_user_id: operation_user_id,
                operation_source_type: operation_source_type,
                operation_type: operation_type
            }
        })
    },
}