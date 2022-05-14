package com.knight.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private Integer id;
    private String empName;
    private String email;
    private Integer gender;

    public static void test(){
        System.out.println("hello world");
    }

}
