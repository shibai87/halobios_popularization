import request from './axios' // 使用实例

export default {
   getLoadKnowledge(knowledge_id){
    return request({
        url: `knowledge/get_knowledge`,
        method: 'GET',
        params:{
            knowledge_id:knowledge_id,
        }
    })
} 
}
