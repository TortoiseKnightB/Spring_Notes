package com.knight.bean;

import com.knight.dao.EmployeeDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

@DisplayName("1、MyBatis连接测试")
public class MyBatisTest {

    @DisplayName("程序运行测试")
    @Test
    void hello() {
        Employee.test();
    }

    @DisplayName("MyBatis连接测试")
    @Test
    void test() throws IOException {

        // 1、根据全局配置文件先创建一个 SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        Employee employee;
        // 2、获取和数据库的一次会话；getConnection()；
        SqlSession session = sqlSessionFactory.openSession();
        try {
            //3、使用SqlSession操作数据库，获取到dao接口的实现
            EmployeeDao employeeDao = session.getMapper(EmployeeDao.class);
            //4、调用之前的方法；
            employee = employeeDao.getEmpById(1);
        } finally {
            session.close();
        }
        System.out.println(employee);
    }

}