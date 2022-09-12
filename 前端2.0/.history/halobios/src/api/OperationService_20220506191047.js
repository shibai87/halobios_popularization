import request from './axios' // 使用实例

export default {
    getUserOperationList(operation_user_id, operation_source_type, operation_type) {
        return request({
            url: `operation/getUserOperationList`,
            method: 'GET',
            params: {
                operation_user_id: operation_user_id,
                operation_source_type: operation_source_type,
                operation_type: operation_type
            }
        })
    },
    getUserCommentList(operation_user_id) {
        return request({
            url: `operation/getUserCommentList`,
            method: 'GET',
            params: {
                operation_user_id: operation_user_id,
            }
        })
    },
    judgeOperationId(operation_user_id, operation_source_type, operation_source_id) {
        return request({
            url: `operation/judgeOperationId`,
            method: 'GET',
            params: {
                operation_user_id: operation_user_id,
                operation_source_type: operation_source_type,
                operation_source_id: operation_source_id
            }
        })
    },
    deleteComment(operation_id) {
        return request({
            url: `operation/deleteComment`,
            method: 'GET',
            params: {
                operation_id: operation_id,
            }
        })
    },
    getCommentList(operation_source_type,operation_source_id,operation_user_id) {
        return request({
            url: `operation/getCommentList`,
            method: 'GET',
            params: {
                operation_source_type: operation_source_type,
                operation_source_id:operation_source_id,
                operation_user_id:operation_user_id
            }
        })
    },
    getOperationNumber(operation_source_type,operation_source_id,operation_type) {
        return request({
            url: `operation/getOperationNumber`,
            method: 'GET',
            params: {
                operation_source_type:operation_source_type,
                operation_source_id:operation_source_id,
                operation_type:operation_type
            }
        })
    },
    getUserOperation(user_id,operation_source_type,operation_source_id) {
        return request({
            url: `operation/getOperationNumber`,
            method: 'GET',
            params: {
                user_id:user_id,
                operation_source_type:operation_source_type,
                operation_source_id:operation_source_id
            }
        })
    },
    
}