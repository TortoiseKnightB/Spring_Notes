package com.knight.reference.bean3;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class B3 {

    private A3 a;

    @Autowired
    public void setA(A3 a) {
        this.a = a;
    }
}
