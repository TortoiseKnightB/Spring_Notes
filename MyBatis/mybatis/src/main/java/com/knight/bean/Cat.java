package com.knight.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
// 开启二级缓存需要实现序列化Serializable接口
public class Cat implements Serializable {

    private Integer catId;
    private String name;
    private Integer gender;
    private Integer age;

}
