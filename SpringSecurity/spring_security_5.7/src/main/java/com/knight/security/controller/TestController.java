package com.knight.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author TortoiseKnightB
 * @date 2022/08/13
 */
@RestController
@RequestMapping("/test")
public class TestController {


    /**
     * 不需要登录
     *
     * @return
     */
    @GetMapping("/hello")
    public String hello() {
        return "hello security";
    }

    /**
     * 仅登录，不需要任何权限
     *
     * @return
     */
    @GetMapping("/world")
    public String world() {
        return "the world!!!";
    }

    /**
     * 需登录，并具有admin权限
     *
     * @return
     */
    @GetMapping("/index")
    public String index() {
        return "hello index";
    }

    /**
     * 需登录，并具有adminPlus权限
     *
     * @return
     */
    @GetMapping("/indexPlus")
    public String indexPlus() {
        return "hello indexPlus";
    }

}
