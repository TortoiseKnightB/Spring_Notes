package com.knight.lifecycle.bean.impl;

import com.knight.lifecycle.bean.UserDao;
import com.knight.lifecycle.bean.UserZao;
import com.knight.lifecycle.bean.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    UserZao userZao;

    @PostConstruct
    public void init() {
        System.out.println("###...UserServiceImpl 初始化完成");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("###...UserServiceImpl 被销毁");
    }

    public UserServiceImpl() {
        System.out.println("###...UserServiceImpl 创建完成");
    }

}
