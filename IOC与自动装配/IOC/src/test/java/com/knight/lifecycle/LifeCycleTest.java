package com.knight.lifecycle;

import com.knight.lifecycle.bean.SpringConfig;
import com.knight.lifecycle.bean.UserService;
import com.knight.lifecycle.bean2.Person;
import com.knight.lifecycle.bean2.SpringConfig2;
import com.knight.lifecycle.bean2.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


@DisplayName("Bean生命周期")
public class LifeCycleTest {

    @Test
    public void test01() {
        ApplicationContext ioc = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserService userService = ioc.getBean(UserService.class);
        System.out.println(userService);
    }

    @Test
    public void test02() {
        ApplicationContext ioc = new AnnotationConfigApplicationContext(SpringConfig2.class);
        User user = ioc.getBean(User.class);
        Person person = ioc.getBean(Person.class);
        System.out.println(user);
        System.out.println(person);
    }
}
