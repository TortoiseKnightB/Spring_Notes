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

@DisplayName("3、MyBatis映射文件研究")
public class MyBatisMapper {

    SqlSessionFactory sqlSessionFactory;


    @BeforeEach
    void initSqlSessionFactory() throws IOException {
        // 1、根据全局配置文件先创建一个 SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }


    @DisplayName("自增ID测试")
    @Test
    void test1() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            EmployeeDao mapper = session.getMapper(EmployeeDao.class);
            Employee employee = new Employee(null, "刘邦", "liubang@qq.com", 1);
            int i = mapper.insertEmployee(employee);
            System.out.println(i);
            System.out.println("插入后自增的id：" + employee.getId());
        } finally {
            session.commit();
            session.close();
        }
    }

    // 多参数时推荐使用@Param("keyName")，更多请看pdf
    // 本质还是封装成map，key为@Param()中的值
    @DisplayName("参数的各种取值测试")
    @Test
    void test2() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            EmployeeDao mapper = session.getMapper(EmployeeDao.class);
            Employee emp = mapper.getEmpByIdAndName(1, "项羽");
            System.out.println(emp);
        } finally {
            session.commit();
            session.close();
        }
    }

    @DisplayName("#{}、${}区别")
    @Test
    void test3() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            EmployeeDao mapper = session.getMapper(EmployeeDao.class);
            Employee emp = mapper.getEmpByIdAndNameAndTable(1, "项羽","t_employee");
            System.out.println(emp);
        } finally {
            session.commit();
            session.close();
        }
    }
}
