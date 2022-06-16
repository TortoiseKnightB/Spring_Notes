package com.knight.reference.bean1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class B1 {

    private A1 a;

    @Autowired
    public B1(A1 a) {
        this.a = a;
    }
}
