package com.knight.reference;

import com.knight.reference.bean1.A1;
import com.knight.reference.bean1.B1;
import com.knight.reference.bean1.SpringConfig1;
import com.knight.reference.bean2.A2;
import com.knight.reference.bean2.B2;
import com.knight.reference.bean2.SpringConfig2;
import com.knight.reference.bean3.A3;
import com.knight.reference.bean3.B3;
import com.knight.reference.bean3.SpringConfig3;
import com.knight.reference.bean4.A4;
import com.knight.reference.bean4.B4;
import com.knight.reference.bean4.SpringConfig4;
import com.knight.reference.bean5_no_cr.A5;
import com.knight.reference.bean5_no_cr.B5;
import com.knight.reference.bean5_no_cr.SpringConfig5;
import com.knight.reference.bean6_cr.A6;
import com.knight.reference.bean6_cr.B6;
import com.knight.reference.bean6_cr.SpringConfig6;
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

    @DisplayName("属性注入循环依赖")
    @Test
    public void test04() {
        ApplicationContext ioc = new AnnotationConfigApplicationContext(SpringConfig4.class);
        A4 a = ioc.getBean(A4.class);
        B4 b = ioc.getBean(B4.class);
        System.out.println(a);
        System.out.println(b);
    }

    @DisplayName("无循环依赖情况流程演示")
    @Test
    public void test05() {
        ApplicationContext ioc = new AnnotationConfigApplicationContext(SpringConfig5.class);
        A5 a = ioc.getBean(A5.class);
        B5 b = ioc.getBean(B5.class);
        System.out.println(a);
        System.out.println(b);
    }

    @DisplayName("循环依赖情况流程演示")
    @Test
    public void test06() {
        ApplicationContext ioc = new AnnotationConfigApplicationContext(SpringConfig6.class);
        A6 a = ioc.getBean(A6.class);
        B6 b = ioc.getBean(B6.class);
        a.say();
        b.say();
    }

}
