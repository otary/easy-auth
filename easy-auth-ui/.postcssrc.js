// https://github.com/michael-ciniawsky/postcss-load-config

module.exports = {
    "plugins": {
        "postcss-import": {},
        "postcss-url": {},
        "autoprefixer": {
            browsers: ['last 30 versions', "> 2%", "Firefox >= 10", "ie 6-11"]
        }
    }
}
