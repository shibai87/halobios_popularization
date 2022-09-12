import request from './axios' // 使用实例

export default {
    // 分类
    getSortListRequest() {
        return request({
            url: `knowledge/get_all_sort`,
            method: 'GET'
        })
    },

    getKnowledge(knowledge_id){
        return request({
            url: `knowledge/get_knowledge`,
            method: 'GET',
            params:{
                knowledge_id:knowledge_id,
            }
        })
    },
    get_knowledge_list(knowledge_upload){
        return request({
            url:`knowledge/get_knowledge_list`,
            method:`GET`,
            params:{
                knowledge_upload:knowledge_upload,
                options_id:options_id
            }
        })
    }
}
