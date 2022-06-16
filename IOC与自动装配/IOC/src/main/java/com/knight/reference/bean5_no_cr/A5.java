package com.knight.reference.bean5_no_cr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class A5 {

    @Autowired
    B5 b;

    public A5() {
        System.out.println("###...A5 实例化");
    }
}
