const path = require('path');
const merge = require('webpack-merge');
const devConfig = require('./config');

module.exports = {
    mode: 'development',
    entry: require('./parts/webpack.entries.config'),
    output: require('./parts/webpack.output.config'),
    module: {
        rules: require('./parts/webpack.module.config')
    },
    devtool: '#source-map',
    // 输出构建性能信息（用于分析说明原因导致构建性能不佳）
    // profile: true,
    plugins: Object.values(require('./parts/webpack.plugins.config')),
    resolve: require('./parts/webpack.resolve.config'),
    devServer: require('./parts/webpack.devserver.config')
}