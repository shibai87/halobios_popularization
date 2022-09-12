module.exports ={
     proxyTable: {
            '/api': {
                target: 'http://192.168.1.103:8083',//后端接口地址
                secure: true,  // 如果是https接口，需要配置这个参数
                changeOrigin: true,//是否允许跨越
                pathRewrite: {
                    '^/api': '',//重写,
                }
            }
        }
}
       
   