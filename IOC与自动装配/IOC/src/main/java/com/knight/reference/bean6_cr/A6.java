package com.knight.reference.bean6_cr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class A6 {

    @Autowired
    B6 b;

    public A6() {
        System.out.println("###...A6 实例化");
    }

    @PostConstruct
    public void init(){
        System.out.println("###...A6 开始初始化");
    }

    public void say(){
        System.out.println("###...A6 say hello");
    }



}