package com.knight.boot.controller;

import com.knight.hello.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author TortoiseKnightB
 * @date 2022/08/10
 */
@RestController
public class HelloController {


    @Autowired
    HelloService helloService;

    @GetMapping("/hello")
    public String sayHello() {
        String s = helloService.sayHello("Ben");
        return s;
    }


}
