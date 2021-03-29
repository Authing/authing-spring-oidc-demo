### 概述
普通vue前端工程。调用接口时候需要设置http header的withCredentials为true，
从而跨域访问时候可以传递cookie给后端，如：
```
 axios.get('http://localhost:8050/resource'
 , {withCredentials:true})
    .then((data ) => {
        alert("返回值："+JSON.stringify(data))
})
```

### 运行

```
npm install

npm run serve
```

### 开发过程
1. 使用vue-cli3创建工程：vue create vue-front-end
1. 安装依赖包：
    ```
    npm install vue-router
    npm install axios
    ```
1. main.js添加依赖
    ```
    import router from './router'
    ```
    并且初始化:
    ```
    new Vue({
      router,
      ...
    ```
1. 新建vue.config.js
1. 增加环境变量配置.env
1. 增加路由规则：router/index.js
1. HelloWorld修改：
    * 增加登录按钮
    * 增加查询当前登录用户按钮
    * 增加调用get类型接口按钮
    * 增加调用post类型接口按钮
    * 增加退出登录按钮
