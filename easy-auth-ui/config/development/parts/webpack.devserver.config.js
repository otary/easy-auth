/**
 * webpack 服务器配置
 *
 * @author chenzw
 * @type {module:path}
 */

const path = require('path');
const merge = require('webpack-merge');
const devConfig = require('../config');

module.exports = {

    // 指定根目录
    contentBase: devConfig.distPath,

    // 指定端口
    port: devConfig.port,

    // 热替换
    inline: true,
    watchOptions: {
        aggregateTimeout: 300,
        poll: 1000
    },
    watchContentBase: true,

    // 定义头部信息
    headers: {"Access-Control-Allow-Origin": "*"},

    // 编译完成后打开浏览器
    // open: true,

    // 显示编译进度
    progress: true,

    // 重定向
    proxy: {
        // 代理到后端服务接口
        "/api": {
            target: "http://localhost:3000",
            pathRewrite: {"^/api": ""}
        }
    }
}