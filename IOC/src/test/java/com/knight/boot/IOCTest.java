package com.knight.boot;


import com.knight.boot.bean.Car;
import com.knight.boot.bean.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@DisplayName("IOC原理入门")
public class IOCTest {

    @DisplayName("手动创建资源")
    @Test
    public void test01() {
        Car car = new Car(300000.0, "Blue");
        Person person = new Person("张三", 20, car);
        System.out.println(person);
    }


    @DisplayName("从IOC容器中获取资源")
    @Test
    public void test02() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("ioc.xml");
        Object person = ioc.getBean("myPerson");
        System.out.println(person);
    }


    @DisplayName("容器中的同一组件默认为单实例")
    @Test
    public void test03() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("ioc.xml");
        Object person1 = ioc.getBean("myPerson");
        Object person2 = ioc.getBean("myPerson");
        System.out.println("person1 与 person2 是否为同一个对象：" + (person1 == person2));
    }

}