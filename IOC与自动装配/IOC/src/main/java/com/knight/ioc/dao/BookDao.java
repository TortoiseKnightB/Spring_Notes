package com.knight.ioc.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository(value = "BookDao")
@Scope(value = "singleton")
public class BookDao {

    @Value(value = "《Spring揭秘》")
    private String bookName;

    public void getBook() {
        System.out.println("获取Book：" + this.bookName);
    }
}
