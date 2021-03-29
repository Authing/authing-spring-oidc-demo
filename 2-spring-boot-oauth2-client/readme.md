## 简介
单体架构接入 Authing 的示例

## 环境
JDK1.8+

## 运行
1. mvn spring-boot:run -e
1. 浏览器 http://localhost:8082/coupon/list 登录后展示内容

* 单点登录演示操作：
    1. 模拟另一个应用启动： `mvn spring-boot:run  -Dspring-boot.run.jvmArguments="-Dspring.profiles.active=sso"`
    1. 浏览器输入 http://localhost:8081/coupon/list 此时不用再输入用户名密码
