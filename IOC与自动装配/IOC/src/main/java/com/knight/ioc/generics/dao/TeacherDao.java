package com.knight.ioc.generics.dao;

import com.knight.ioc.generics.bean.Teacher;
import org.springframework.stereotype.Repository;

@Repository
public class TeacherDao extends BaseDao<Teacher>{

    @Override
    public void work() {
        System.out.println("Teacher teaching...");
    }
}
