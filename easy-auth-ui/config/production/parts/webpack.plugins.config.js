/**
 * webpack插件配置
 * @author chenzw
 * @type {module:path}
 */
const path = require('path');
const webpack = require('webpack');
const prodConfig = require('../config');

const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const VueLoaderPlugin = require('vue-loader/lib/plugin');
const CopyWebpackPlugin = require("copy-webpack-plugin");
const ParallelUglifyPlugin = require('webpack-parallel-uglify-plugin');
const OptimizeCssAssetsWebpackPlugin = require('optimize-css-assets-webpack-plugin');
const AddAssetHtmlPlugin = require('add-asset-html-webpack-plugin');
const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {

    htmlWebpackPlugin: new HtmlWebpackPlugin({
        template: 'src/template.html',
        filename: 'easy-auth-ui.html',
        // chunks: 'all',
        favicon: path.join(prodConfig.assetsPath, 'img/favicon.ico')
    }),


    // 将src的dll文件拷贝到dist目录
    copyWebpackPlugin: new CopyWebpackPlugin([{
        from: path.join(prodConfig.dllSrcPath, 'static'),
        to: prodConfig.staticDistPath
    }]),

    providePlugin: new webpack.ProvidePlugin({
        'window.jQuery': 'jquery',
        $: 'jquery',
        Vue: ['vue']
    }),
    vueLoaderPlugin: new VueLoaderPlugin(),

    // 提取CSS文件
    miniCssExtractPlugin: new MiniCssExtractPlugin({
        filename: `static/[name]/css/[name].css`,
        ignoreOrder: false
    }),

    // 压缩css插件配置
    optimizeCssAssetsWebpackPlugin: new OptimizeCssAssetsWebpackPlugin(),

    // 使用ParallelUglifyPlugin 并行压缩输出的JavaScript代码
    parallelUglifyPlugin: new ParallelUglifyPlugin({
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
    }),

    hotModuleReplacementPlugin: new webpack.HotModuleReplacementPlugin(),

    vendorDllReferencePlugin: new webpack.DllReferencePlugin({
        context: prodConfig.srcPath,
        manifest: require(path.join(prodConfig.dllSrcPath, 'vendor.manifest.json'))
    }),
    vueDllReferencePlugin: new webpack.DllReferencePlugin({
        context: prodConfig.srcPath,
        manifest: require(path.join(prodConfig.dllSrcPath, 'vue.manifest.json'))
    }),
    addAssetHtmlPlugin: new AddAssetHtmlPlugin([{
        filepath: path.join(prodConfig.dllSrcPath, '*.dll.js'),
        outputPath: 'static/easy-auth',
        publicPath: '/static/easy-auth',
        hash: true
    }, {
        filepath: path.join(prodConfig.dllSrcPath, '*.dll.css'),
        outputPath: 'static/easy-auth',
        publicPath: '/static/easy-auth',
        typeOfAsset: 'css',
        hash: true
    }])

}