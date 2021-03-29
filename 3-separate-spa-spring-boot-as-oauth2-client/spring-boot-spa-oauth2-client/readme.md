### 概述
作为OAuth2 Client，被vue前端跨域访问。

### 启动
运行TdfServiceSampleNormalApplication

### 开发关键步骤
1. 依赖OAuth2 Client包
    ```xml
     <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-oauth2-client</artifactId>
    </dependency>
    ```
1. SecurityConfig配置
1. CorsConfig配置跨域具体规则
1. 增加被vue前端调用的OAuth2登录中转页面接口FrontEndLoginTransportPageController：
    ```java
    @GetMapping("/oauth2login")
    public void transportPage(HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        try {
            response.getWriter().println("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Title</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<h1>供第三方登录应用请求使用</h1>\n" +
                    "<script>\n" +
                    "    window.close();\n" +
                    "</script>\n" +
                    "</body>\n" +
                    "</html>");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }
    ```
1. 
