module.exports = {
    css: {
      loaderOptions: {
        less: {
          // 若 less-loader 版本小于 6.0，请移除 lessOptions 这一级，直接配置选项。
          lessOptions: {
            modifyVars: {
              // 直接覆盖变量
              'text-color': '#111',
              'border-color': '#eee',
              // 或者可以通过 less 文件覆盖（文件路径为绝对路径）
              hack: `true; @import "your-less-file-path.less";`,
            },
          },
        },
      },
    },
  };
  module.exports = {
    css: {
      loaderOptions: {
        postcss: {
          plugins: [
            require('postcss-pxtorem')({ // 把px单位换算成rem单位
              rootValue: 37.5, // vant官方使用的是37.5
              selectorBlackList: ['vant', 'mu'], // 忽略转换正则匹配项
              propList: ['*']
            })
          ]
        }
      }
    }
  }