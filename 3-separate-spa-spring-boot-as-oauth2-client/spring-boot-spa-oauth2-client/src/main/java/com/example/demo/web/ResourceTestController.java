package com.example.demo.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
public class ResourceTestController {
    private static final Logger logger = LoggerFactory.getLogger(ResourceTestController.class);

    //模拟获取当前登录用户
    @GetMapping("/currentUser")
    public Map currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Map<String, Object> result = new HashMap();
        result.put("user", authentication.getPrincipal());
        return result;
    }

    //模拟get
    @GetMapping("/resource")
    public Map resource() {
        Map<String, String> result = new HashMap();
        result.put("name", "test");
        result.put("id", "id");
        return result;
    }

    //模拟post
    @PostMapping("/resource")
    public Map add(@RequestBody Map data) {
        logger.info("收到参数：",data);
        return data;
    }
}
