package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 1. 定义加密器积木
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2. 配置安全过滤链（放行注册接口）
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // 关闭跨站请求伪造防护，方便测试
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/register").permitAll() // 允许任何人访问 /register
                        .anyRequest().authenticated() // 其他请求需要登录
                )
                .httpBasic(Customizer.withDefaults()); // 使用默认配置

        return http.build();
    }
}