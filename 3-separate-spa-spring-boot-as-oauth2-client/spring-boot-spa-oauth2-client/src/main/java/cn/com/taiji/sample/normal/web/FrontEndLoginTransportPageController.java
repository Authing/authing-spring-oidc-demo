package com.example.sample.normal.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class FrontEndLoginTransportPageController {
    private static final Logger logger = LoggerFactory.getLogger(FrontEndLoginTransportPageController.class);

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
}
