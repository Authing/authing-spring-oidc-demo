## 简介
OAuth2 Client使用 Client Credentials模式获取token。Client Credentials模式
是应用间互相认证和鉴权，与用户无关。

## 环境
JDK1.8+


## 运行
1. mvn spring-boot:run -e
2. 模拟另一个应用启动：mvn spring-boot:run -e -Dspring.profiles.active=sso

* 单点登录演示操作：
 1. 浏览器 http://localhost:8082/coupon/list 登录后展示内容
 2. 浏览器输入 http://localhost:8081/coupon/list 此时不用再输入用户名密码

### cur测试脚本

```
# 使用Client Credentials模式获取token
curl --location --request POST 'https://zztest1.authing.cn/oidc/token' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'grant_type=client_credentials' \
--data-urlencode 'client_id=6061e247f16c6c39fcc4b35d' \
--data-urlencode 'client_secret=505049220ea0ed908924bec8e0d2a6d1' \
--data-urlencode 'scope=openid,profile'
```
