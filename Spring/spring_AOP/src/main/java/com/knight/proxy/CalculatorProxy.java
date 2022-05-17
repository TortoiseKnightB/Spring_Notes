package com.knight.proxy;

import com.knight.inter.Calculator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 帮Calculator.java生成代理对象的类
 * Object newProxyInstance
 * (ClassLoader loader, Class<?>[] interfaces, InvocationHandler h)
 */
public class CalculatorProxy {

    /**
     * 为传入的参数对象创建一个动态代理对象
     *
     * @param calculator
     * @return Calculator calculator:被代理对象；
     */
    public static Calculator getProxy(final Calculator calculator) {

        //方法执行器。帮我们目标对象执行目标方法
        InvocationHandler h = new InvocationHandler() {
            /**
             * Object proxy：代理对象；给jdk使用，任何时候都不要动这个对象
             * Method method：当前将要执行的目标对象的方法
             * Object[] args：这个方法调用时外界传入的参数值
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) {

                Object result = null;
                try {
                    // 利用反射执行目标方法
                    //目标方法执行后的返回值
                    result = method.invoke(calculator, args);
                } catch (Exception e) {

                } finally {

                }

                //返回值必须返回出去外界才能拿到真正执行后的返回值
                return result;
            }
        };
        Class<?>[] interfaces = calculator.getClass().getInterfaces();
        ClassLoader loader = calculator.getClass().getClassLoader();

        //Proxy为目标对象创建代理对象；
        Object proxy = Proxy.newProxyInstance(loader, interfaces, h);
        return (Calculator) proxy;
    }

}