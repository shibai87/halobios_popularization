import request from './axios' // 使用实例

export default {
    judgeComment(operation_user_id,operation_source_type,operation_source_id){
        return request({
            url: `user/login`,
            method: 'POST',
            params:{
                operation_user_id:operation_user_id,
                operation_source_type:operation_source_type,
                operation_source_id:operation_source_id
            }
        })
    },
}