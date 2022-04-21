package com.knight.bean;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IOCTest {
    /**
     * 存在的几个问题；
     * 1）、src，源码包开始的路径，称为类路径的开始；
     *      所有源码包里面的东西都会被合并放在类路径里面；
     *      java：/bin/
     *      web：/WEB-INF/classes/
     * 2）、导包commons-logging-1.1.3.jar（依赖）
     * 3）、先导包再创建配置文件；
     * 4）、Spring的容器接管了标志了s的类；
     */

    /**
     * 几个细节：
     * 1）、ApplicationContext（IOC容器的接口）
     * 2）、给容器中注册一个组件；我们也从容器中按照id拿到了这个组件的对象？
     * 组件的创建工作，是容器完成；
     * Person对象是什么时候创建好了呢？
     * 容器中对象的创建在容器创建完成的时候就已经创建好了；
     * 3）、同一个组件在ioc容器中是单实例的；容器启动完成都已经创建准备好的；
     * 4）、容器中如果没有这个组件，获取组件？报异常
     * org.springframework.beans.factory.NoSuchBeanDefinitionException:
     * No bean named 'person03' is defined
     * 5）、ioc容器在创建这个组件对象的时候，(property)会利用setter方法为javaBean的属性进行赋值；
     * 6）、javaBean的属性名是由什么决定的？getter/setter方法是属性名;set去掉后面那一串首字母小写就是属性名;
     * private String lastName;？
     * 所有getter/setter都自动生成！
     */


    private ApplicationContext ioc = new ClassPathXmlApplicationContext("ioc.xml");
    private ApplicationContext ioc2 = new ClassPathXmlApplicationContext("ioc2.xml");
    private ApplicationContext ioc3 = new ClassPathXmlApplicationContext("ioc3.xml");
    private ApplicationContext ioc4 = new ClassPathXmlApplicationContext("ioc4.xml");


    @DisplayName("从容器中拿到这个组件")
    @Test
    public void test1() {
        //ApplicationContext：代表ioc容器
        //ClassPathXmlApplicationContext:当前应用的xml配置文件在 ClassPath下
        //根据spring的配置文件得到ioc容器对象
        //ApplicationContext ioc = new ClassPathXmlApplicationContext("com/atguigu/bean/ioc.xml");
        //容器帮我们创建好对象了；
        System.out.println("容器启动完成....");
        Person bean = (Person) ioc.getBean("person01");
        Object bean2 = ioc.getBean("person01");
        System.out.println(bean == bean2);

        System.out.println("==================");
//        Object bean3 = ioc.getBean("person03");
    }

    @DisplayName("根据bean的类型获取")
    @Test
    public void test2() {
//        Person bean = ioc.getBean(Person.class);
        Person bean = ioc.getBean("person02", Person.class);
        System.out.println(bean);
    }

    @DisplayName("有参构造器获取")
    @Test
    public void test3() {
        Object bean = ioc.getBean("person03");
        System.out.println(bean);
    }

    @DisplayName("通过p名称空间为bean赋值")
    @Test
    public void test4() {
        Object bean = ioc.getBean("person04");
        System.out.println(bean);
    }

    @DisplayName("复杂类型赋值")
    @Test
    public void test5() {
        Person person05 = (Person) ioc2.getBean("person05");
        System.out.println(person05);
        System.out.println(person05.getMaps());
        System.out.println(person05.getProperties());
    }

    @DisplayName("复杂类型赋值")
    @Test
    public void test6() {
        Person person06 = (Person) ioc2.getBean("person06");
        Object car01 = ioc2.getBean("car01");
        System.out.println(person06);
        System.out.println(person06.getCar());
        System.out.println(car01);
    }

    @DisplayName("多实例作用域")
    @Test
    public void test7() {
        System.out.println("容器启动完成");
        Object book1 = ioc3.getBean("book");
        Object book2 = ioc3.getBean("book");
        Object book3 = ioc3.getBean("book");
        System.out.println(book1);
        System.out.println(book2);
        System.out.println(book3);
    }

    @DisplayName("工厂模式——自定义工厂类")
    @Test
    public void test8() {
        System.out.println("容器启动完成");
        Object airPlane01 = ioc3.getBean("airPlane01");
        System.out.println(airPlane01);

        Object airPlane02 = ioc3.getBean("airPlane02");
        System.out.println(airPlane02);
    }

    @DisplayName("工厂模式——实现FactoryBean")
    @Test
    public void test9() {
        System.out.println("容器启动完成");
        Object myFactoryBeanImp = ioc3.getBean("myFactoryBeanImp");
        Object myFactoryBeanImp2 = ioc3.getBean("myFactoryBeanImp");
        System.out.println(myFactoryBeanImp);
        System.out.println(myFactoryBeanImp == myFactoryBeanImp2);
    }

    @DisplayName("引用外部属性文件")
    @Test
    public void test10() {
        System.out.println("容器启动完成");
        Object bean = ioc4.getBean("car");
        System.out.println(bean);
    }

    @DisplayName("自动装配autowire")
    @Test
    public void test11() {
        System.out.println("容器启动完成");
        Person bean = ioc4.getBean(Person.class);
        System.out.println(bean);
    }
}