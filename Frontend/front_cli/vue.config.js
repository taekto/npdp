const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    proxy: {
      '/member': {
        target: 'https://i9b202.p.ssafy.io:8080/api',
        changeOrigin: true,
        withCredentials: true
      }
    }
  }
})
