package com.knight.reference;

import com.knight.reference.bean.SpringConfig;
import com.knight.reference.bean.UserService;
import com.knight.reference.bean1.A1;
import com.knight.reference.bean1.B1;
import com.knight.reference.bean1.SpringConfig1;
import com.knight.reference.bean2.A2;
import com.knight.reference.bean2.B2;
import com.knight.reference.bean2.SpringConfig2;
import com.knight.reference.bean2222.Person;
import com.knight.reference.bean2222.User;
import com.knight.reference.bean3.A3;
import com.knight.reference.bean3.B3;
import com.knight.reference.bean3.SpringConfig3;
import com.knight.reference.bean4.A4;
import com.knight.reference.bean4.B4;
import com.knight.reference.bean4.SpringConfig4;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


@DisplayName("Bean循环依赖")
public class ReferenceTest {

    @DisplayName("构造器注入循环依赖")
    @Test
    public void test01() {
        ApplicationContext ioc = new AnnotationConfigApplicationContext(SpringConfig1.class);
        A1 a = ioc.getBean(A1.class);
        B1 b = ioc.getBean(B1.class);
        System.out.println(a);
        System.out.println(b);
    }

    @DisplayName("单例setter注入循环依赖")
    @Test
    public void test02() {
        ApplicationContext ioc = new AnnotationConfigApplicationContext(SpringConfig2.class);
        A2 a = ioc.getBean(A2.class);
        B2 b = ioc.getBean(B2.class);
        System.out.println(a);
        System.out.println(b);
    }

    @DisplayName("多例setter注入循环依赖")
    @Test
    public void test03() {
        ApplicationContext ioc = new AnnotationConfigApplicationContext(SpringConfig3.class);
        A3 a = ioc.getBean(A3.class);
        B3 b = ioc.getBean(B3.class);
        System.out.println(a);
        System.out.println(b);
    }

    @DisplayName("多例setter注入循环依赖")
    @Test
    public void test04() {
        ApplicationContext ioc = new AnnotationConfigApplicationContext(SpringConfig4.class);
        A4 a = ioc.getBean(A4.class);
        B4 b = ioc.getBean(B4.class);
        System.out.println(a);
        System.out.println(b);
    }













    @Test
    public void test011() {
        ApplicationContext ioc = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserService userService = ioc.getBean(UserService.class);
        System.out.println(userService);
    }

    @Test
    public void test022() {
        ApplicationContext ioc = new AnnotationConfigApplicationContext(SpringConfig2.class);
        User user = ioc.getBean(User.class);
        Person person = ioc.getBean(Person.class);
        System.out.println(user);
        System.out.println(person);
    }
}
