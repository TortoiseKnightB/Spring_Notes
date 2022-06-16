package com.knight.reference.bean3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class A3 {
    private B3 b;

    @Autowired
    public void setB(B3 b) {
        this.b = b;
    }
}
