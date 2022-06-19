package com.knight.reference.bean2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "singleton")
public class A2 {

    private B2 b;

    @Autowired
    public void setB(B2 b) {
        this.b = b;
    }
}
