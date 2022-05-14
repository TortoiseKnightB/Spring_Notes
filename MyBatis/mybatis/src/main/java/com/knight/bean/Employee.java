package com.knight.bean;

import lombok.Data;

@Data
public class Employee {

    private Integer id;
    private String empName;
    private String email;
    private Integer gender;

    public static void test(){
        System.out.println("hello world");
    }

}
