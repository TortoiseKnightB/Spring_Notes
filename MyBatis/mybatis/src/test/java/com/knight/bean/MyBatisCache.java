package com.knight.bean;

import com.knight.dao.CatDao;
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


    /**
     * 一级缓存(local cache), 即本地缓存, 作用域默认为SqlSession
     * <p>
     * org.apache.ibatis.cache.impl.PerpetualCache
     */
    // 一级缓存：MyBatis：SqlSesion级别的缓存；默认存在；本地缓存不能被关闭
    //机制：只要之前查询过的数据，mybatis就会保存在一个缓存中（Map）；下次获取直接从缓存中拿；
    @DisplayName("一级缓存验证")
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


    @DisplayName("缓存失效——不同的SqlSession对应不同的一级缓存")
    @Test
    void test2() {
        // 第一个会话
        SqlSession session = sqlSessionFactory.openSession();
        TeacherDao mapper = session.getMapper(TeacherDao.class);
        Teacher teacher1 = mapper.getTeacherById(1);
        session.close();

        // 第二个会话
        SqlSession session2 = sqlSessionFactory.openSession();
        TeacherDao mapper2 = session2.getMapper(TeacherDao.class);
        Teacher teacher2 = mapper2.getTeacherById(1);
        session2.close();

        System.out.println(teacher1 == teacher2);
    }


    // 增删改操作会清空缓存
    @DisplayName("缓存失效——同一个SqlSession两次查询期间执行了任何一次增删改操作")
    @Test
    void test3() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            TeacherDao mapper = session.getMapper(TeacherDao.class);
            Teacher teacher1 = mapper.getTeacherById(1);

            // 执行了任何一次增删改操作
            Teacher teacher3 = new Teacher();
            teacher3.setId(3);
            teacher3.setName("老师3");
            mapper.updateTeacher(teacher3);

            Teacher teacher2 = mapper.getTeacherById(1);
            System.out.println(teacher1 == teacher2);
        } finally {
            session.commit();
            session.close();
        }
    }


    @DisplayName("缓存失效——两次查询期间手动清空了缓存")
    @Test
    void test4() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            TeacherDao mapper = session.getMapper(TeacherDao.class);
            Teacher teacher1 = mapper.getTeacherById(1);

            // 或者手动清空缓存
            session.clearCache();

            Teacher teacher2 = mapper.getTeacherById(1);
            System.out.println(teacher1 == teacher2);
        } finally {
            session.commit();
            session.close();
        }
    }


    /**
     * 二级缓存(second level cache)，全局作用域缓存，namespace级别的缓存
     * <p>
     * 二级缓存默认不开启，需要手动配置
     * <p>
     * 二级缓存在 SqlSession 关闭或提交之后才会生效
     * <p>
     * **使用步骤**：
     * <p>
     * 1、全局配置文件中开启二级缓存：{@code <setting name="cacheEnabled" value="true"/>}
     * <p>
     * 2、需要使用二级缓存的映射文件处使用cache配置缓存：{@code <cache></cache>}
     * <p>
     * 3、POJO需要实现Serializable接口
     * <p>
     * **查找顺序为**：
     * <p>
     * 二级缓存–>一级缓存–>数据库
     * <p>
     * 1、如果二级缓存关闭，直接判断一级缓存是否有数据，如果没有就查数据库
     * <p>
     * 2、如果二级缓存开启，先判断二级缓存有没有数据，如果有就直接返回；如果没有，就查询一级缓存，如果有就返回，没有就查询数据库
     */
    @DisplayName("二级缓存验证")
    @Test
    void test5() {
        // 第一个会话
        SqlSession session1 = sqlSessionFactory.openSession();
        CatDao mapper1 = session1.getMapper(CatDao.class);
        Cat cat1 = mapper1.getCatById(1);
        session1.close();

        // 第二个会话
        SqlSession session2 = sqlSessionFactory.openSession();
        CatDao mapper2 = session2.getMapper(CatDao.class);
        Cat cat2 = mapper2.getCatById(1);
        session2.close();

        System.out.println(cat1 == cat2);
    }


    @DisplayName("整合第三方缓存")
    @Test
    void test6() {
        System.out.println("不会写，先跳过");
    }


}
