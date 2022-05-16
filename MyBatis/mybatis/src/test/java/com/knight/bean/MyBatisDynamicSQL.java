package com.knight.bean;

import com.knight.dao.TeacherDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@DisplayName("5、动态SQL")
public class MyBatisDynamicSQL {

    SqlSessionFactory sqlSessionFactory;


    @BeforeEach
    void initSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }


    @DisplayName("简单测试")
    @Test
    void test1() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            TeacherDao mapper = session.getMapper(TeacherDao.class);
            Teacher teacher = mapper.getTeacherById(1);
            System.out.println(teacher);
        } finally {
            session.close();
        }
    }

    @DisplayName("trim、if 标签测试")
    @Test
    void test2() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            TeacherDao mapper = session.getMapper(TeacherDao.class);
            Teacher teacher = new Teacher();
            teacher.setId(1);
            teacher.setName("%t%");
            teacher.setBirth(new Date());
            List<Teacher> teacherList = mapper.getTeacherByCondition(teacher);
            System.out.println(teacherList);
            for (Teacher t : teacherList) {
                System.out.println(t);
            }
        } finally {
            session.close();
        }
    }

    @DisplayName("foreach 标签测试")
    @Test
    void test3() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            TeacherDao mapper = session.getMapper(TeacherDao.class);
            List<Teacher> teacherList = mapper.getTeacherByIdIn(Arrays.asList(1, 2));
            System.out.println(teacherList);
            for (Teacher t : teacherList) {
                System.out.println(t);
            }
        } finally {
            session.close();
        }
    }

    @DisplayName("choose 标签测试")
    @Test
    void test4() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            TeacherDao mapper = session.getMapper(TeacherDao.class);
            Teacher teacher = new Teacher();
            teacher.setId(1);
            teacher.setName("t3");
            teacher.setBirth(new Date());
            List<Teacher> teacherList = mapper.getTeacherByConditionChoose(teacher);
            System.out.println(teacherList);
            for (Teacher t : teacherList) {
                System.out.println(t);
            }
        } finally {
            session.close();
        }
    }


    @DisplayName("set + if 标签测试")
    @Test
    void test5() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            TeacherDao mapper = session.getMapper(TeacherDao.class);
            Teacher teacher = new Teacher();
            teacher.setId(1);
            teacher.setName("老师1");
            teacher.setCourse("体育");
            teacher.setAddress("徐家汇");
            teacher.setBirth(new Date());
            int i = mapper.updateTeacher(teacher);
            System.out.println(i);
            session.commit();
        } finally {
            session.close();
        }
    }


}
