import axios from 'axios'

// 创建axios实例
const request = axios.create({
    baseURL: 'http://127.0.0.1:8083', // api 的 base_url
    timeout: 2000, // 请求超时时间
})
// axios.defaults.baseURL='http://127.0.0.1:5173:8083'
// axios.defaults.timeout=2000
// request 请求发起前的拦截器
request.interceptors.request.use()

// response 响应收到后的拦截器
request.interceptors.response.use(
    (response) => {
        return response.data
    },
    (error) => {
        return error
    }
    )
    // 在AxiosResponse上规定返回的一些字段
    declare module 'axios' {
        interface AxiosInstance {
          (config: AxiosRequestConfig): Promise<any>
        }
      }

export default request