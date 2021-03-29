### 概述
Authing的Spring OIDC/OAuth2 Client示例集合:
1. spring-boot-oauth2-client: 演示普通单体spring boot 2工程作为OAuth2 Client
1. separate-spa-vue-as-oauth2-client: 演示前后端分离架构下的统一认证，前端独立作为OAuth2 Client的方式
1. separate-spa-spring-boot-as-oauth2-client: 演示前后端分离架构下的统一认证，前后端共享后端cookie跨域访问的方式

当前示例中所有spring boot版本为spring boot 2.2.x。vue项目使用vue-cli 3工程结构。  

其他Spring Boot版本示例请切换其他分支，如基于Spring Boot 2.1.x的版本切换到2.1.x分支。

### 注意
* 推荐使用separate-spa-vue-as-oauth2-client中的授权码模式示例
* 因为tdf-oauth-server依赖浏览器cookie

### 参考
* https://auth0.com/docs/api-auth/which-oauth-flow-to-use
* front-end作为OAuth2 Client + PKCE保护
* Token replay的问题，实现防重放
* OAuth2安全问题：https://tools.ietf.org/html/draft-ietf-oauth-security-topics-09#section-2.1.2
* [IS A SPA LESS SECURE THAN A SERVER RENDERED WEB APPLICATION?](https://damienbod.com/2019/01/20/is-a-spa-less-secure-than-a-server-rendered-web-application/)
* Single-page apps (or browser-based apps) 前后端分离架构: https://aaronparecki.com/oauth-2-simplified/#single-page-apps
* web server apps 单体服务架构: https://aaronparecki.com/oauth-2-simplified/#web-server-apps
