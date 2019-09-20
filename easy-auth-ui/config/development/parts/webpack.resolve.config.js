/**
 *
 * @author chenzw
 * @type {module:path}
 */

const path = require('path');
const merge = require('webpack-merge');
const devConfig = require('../config');

module.exports = {
    // 寻找模块的根目录，默认以node_modules为根目录
    modules: ['node_modules'],
    alias: {
        '@': devConfig.srcPath,
        '@assets': devConfig.assetsPath,
        '@scss': path.join(devConfig.assetsPath, 'scss')
    },
    extensions: ['.js', '.vue', '.json', '.css', '.scss']
}