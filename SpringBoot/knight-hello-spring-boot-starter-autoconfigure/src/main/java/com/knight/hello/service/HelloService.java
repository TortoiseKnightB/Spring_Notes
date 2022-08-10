package com.knight.hello.service;

import com.knight.hello.bean.HelloProperties;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 默认不要放在容器中
 *
 * @author TortoiseKnightB
 * @date 2022/08/10
 */
public class HelloService {

    @Autowired
    HelloProperties helloProperties;

    public String sayHello(String userName) {
        return helloProperties.getPrefix() + ": " + userName + ">> " + helloProperties.getSuffix();
    }
}
