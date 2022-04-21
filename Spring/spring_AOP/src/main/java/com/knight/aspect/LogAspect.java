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
@Order(2)
public class LogAspect {

    // 抽取可重用的切入点表达式
    @Pointcut("execution(public int com.knight.impl.MyMathCalculator.*(int, int))")
    public void pointcut() {
    }

    // 在目标方法正常执行之前执行
    // JoinPoint 封装了目标方法当前的详细信息
    @Before(value = "pointcut()")
    public static void logStart(JoinPoint joinPoint) {
        // 获取目标方法运行使用的参数
        Object[] args = joinPoint.getArgs();
        // 获取目标方法签名
        Signature signature = joinPoint.getSignature();
        System.out.println("LogAspect【" + signature.getName() + "】方法开始执行，用的参数列表【" + Arrays.asList(args) + "】");
    }

    // 在目标方法正常返回之后执行
    // returning = "result"，告诉spring用result来接收目标方法返回值
    @AfterReturning(value = "execution(public int com.knight.impl.MyMathCalculator.*(int, int))", returning = "result")
    public static void logReturn(JoinPoint joinPoint, Object result) {
        System.out.println("LogAspect【" + joinPoint.getSignature().getName() + "】方法正常执行完成，计算结果是：" + result);
    }

    // 在目标方法抛出异常之后执行
    // throwing = "exception"，告诉spring用 exception 来接收目标方法返回值
    @AfterThrowing(value = "execution(public int com.knight.impl.MyMathCalculator.*(int, *))", throwing = "exception")
    public static void logException(JoinPoint joinPoint, Exception exception) {
        System.out.println("LogAspect【" + joinPoint.getSignature().getName() + "】方法执行出现异常了，异常信息是：" + exception);
    }

    //想在目标方法结束的时候执行
    @After("execution(public int com.knight.impl.MyMathCalculator.*(..))")
    public static void logEnd() {
        System.out.println("LogAspect【xxx】方法最终结束了");
    }

    /**
     * 环绕通知
     */
    @Around("pointcut()")
    public Object myAround(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        String name = pjp.getSignature().getName();
        Object result = null;
        try {
            System.out.println("环绕前置通知");
            // @Before
            // 就是利用反射调用目标方法，method.invoke(obj,args)
            result = pjp.proceed(args);
            System.out.println("环绕返回通知");
            // @AfterReturning
        } catch (Throwable throwable) {
            System.out.println("环绕异常通知");
            // @AfterThrowing
            System.out.println(throwable);
            throw throwable;
        } finally {
            System.out.println("环绕后置通知");
            // @After
        }
        // 反射调用后的返回值
        return result;
    }

}
