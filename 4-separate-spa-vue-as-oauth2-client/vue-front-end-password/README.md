### 运行

```
npm install

npm run serve
```


### 开发过程
1. 使用vue-cli3创建工程：vue create vue-front-end-password
1. 安装依赖包：
    ```
    npm install axios
    npm install js-cookie
    npm install qs
    ```
1. 新建vue.config.js
1. 增加utils/oAuth2PwdLogin.js
1. 增加环境变量配置.env
1. HelloWorld修改：
    * 增加用户名密码登录的input表单
    * 增加获取token按钮
    * 增加查询用户按钮
    * 增加调用资源服务接口按钮
    * 增加退出登录按钮


### 其他
使用curl测试用户名密码模式获取token：
```
curl -i -X POST -d "username=admin&password=123456&grant_type=password&client_id=tdfuivue&client_secret=tdfuivue" http://192.168.99.77:9998/oauth/token

curl -i -X POST -d "username=admin&password=admin&grant_type=password&client_id=client-for-server&client_secret=client-for-server" http://localhost:9999/oauth/token

curl -i -X POST -d "username=admin&password=123456&grant_type=password&client_id=swagger&client_secret=swagger" http://192.168.70.40:8769/oauth/token

curl -i -X POST -d "username=admin&password=123456&grant_type=password&client_id=tdf-cloud-gateway-zuul&client_secret=tdf-cloud-gateway-zuul" http://192.168.70.40:8769/oauth/token

curl -i -X POST -d "username=admin&password=123456&grant_type=password&client_id=tdf-cloud-ui-vue&client_secret=tdf-cloud-ui-vue" http://192.168.70.40:8769/oauth/token

```
