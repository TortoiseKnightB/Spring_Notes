package com.knight.boot.controller;

import com.knight.boot.bean.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class ResponseTestController {

    // http://localhost:8080/test/person
    @ResponseBody
    @GetMapping("/test/person")
    public Person getPerson() {
        Person person = new Person();
        person.setUsername("李四");
        person.setAge(28);
        person.setBirth(new Date());
        return person;
    }
}
