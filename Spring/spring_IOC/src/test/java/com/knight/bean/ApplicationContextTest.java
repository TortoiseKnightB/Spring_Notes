package com.knight.bean;

import com.knight.generics.service.BirdService;
import com.knight.generics.service.PlaneService;
import com.knight.servlet.BookServlet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextTest {

    ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");

    /**
     * 1、组件id:默认为类名首字母小写；自定义：@Service("BookService")
     * <p>
     * 2、组件作用域：默认为单例；自定义：@Scope(value = "prototype")
     */
    @DisplayName("4个注解注入")
    @Test
    public void test1() {
        Object bookDao = ioc.getBean("bookDao");
        Object bookDao2 = ioc.getBean("bookDao");
        Object bookService = ioc.getBean("BookService");
        System.out.println(bookDao == bookDao2);
        System.out.println(bookService);
    }

    /**
     * 原理：@Autowired
     * <p>
     * 1、先按照类型去容器中找到对应组件；bookService = ioc.getBean(BookService.class);
     * <p>
     * 若没找到，则抛异常；
     * <p>
     * 若找到多个，则按照变量名作为id继续匹配，若仍没匹配上则报错
     * <p>
     * 用 @Qualifier 可以指定id
     * <p>
     * 其他：@Autowired(required = false)，找不到会返回null
     */
    @DisplayName("@Autowire自动装配")
    @Test
    public void test2() {
        BookServlet bookServlet = ioc.getBean(BookServlet.class);
        bookServlet.doGet();
    }

    /**
     * 通过带泛型的父类类型确定注入的子类
     */
    @DisplayName("泛型依赖注入")
    @Test
    public void test3() {
        BirdService birdService = ioc.getBean(BirdService.class);
        PlaneService planeService = ioc.getBean(PlaneService.class);

        birdService.save();
        planeService.save();

        // 带泛型的父类类型
        System.out.println(birdService.getClass().getGenericSuperclass());
    }
}
