module.exports = {
    devServer: {
      proxy: {
        '/members': {
          target: 'https://i9b202.p.ssafy.io:8080/api',
          changeOrigin: true,
          withCredentials: true
        }
      }
    }
  };