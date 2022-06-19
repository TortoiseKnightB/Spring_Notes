package com.knight.reference.bean4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope(value = "prototype")
public class B4 {

    @Autowired
    A4 a;
}
