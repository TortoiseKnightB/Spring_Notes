package com.knight.reference.bean6_cr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class B6 {

    @Autowired
    A6 a;

    public B6() {
        System.out.println("###...B6 实例化");
    }

    @PostConstruct
    public void init() {
        System.out.println("###...B6 开始初始化");
    }

    public void say(){
        System.out.println("###...B6 say hello");
    }


}