package com.knight.bean;


import com.knight.servlet.BookServlet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * spring单元测试
 * <p>
 * 用@ContextConfiguration(locations = "")来指定Spring配置文件的位置
 * <p>
 * 用@RunWith指定用哪种驱动进行单元测试，默认JUnit（好处：不用ioc.getBean()获取组件，直接@Autowired自动装配）
 */
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringTest {

    @Autowired
    BookServlet bookServlet;

    /**
     * 此处用JUnit4，而非JUnit5
     */
    @Test
    public void test1() {
        bookServlet.doGet();
    }
}
