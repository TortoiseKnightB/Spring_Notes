package com.knight.reference.bean6_cr;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan(basePackages = {"com.knight.reference.bean6_cr"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SpringConfig6 {
}
