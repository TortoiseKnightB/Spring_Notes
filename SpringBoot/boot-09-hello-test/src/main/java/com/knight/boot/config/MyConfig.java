package com.knight.boot.config;

import com.knight.hello.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author TortoiseKnightB
 * @date 2022/08/11
 */
@Configuration
public class MyConfig {

    // 自定义HelloService，使HelloServiceAutoConfiguration中的HelloService失效
    @Bean
    public HelloService helloService() {
        HelloService helloService = new HelloService();
        return helloService;
    }
}
