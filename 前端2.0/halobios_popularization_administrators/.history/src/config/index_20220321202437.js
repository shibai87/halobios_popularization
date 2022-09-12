// // 跨域访问后台api
module.exports = {
dev: {
    // Paths
    assetsSubDirectory: 'static',
    assetsPublicPath: '/',
    proxyTable: {//设置代理
    // 在访问后台数据时,在地址前面面加上/apis,则请求会重写为 http://127.0.0.1:3000,
    //下面的pathRewrite指 /apis 会被重写为 '',这里用的正则表达式 '^'表示从头开始匹配
      '/api':{
    // target后台的接口地址
        target: "http://192.168.1.106:8081/",
        secure: false,//http接口需要配置的参数
        changeOrigin: true,
        pathRewrite: {
          '^/api': ''//以/api开头的接口地址都会被替换为target中的地址
        }
      }
    }
}
}
