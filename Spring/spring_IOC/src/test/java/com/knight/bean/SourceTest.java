package com.knight.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ioc 源码测试
 */
public class SourceTest {

    private static ApplicationContext ioc;

    public static void main(String[] args) {
        ioc = new ClassPathXmlApplicationContext("ioc2.xml");
        Object person05 = ioc.getBean("person05");
        System.out.println(person05);
    }
}
