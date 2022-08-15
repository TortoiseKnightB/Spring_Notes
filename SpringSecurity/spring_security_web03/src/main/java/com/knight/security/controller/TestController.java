package com.knight.security.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
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
     * 需登录，并具有admins权限
     *
     * @return
     */
    @GetMapping("/index")
    public String index() {
        return "hello index";
    }

    /**
     * 需登录,并具有admins、manager中任一权限
     *
     * @return
     */
    @GetMapping("/home")
    public String home() {
        return "welcome home";
    }

    /**
     * 需登录,并具有ROLE_sale权限
     *
     * @return
     */
    @GetMapping("/sale")
    public String sale() {
        return "welcome sale";
    }

    /**
     * 需登录,并具有ROLE_sale、ROLE_admin任一权限
     *
     * @return
     */
    @GetMapping("/anySale")
    public String anySale() {
        return "welcome anySale";
    }

    @GetMapping("/update")
    @Secured({"ROLE_sale", "ROLE_manager"})     // 访问该路径，需要具有ROLE_sale、ROLE_manager任一角色
    public String update() {
        return "hello @Secured";
    }

    @GetMapping("/update2")
    @PreAuthorize("hasAnyAuthority('admins')")     // 访问该路径，需要具有admins角色
    public String update2() {
        return "hello @PreAuthorize";
    }

    @GetMapping("/update3")
    @PostAuthorize("hasAnyAuthority('ROLE_admin')")     // 访问该路径，需要具有ROLE_admin角色
    public String update3() {
        System.out.println("### update3......没有权限也会输出，但会进入无权限页面");
        return "hello @PostAuthorize";
    }

}
