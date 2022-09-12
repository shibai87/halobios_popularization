proxyTable: {
    '/api':
      {
      target: 'https://u.y.qq.com/cgi-bin',
      secure: false,
      changeOrigin: true,
      pathRewrite: {
           '^/api': ''
        }


    }
  }