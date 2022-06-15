package com.knight.lifecycle.bean2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class User {

    @Autowired
    Person person;

    public User() {
        System.out.println("###...User 被实例化");
    }
}
