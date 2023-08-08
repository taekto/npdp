const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  publicPath: process.env.NODE_ENV === 'production'
  ? '/'
  : '/',
  transpileDependencies: true,
  devServer: {
    historyApiFallback: true,
    proxy: {
      '/api': {
        target: 'https://i9b202.p.ssafy.io:8080',
        changeOrigin: true,
        withCredentials: true,
        pathRewrite: {
          '^/api': ''  // 프록시 경로 제거 (선택사항)
        }
      }
    }
  }
})