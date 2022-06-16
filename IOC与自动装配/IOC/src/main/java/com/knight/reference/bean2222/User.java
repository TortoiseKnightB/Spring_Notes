package com.knight.reference.bean2222;

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
