import request from './axios' // 使用实例

export default {
    // 分类
    getSortListRequest() {
        return request({
            url: `knowledge/get_all_sort`,
            method: 'GET'
        })
    },

 
}
