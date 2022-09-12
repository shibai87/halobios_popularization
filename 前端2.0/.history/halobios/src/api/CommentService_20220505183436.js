import request from './axios' // 使用实例

export default {
    judgeComment(operation_user_id,operation_source_type,operation_source_id){
        return request({
            url: `operation/judgeOperationId`,
            method: 'GET',
            params:{
                operation_user_id:operation_user_id,
                operation_source_type:operation_source_type,
                operation_source_id:operation_source_id
            }
        })
    },
    addComment(operation_user_id,operation_source_type,operation_source_id,comment_content){
        return request({
            url:`operation/addComment`,
            method:`POST`,
            params:{
                operation_user_id:operation_user_id,
                operation_source_type:operation_source_type,
                operation_source_id:operation_source_id,
                comment_content:comment_content
            }
        })
    },
    updateComment(operation_id,comment_content){
        return request({
            url:`operation/updateComment`,
            method:`GET`,
            params:{
                operation_id:operation_id,
                comment_content:comment_content,
            }
        })
    },
    deleteComment(operation_id){
        return request({
            url:`operation/deleteComment`,
            method:`GET`,
            params:{
                operation_id:operation_id
            }
        })
    }
}