/**
 * webpack插件配置
 * @author chenzw
 * @type {module:path}
 */
const path = require('path');
const webpack = require('webpack');
const devConfig = require('../config');

const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const VueLoaderPlugin = require('vue-loader/lib/plugin');
const CopyWebpackPlugin = require("copy-webpack-plugin");
const AddAssetHtmlPlugin = require('add-asset-html-webpack-plugin');
const HtmlWebpackPlugin = require('html-webpack-plugin');


module.exports = {

    htmlWebpackPlugin: new HtmlWebpackPlugin({
        template: 'src/template.html',
        filename: 'easy-auth-ui.html',
        // chunks: 'all',
        favicon: path.join(devConfig.assetsPath, 'img/favicon.ico')
    }),

    // 将src的dll文件拷贝到dist目录
    copyWebpackPlugin: new CopyWebpackPlugin([{
        from: path.join(devConfig.dllSrcPath, 'static'),
        to: devConfig.staticDistPath
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

    hotModuleReplacementPlugin: new webpack.HotModuleReplacementPlugin(),

    vendorDllReferencePlugin: new webpack.DllReferencePlugin({
        context: devConfig.srcPath,
        manifest: require(path.join(devConfig.dllSrcPath, 'vendor.manifest.json'))
    }),
    vueDllReferencePlugin: new webpack.DllReferencePlugin({
        context: devConfig.srcPath,
        manifest: require(path.join(devConfig.dllSrcPath, 'vue.manifest.json'))
    }),
    addAssetHtmlPlugin: new AddAssetHtmlPlugin([{
        filepath: path.join(devConfig.dllSrcPath, '*.dll.js'),
        outputPath: 'static/easy-auth',
        publicPath: '/static/easy-auth',
        hash: true
    }, {
        filepath: path.join(devConfig.dllSrcPath, '*.dll.css'),
        outputPath: 'static/easy-auth',
        publicPath: '/static/easy-auth',
        typeOfAsset: 'css',
        hash: true
    }])
}