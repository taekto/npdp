const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
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