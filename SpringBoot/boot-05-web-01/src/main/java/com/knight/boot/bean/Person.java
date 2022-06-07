package com.knight.boot.bean;

import lombok.Data;

import java.util.Date;

@Data
public class Person {

    private String username;
    private Integer age;
    private Date birth;
    private Pet pet;

}
