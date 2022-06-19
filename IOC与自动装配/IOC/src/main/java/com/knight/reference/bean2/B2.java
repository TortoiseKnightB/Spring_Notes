package com.knight.reference.bean2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "singleton")
public class B2 {

    private A2 a;

    @Autowired
    public void setA(A2 a) {
        this.a = a;
    }
}
