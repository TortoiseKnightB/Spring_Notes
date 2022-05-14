package com.knight.bean;

import com.knight.dao.EmployeeDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisCRUDTest {

    SqlSessionFactory sqlSessionFactory;

    @BeforeEach
    void initSqlSessionFactory() throws IOException {
        // 1、根据全局配置文件先创建一个 SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }


    @DisplayName("select测试")
    @Test
    void test1() {

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

    @DisplayName("insert测试/手动提交")
    @Test
    void test2() {

        Employee employee = new Employee(null, "吕布", "lvbu@qq.com", 1);
        // 2、获取和数据库的一次会话；getConnection()；
        SqlSession session = sqlSessionFactory.openSession();
        try {
            //3、使用SqlSession操作数据库，获取到dao接口的实现
            EmployeeDao employeeDao = session.getMapper(EmployeeDao.class);
            //4、调用之前的方法；
            int res = employeeDao.insertEmployee(employee);
            System.out.println(res);
        } finally {
            // 手动提交
            session.commit();
            session.close();
        }
    }

    @DisplayName("update测试/自动提交")
    @Test
    void test3() {
        Employee employee = new Employee(1, "项羽", "xiangyu@qq.com", 1);
        // 2、获取和数据库的一次会话；getConnection()；
        // 设置的会话为自动提交
        SqlSession session = sqlSessionFactory.openSession(true);
        try {
            //3、使用SqlSession操作数据库，获取到dao接口的实现
            EmployeeDao employeeDao = session.getMapper(EmployeeDao.class);
            //4、调用之前的方法；
            int res = employeeDao.updateEmployee(employee);
            System.out.println(res);
        } finally {
            session.close();
        }
    }

    @DisplayName("delete测试")
    @Test
    void test4() {
        // 2、获取和数据库的一次会话；getConnection()；
        SqlSession session = sqlSessionFactory.openSession();
        try {
            //3、使用SqlSession操作数据库，获取到dao接口的实现
            EmployeeDao employeeDao = session.getMapper(EmployeeDao.class);
            //4、调用之前的方法；
            int res = employeeDao.deleteEmployee(8);
            System.out.println(res);
        } finally {
            // 手动提交
            session.commit();
            session.close();
        }
    }
}
