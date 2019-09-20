/**
 * webpack entry配置
 *
 * @author chenzw
 * @type {module:path}
 */
const path = require('path');
const merge = require('webpack-merge');
const prodConfig = require('../config');

// 单页面entry
module.exports = {
    "easy-auth": path.resolve(prodConfig.srcPath, 'index.entry.js')
}