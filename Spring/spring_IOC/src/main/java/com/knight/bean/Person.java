package com.knight.bean;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Properties;

@Data
public class Person {

    private String lastName;

    private Integer age;

    private String gender;

    private String email;

    private Car car;

    private List<Book> books;

    private Map<String, Object> maps;

    private Properties properties;


    Person() {
        System.out.println("无参构造器，被调用!!!");
    }

    public Person(String lastName, Integer age, String gender, String email) {
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.email = email;
        System.out.println("有参构造器，被调用！！！");
    }

    public Person(Car car) {
        this.car = car;
    }
}
