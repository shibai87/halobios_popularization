import request from './axios' // 使用实例

export default {
    getloadOperation(operation_user_id,operation_source_type,operation_type){
        return request({
            url: `operation/getUserOperationList`,
            method: 'GET',
            params:{
                operation_user_id:operation_user_id,
                operation_source_type:operation_source_type,
                operation_type:operation_type
            }
        })
    },
}
