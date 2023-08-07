const { defineConfig } = require("@vue/cli-service");
module.exports = defineConfig({
  transpileDependencies: true,

  // Build Directory
  outputDir: "../src/main/resources/static",

  //인덱스 html 경로
  indexPath: "../templates/index.html",

  devServer: {
    proxy: {
      '/':{
        "target":'http://localhost:8080', // Spring boot의 주소 및 포트
        "pathRewrite":{'^/':''},
        "changeOrigin":true,
        "ws":false,
      }
    }
  },

  pluginOptions: {
    vuetify: {
      // https://github.com/vuetifyjs/vuetify-loader/tree/next/packages/vuetify-loader
    },
  },
});
