### 概述
OAuth2 Client示例集合:
1. 1-simple：最简示例，包含[入门教程](1-simple/README.md)
1. 2-spring-boot-oauth2-client: 稍复杂示例，演示普通单体Spring Boot工程作为OAuth2 Client
1. 3-separate-spa-spring-boot-as-oauth2-client: 前后端分离架构下的统一认证，前后端共享后端cookie跨域访问的方式

当前示例中所有spring boot版本为spring boot 2.3.6。vue项目使用vue-cli 3工程结构。  

所有Vue前端示例使用了直接调用Authing标准OIDC接口的方式。

//TODO 
1. authing单点登录的实现方式测试
1. 4-separate-spa-vue-as-oauth2-client: 前后端分离架构下的统一认证示例——vue前端独立作为OAuth2 Client,Spring Boot后端作为OAuth2 Resource Server
1. vue各种登录模式测试
    1. 无后端场景：https://docs.authing.cn/v2/guides/asics/authenticate-first-user/use-hosted-login-page.html
    1. 
1. 前后端分离方式