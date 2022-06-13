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


    @DisplayName("工厂模式——自定义工厂类")
    @Test
    public void test04() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("ioc2.xml");
        Object car01 = ioc.getBean("car01");
        System.out.println("静态工厂：" + car01);
        Object car02 = ioc.getBean("car02");
        System.out.println("实例工厂：" + car02);
    }


    @DisplayName("工厂模式——实现FactoryBean接口")
    @Test
    public void test05() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("ioc2.xml");

        System.out.println("容器启动完成，所有组件如下：");
        String[] beanDefinitionNames2 = ioc.getBeanDefinitionNames();
        for (String bean : beanDefinitionNames2) {
            System.out.println(bean);
        }

        System.out.println("***********************************");
        Object car = ioc.getBean("carFactoryBeanImp");
        System.out.println("实现FactoryBean接口：" + car);
    }

}