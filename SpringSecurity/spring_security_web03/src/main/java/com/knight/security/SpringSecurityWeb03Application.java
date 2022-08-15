package com.knight.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @author TortoiseKnightB
 * @date 2022/08/13
 */
@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SpringSecurityWeb03Application {
    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityWeb03Application.class);
    }
}
