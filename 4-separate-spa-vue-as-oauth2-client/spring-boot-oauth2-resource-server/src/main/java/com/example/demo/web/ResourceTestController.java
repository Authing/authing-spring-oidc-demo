package com.example.demo.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ResourceTestController {
    private static final Logger logger = LoggerFactory.getLogger(ResourceTestController.class);

    @GetMapping("/resource")
    public Map resource() {
        Map<String, String> result = new HashMap();
        result.put("name", "test");
        result.put("id", "id");
        return result;
    }
}
