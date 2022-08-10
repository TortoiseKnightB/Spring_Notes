package com.knight.hello.auto;

import com.knight.hello.bean.HelloProperties;
import com.knight.hello.service.HelloService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author TortoiseKnightB
 * @date 2022/08/10
 */
@Configuration
@EnableConfigurationProperties(HelloProperties.class)       // 默认HelloProperties放在容器中
public class HelloServiceAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(HelloService.class)
    public HelloService helloService() {
        HelloService helloService = new HelloService();
        return helloService;
    }
}
