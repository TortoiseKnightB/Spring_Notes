package com.knight.lifecycle.bean.impl;

import com.knight.lifecycle.bean.UserDao;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    public UserDaoImpl() {
        System.out.println("###...UserDaoImpl 创建完成");
    }

    @Override
    public void addDao() {
        System.out.println("###...addDao()");
    }

    @Override
    public void deleteDao() {
        System.out.println("###...deleteDao()");
    }

    @Override
    public void updateDao() {
        System.out.println("###...updateDao()");
    }

    @Override
    public void queryDao() {
        System.out.println("###...queryDao()");
    }
}
