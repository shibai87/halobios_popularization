import request from './axios' // 使用实例

export default {
    // 分类
    getSortListRequest() {
        return request({
            url: `knowledge/get_all_sort`,
            method: 'GET'
        })
    },
    //  // 分类
    //  getKnowledgeListRequest(knowledge_upload,options_id) {
    //     return request({
    //         url: `knowledge/get_knowledge_list`,
    //         method: 'GET',
    //         params: {
    //             knowledge_upload:knowledge_upload,
    //             options_id:options_id
    //         }
           
    //     })
    // },
}
