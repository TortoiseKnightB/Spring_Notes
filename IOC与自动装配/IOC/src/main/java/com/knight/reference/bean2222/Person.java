package com.knight.reference.bean2222;

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
