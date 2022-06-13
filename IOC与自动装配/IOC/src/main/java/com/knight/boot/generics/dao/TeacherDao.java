package com.knight.boot.generics.dao;

import com.knight.boot.generics.bean.Teacher;
import org.springframework.stereotype.Repository;

@Repository
public class TeacherDao extends BaseDao<Teacher>{

    @Override
    public void work() {
        System.out.println("Teacher teaching...");
    }
}
