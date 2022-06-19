package com.knight.reference.bean6_cr;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class BeanAspect {

    @Pointcut(value = "execution(* com.knight.reference.bean6_cr..*.*(..))")
    public void pointcut(){}

    @After(value = "pointcut()")
    public void after(){
        System.out.println("after 增强...");
    }
}
