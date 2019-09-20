/**
 * webpack输出项
 *
 * @author chenzw
 * @type {module:path}
 */

const path = require('path');
const merge = require('webpack-merge');
const prodConfig = require('../config');

module.exports = {
    path: prodConfig.distPath,
    filename: 'static/[name]/js/[name].js',
    chunkFilename: 'static/[name]/js/[id].[chunkhash].bundle.js',

    // 使用绝对路径
    publicPath: '/'
}