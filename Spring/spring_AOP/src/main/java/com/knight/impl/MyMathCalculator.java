package com.knight.impl;

import com.knight.inter.Calculator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyMathCalculator implements Calculator {

    @Override
    public int add(int i, int j) {
        int res = i + j;
        return res;
    }

    @Override
    public int div(int i, int j) {
        int res = i / j;
        return res;
    }

    /**
     * 事务示例：购买图书
     * <p>
     * 注解 @Transactional 保证对数据库的操作要么全部完成，要么全部回滚
     *
     * @param username 顾客姓名
     * @param bookNo   图书编号
     */
    @Transactional
    public void checkout(String username, String bookNo) {
        // jdbc 减图书库存操作

        // jdbc 减顾客余额操作
    }
}
