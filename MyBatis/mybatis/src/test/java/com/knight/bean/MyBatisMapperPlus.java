package com.knight.bean;

import com.knight.dao.KeyDao;
import com.knight.dao.LockDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@DisplayName("4、MyBatis映射文件研究--联合查询")
public class MyBatisMapperPlus {

    SqlSessionFactory sqlSessionFactory;


    @BeforeEach
    void initSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }


    @DisplayName("查询结果中带对象")
    @Test
    void test1() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            KeyDao mapper = session.getMapper(KeyDao.class);
            Key key = mapper.getKeyById(2);
            System.out.println(key);
        } finally {
            session.close();
        }
    }


    @DisplayName("查询结果中带集合")
    @Test
    void test2() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            LockDao mapper = session.getMapper(LockDao.class);
            Lock lock = mapper.getLcckById(3);
            List<Key> keys = lock.getKeys();
            System.out.println(lock);
            for (Key key : keys) {
                System.out.println(key);
            }
        } finally {
            session.close();
        }
    }
}
