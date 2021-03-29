## 简介
TDF-oauth-client是TDF-oauth-server项目的客户端演示代码，作为Oauth2的client。
TDF-oauth-server是Oauth2的认证服务器(Authentication Server)，同时也是资源服务器

## 环境
JDK1.8+

## 运行
1. mvn spring-boot:run -e
2. 模拟另一个应用启动：mvn spring-boot:run -e -Dspring.profiles.active=sso

* 单点登录演示操作：
 1. 浏览器 http://localhost:8082/coupon/list 登录后展示内容
 2. 浏览器输入 http://localhost:8081/coupon/list 此时不用再输入用户名密码
