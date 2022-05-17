package com.knight.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Aspect
@Component
@Order(1) // 使用 Order 改变切面顺序，数值越小，优先级越高
public class ValidateAspect {

    @Pointcut("com.knight.aspect.LogAspect.pointcut()")
    public void pointcut() {
    }

    @Before(value = "pointcut()")
    public static void logStart(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        System.out.println("ValidateAspect【" + signature.getName() + "】方法开始执行，用的参数列表【" + Arrays.asList(args) + "】");
    }

    @AfterReturning(value = "pointcut()", returning = "result")
    public static void logReturn(JoinPoint joinPoint, Object result) {
        System.out.println("ValidateAspect【" + joinPoint.getSignature().getName() + "】方法正常执行完成，计算结果是：" + result);
    }

    @AfterThrowing(value = "pointcut()", throwing = "exception")
    public static void logException(JoinPoint joinPoint, Exception exception) {
        System.out.println("ValidateAspect【" + joinPoint.getSignature().getName() + "】方法执行出现异常了，异常信息是：" + exception);
    }

    @After("pointcut()")
    public static void logEnd() {
        System.out.println("ValidateAspect【xxx】方法最终结束了");
    }


}
