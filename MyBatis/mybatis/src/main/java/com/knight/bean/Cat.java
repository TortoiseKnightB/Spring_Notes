package com.knight.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cat {

    private Integer catId;
    private String name;
    private Integer gender;
    private Integer age;

}
