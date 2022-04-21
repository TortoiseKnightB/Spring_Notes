package com.knight.servlet;

import com.knight.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class BookServlet {

    /**
     * 属性的自动注入
     */
    @Autowired(required = false)
    private BookService bookService;

    public void doGet() {
        System.out.println("BookServlet...启动");
        bookService.save();
    }
}
