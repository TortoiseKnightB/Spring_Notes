package com.knight.ioc.controller;

import com.knight.ioc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class BookController {

    @Autowired
    BookService bookService;

    public void getBook() {
        bookService.getBook();
    }
}
