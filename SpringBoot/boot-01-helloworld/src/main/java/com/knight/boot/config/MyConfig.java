package com.knight.boot.config;

import com.knight.boot.bean.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 1、配置类里面使用@Bean标注在方法上给容器注册组件，默认是单实例的
 * <p>
 * 2、配置类本身也是组件
 * <p>
 * 3、proxyBeanMethods：代理bean的方法
 * <ul>
 *     <li>Full(proxyBeanMethods = true)、【保证每个@Bean方法无论被调用多少次，返回的组件都是单实例的】</li>
 *     <li>Lite(proxyBeanMethods = false)【每个@Bean方法被调用时，返回的组件都是新创建的】</li>
 *     <li>组件依赖必须使用Full模式（默认模式）。其他选择Lite模式</li>
 * </ul>
 * 4、@Import({User.class, Pet.class})：给容器中自动创建出这两个类型的组件、默认组件的名字就是全类名
 */
@Import({User.class, Pet.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
@Configuration(proxyBeanMethods = true)  // 告诉SpringBoot这是一个配置类 == 配置文件
public class MyConfig {

    @Bean(name = "tom") // 给容器中添加组件。以方法名作为组件的id。返回类型就是组件类型。返回的值，就是组件在容器中的实例
    public Pet tomcatPet() {
        return new Pet("tomcat", 6);
    }

    @Bean(name = "user01")
    public User user01() {
        return User.builder()
                .name("用户01")
                .pet(tomcatPet())
                .build();
    }

    @ConditionalOnBean(name = "user01")
    @Bean(name = "userToy01")
    public Toy userToy01() {
        return Toy.builder()
                .name("userToy01")
                .build();
    }
}
