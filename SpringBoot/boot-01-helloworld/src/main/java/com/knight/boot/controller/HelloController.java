package com.knight.boot.controller;

import com.knight.boot.bean.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    Car car;

    @RequestMapping("/hello")
    public String home() {
        return "Hello World!" + "你好";
    }

    @RequestMapping("/car")
    public Car car() {
        return car;
    }
}
