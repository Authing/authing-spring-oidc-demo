package com.example.demo.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity(debug = true)
@Order(1)
public class SecurityOAuth2Config extends WebSecurityConfigurerAdapter {
    private static Logger logger = LoggerFactory.getLogger(SecurityOAuth2Config.class);

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
                .antMatchers("/oauth2login")
                .antMatchers("/login/**")
                .and().authorizeRequests()
                .anyRequest().authenticated()
                //支持OAuth2登录
                .and().oauth2Login();

        //支持跨域访问
        http.cors();
        //为演示，临时禁用csrf
        http.csrf().disable();
    }
}
