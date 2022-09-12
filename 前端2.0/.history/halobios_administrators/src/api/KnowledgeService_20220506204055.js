import request from './axios' // 使用实例

export default {
    // 分类
    get_all_sort() {
        return request({
            url: `knowledge/get_all_sort`,
            method: 'GET'
        })
    },
    get_knowledge(knowledge_id) {
        return request({
            url: `knowledge/get_knowledge`,
            method: 'GET',
            params: {
                knowledge_id: knowledge_id,
            }
        })
    },
    // get_knowledge_list(knowledge_upload, options_id) {
    //     return request({
    //         url: `knowledge/get_knowledge_list`,
    //         method: `GET`,
    //         params: {
    //             knowledge_upload: knowledge_upload,
    //             options_id: options_id
    //         }
    //     })
    // },
    get_sort(knowledge_sort_id, options_id) {
        return request({
            url: `knowledge/get_sort`,
            method: `GET`,
            params: {
                knowledge_sort_id: knowledge_sort_id,
                options_id: options_id
            }
        })
    },
    search() {
        return request({
            url: `knowledge/search`,
            method: `GET`,
        })
    },
    get_knowledgeTitle(knowledge_title) {
        return request({
            url: `knowledge/get_knowledgeTitle`,
            method: `GET`,
            params: {
                knowledge_title:knowledge_title
            }
        })
    },

}