/**
 * webpack loader配置
 *
 * @author chenzw
 * @type {module:path}
 */
const path = require('path');
const merge = require('webpack-merge');
const devConfig = require('../config');

const MiniCssExtractPlugin = require('mini-css-extract-plugin');

module.exports = [
    {
        test: /\.(sa|sc|c)ss$/,
        use: [{
            loader: MiniCssExtractPlugin.loader,
            options: {
                hmr: true,
            },
        }, 'css-loader', 'postcss-loader', 'sass-loader']
    }, {
        test: /\.(js|jsx)$/,
        use: [{
            loader: 'babel-loader'
        }],

        // 排除 node_modules 目录下的文件（node_modules 目录下的文件都是采用的 ES5 语法，没必要再通过 Babel 去转换）
        exclude: /node_modules/
    }, {
        test: /\.(gif|png|jpe?g|pdf)$/,
        use: ['file-loader']
    }, {
        test: /\.(mp4|webm|ogg|mp3|wav|flac|aac)(\?.*)?$/,
        loader: 'url-loader',
        options: {
            limit: 10000,
            name: 'static/media/[name].[hash:7].[ext]'
        }
    }, {
        test: /\.(woff2?|eot|ttf|otf)(\?.*)?$/,
        use: [{
            loader: 'url-loader',
            options: {
                name: 'static/fonts/[name].[hash:8].[ext]',
                limit: 10000
            }
        }]
    }, {
        test: /\.svg(\?v=\d+\.\d+\.\d+)?$/,
        use: [{
            loader: 'url-loader',
            options: {
                limit: 10000,
                mimeType: 'image/svg+xml',
                name: 'static/imgs/[name].[hash:7].[ext]'
            }
        }]
    }, {
        test: /\.vue$/,
        use: ['vue-loader'],
        exclude: /node_modules/
    }
]