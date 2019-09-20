/**
 * webpack dll配置
 *
 * @author chenzw
 * @type {module:path}
 */
const path = require('path');
const webpack = require('webpack');
const merge = require('webpack-merge');
const DllPlugin = webpack.DllPlugin;
const devConfig = require('./config');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const ParallelUglifyPlugin = require('webpack-parallel-uglify-plugin');
const OptimizeCssAssetsWebpackPlugin = require('optimize-css-assets-webpack-plugin');
const {CleanWebpackPlugin} = require("clean-webpack-plugin");

const dllLibraryName = '_dll_[name]';

module.exports = {
    mode: 'production',
    entry: {
        vue: ['vue', 'vuex', 'axios'],
        vendor: ['jquery', 'element-ui', 'normalize.css', 'element-ui/lib/theme-chalk/index.css', 'font-awesome/css/font-awesome.css']
    },
    output: {
        filename: '[name].dll.js',
        path: devConfig.dllSrcPath,

        publicPath: '/',
        library: dllLibraryName
    },

    module: {
        rules: require('./parts/webpack.module.config')
    },

    plugins: [
        // new CleanWebpackPlugin(),

        new DllPlugin({
            context: devConfig.srcPath,
            name: dllLibraryName,
            path: path.join(devConfig.dllSrcPath, '[name].manifest.json')
        }),
        new MiniCssExtractPlugin({
            filename: `[name].dll.css`,
            ignoreOrder: false
        }),
        // 压缩css插件配置
        new OptimizeCssAssetsWebpackPlugin(),

        // 使用ParallelUglifyPlugin 并行压缩输出的JavaScript代码
        new ParallelUglifyPlugin({
            uglifyJS: {
                output: {
                    //最紧凑的输出
                    beautify: false,
                    //删除所有注释
                    comments: true
                },
                compress: {
                    //删除所有console语句，可以兼容IE浏览器
                    drop_console: true,
                    //内嵌已定义但是只用到一次的变量
                    collapse_vars: true
                }
            }
        })
    ]
}