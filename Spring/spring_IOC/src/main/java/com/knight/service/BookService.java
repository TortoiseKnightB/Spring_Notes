package com.knight.service;

import com.knight.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("BookService")
public class BookService {

    @Autowired
    private BookDao bookDao;

    public void save() {
        System.out.println("BookService...启动");
        bookDao.save();
    }
}
