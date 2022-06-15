package com.knight.lifecycle.bean.impl;

import com.knight.lifecycle.bean.UserZao;
import org.springframework.stereotype.Service;

@Service
public class UserZaoImpl implements UserZao {


    public UserZaoImpl() {
        System.out.println("###...创建新的 UserServiceImpl");
        new UserServiceImpl();
        System.out.println("###...UserZaoImpl 创建完成");
    }

    @Override
    public void addUserZao() {

    }
}
