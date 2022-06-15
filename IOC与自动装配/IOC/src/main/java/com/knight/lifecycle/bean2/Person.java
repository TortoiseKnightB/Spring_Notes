package com.knight.lifecycle.bean2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Person {

    @Autowired
    User user;

    public Person() {
        System.out.println("###...Person 被实例化");
    }
}
