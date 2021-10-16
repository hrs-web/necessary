const path = require('path');
module.exports={
    entry:'./src/index.js', // 指定打包的入口文件
    output:{
        path: path.resolve(__dirname, 'dist'),
        filename:'build.js' // 输出的js文件名
    },
    mode: 'development', // 设置mode
    module:{
        rules:[
            {
                test:/\.css$/,// 通过正则表达式匹配所有的.css文件
                use:[
                    'style-loader', // 加载使用顺序，这两个顺序不能乱
                    'css-loader'
                ]
            }
        ]
    }

};