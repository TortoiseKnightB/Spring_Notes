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

@DisplayName("6、MyBatis缓存")
public class MyBatisCache {

    SqlSessionFactory sqlSessionFactory;

    @BeforeEach
    void initSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }


    @DisplayName("一级缓存体验")
    @Test
    void test1() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            TeacherDao mapper = session.getMapper(TeacherDao.class);
            Teacher teacher1 = mapper.getTeacherById(1);
            Teacher teacher2 = mapper.getTeacherById(1);
            System.out.println(teacher1 == teacher2);
        } finally {
            session.close();
        }
    }
}
