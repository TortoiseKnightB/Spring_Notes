package com.knight.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class homeController {

    @Autowired
    JdbcTemplate jdbcTemplate;


    // http://localhost:8080/hello
    @GetMapping("/hello")
    public String helloworld() {
        return "connect success";
    }

    @GetMapping("/sql")
    public String queryFromDB() {
        Long aLong = jdbcTemplate.queryForObject("SELECT count(*) FROM mybatis.t_cat;", Long.class);
        return aLong.toString();
    }

}
