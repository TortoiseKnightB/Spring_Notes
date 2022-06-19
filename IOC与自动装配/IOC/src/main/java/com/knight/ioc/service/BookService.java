package com.knight.ioc.service;

import com.knight.ioc.dao.BookDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BookService {

    @Resource
    @Qualifier(value = "BookDao")
    BookDao bookDao;

    public void getBook() {
        bookDao.getBook();
    }
}
