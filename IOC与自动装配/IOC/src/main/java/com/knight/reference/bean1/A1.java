package com.knight.reference.bean1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class A1 {

    private B1 b;

    @Autowired
    public A1(B1 b) {
        this.b = b;
    }
}
