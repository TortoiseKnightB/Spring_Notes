package com.knight.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan(basePackages = "com.knight.boot")
@SpringBootApplication
public class Boot05Web01Application {

    public static void main(String[] args) {
        SpringApplication.run(Boot05Web01Application.class, args);
    }
}
