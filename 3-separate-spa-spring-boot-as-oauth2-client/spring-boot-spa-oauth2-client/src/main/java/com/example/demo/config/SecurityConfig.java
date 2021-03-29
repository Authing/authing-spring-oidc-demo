package com.example.demo.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/error").permitAll()
                .anyRequest().authenticated()
                //支持OAuth2登录
                .and().oauth2Login();
        //支持跨域访问
        http.cors();
        //为演示，临时禁用csrf
        http.csrf().disable();
    }
}
