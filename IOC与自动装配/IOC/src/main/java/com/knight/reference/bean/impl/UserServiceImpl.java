package com.knight.reference.bean.impl;

import com.knight.reference.bean.UserDao;
import com.knight.reference.bean.UserService;
import com.knight.reference.bean.UserZao;
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
