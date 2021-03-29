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
    npm install qs
    npm install js-base64
    npm install js-cookie
    ```
1. main.js添加依赖
    ```
    import router from './router'
    import './router/routerHook'
    ```
    并且初始化:
    ```
    new Vue({
      router,
      ...
    ```
1. 新建vue.config.js
1. 增加utils/oAuth2Login.js
1. 增加环境变量配置.env
1. 增加路由规则：router/index.js、router/routerHook.js
1. 增加OAuth2Login.vue
1. HelloWorld修改：
    * 增加登录按钮
    * 增加获取token按钮
    * 增加查询用户按钮
    * 增加调用资源服务接口按钮
    * 增加退出登录按钮

### 演示自动跳转登录
登录网站A以后访问网站B，希望B网站不再显示登录按钮，
也就是B网站需要自动触发登录逻辑。代码示例，在HelloWorld.vue中增加以下代码：

```
created(){
    this.handleLogin()
},
```

加载页面后就自动触发登录。