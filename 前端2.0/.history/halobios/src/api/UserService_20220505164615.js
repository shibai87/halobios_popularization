import request from './axios' // 使用实例

export default {
    // 分类
    Login(user_tel,user_pwd) {
        return request({
            url: `user/login`,
            method: 'GET',
            params:{
                user_tel:user_tel,
                user_pwd:user_pwd,
            }
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
