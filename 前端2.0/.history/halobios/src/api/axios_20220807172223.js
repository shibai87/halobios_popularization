import axios from 'axios'
import { Notify } from 'vant';

// 创建axios实例
const service = axios.create({
    baseURL: 'http://192.168.1.105:8083', // api 的 base_url
    timeout: 6000, // 请求超时时间
})

// request 请求拦截器
service.interceptors.request.use()

// response 拦截器
service.interceptors.response.use(
    (response) => {
        return response.data
    },
    (error) => {
        Notify({ type: 'warning', message: '获取信息失败!' });
        return error
    }
)

export default service