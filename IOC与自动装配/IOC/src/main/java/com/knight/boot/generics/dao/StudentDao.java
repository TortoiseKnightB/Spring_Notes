package com.knight.boot.generics.dao;

import com.knight.boot.generics.bean.Student;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDao extends BaseDao<Student> {

    @Override
    public void work() {
        System.out.println("Student doing homework...");
    }
}
