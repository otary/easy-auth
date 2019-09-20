/**
 * webpack entry配置
 *
 * @author chenzw
 * @type {module:path}
 */
const path = require('path');
const merge = require('webpack-merge');
const devConfig = require('../config');

// 单页面entry
module.exports = {
    "easy-auth": path.resolve(devConfig.srcPath, 'index.entry.js')
}