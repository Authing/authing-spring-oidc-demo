### 概述
前后端分离架构下的统一认证示例——vue前端独立作为OAuth2 Client的方式

* **vue-front-end**: vue前端作为OAuth2 Client，使用OAuth2的 **授权码模式**，OAuth2客户端不需要获取用户密码
* **spring-boot-oauth2-resource-server**: Spring Boot后端作为OAuth2 Resource Server

### 运行
##### 演示普通OAuth2授权码模式统一登录
1. 启动vue-front-end(启动方式参考：[vue-front-end的readme](vue-front-end/README.md)）
2. 启动spring-boot-oauth2-resource-server
3. 浏览器访问：http://localhost:9527/


