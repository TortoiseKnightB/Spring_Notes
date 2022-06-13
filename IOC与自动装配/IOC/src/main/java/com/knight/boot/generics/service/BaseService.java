package com.knight.boot.generics.service;

import com.knight.boot.generics.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;


public class BaseService<T> {

    @Autowired
    private BaseDao<T> baseDao;

    public void work() {
        System.out.println(baseDao);
        baseDao.work();
    }
}
