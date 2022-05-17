package com.knight.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope(value = "prototype")
public class BookDao {

    public void save(){
        System.out.println("图书已经保存...");
    }
}
