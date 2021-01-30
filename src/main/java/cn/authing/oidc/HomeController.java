package cn.authing.oidc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HomeController {
    @GetMapping("/")
    public String index(Principal user) {
        return "Hello " + user.getName();
    }
}