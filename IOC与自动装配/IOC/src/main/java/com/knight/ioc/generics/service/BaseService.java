package com.knight.ioc.generics.service;

import com.knight.ioc.generics.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;


public class BaseService<T> {

    @Autowired
    private BaseDao<T> baseDao;

    public void work() {
        System.out.println(baseDao);
        baseDao.work();
    }
}
