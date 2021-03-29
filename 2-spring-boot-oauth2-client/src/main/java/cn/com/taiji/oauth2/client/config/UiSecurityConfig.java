package com.example.oauth2.client.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity(debug = true)
public class UiSecurityConfig extends WebSecurityConfigurerAdapter {

    //使用slf4j记录日志,日志属性名logger
    private static final Logger logger = LoggerFactory.getLogger(UiSecurityConfig.class);

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/login**").permitAll()
                .anyRequest()
                .authenticated();
        http.oauth2Login();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 解决静态资源被拦截的问题
        web.ignoring().antMatchers("/theme/**", "/js/**", "/css/**", "/images/**", "**/favicon.ico");
    }
}
