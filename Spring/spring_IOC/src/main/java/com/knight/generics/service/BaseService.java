package com.knight.generics.service;

import com.knight.generics.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseService<T> {

    @Autowired
    private BaseDao<T> baseDao;

    public void save() {
        System.out.println("###......baseDao = " + baseDao);
        baseDao.save();
    }
}
