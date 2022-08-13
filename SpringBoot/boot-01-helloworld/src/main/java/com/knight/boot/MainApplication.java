package com.knight.boot;

import com.knight.boot.bean.Pet;
import com.knight.boot.bean.Toy;
import com.knight.boot.bean.User;
import com.knight.boot.config.MyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 主程序类
 *
 * @SpringBootApplication：这是一个SpringBoot应用
 */
@SpringBootApplication(scanBasePackages = "com.knight.boot")
public class MainApplication {

    public static void main(String[] args) {
        // 返回IOC容器
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);

        // 查看容器里面的组件
        String[] beanDefinitionNames = run.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }

        System.out.println("\n********** @Configuration @Bean 注入组件 **************************");

        // 从容器中获取组件，默认是单实例的
        Pet tom1 = run.getBean("tom", Pet.class);
        Pet tom2 = run.getBean("tom", Pet.class);
        System.out.println(tom1);
        System.out.println(tom1 == tom2);

        //如果@Configuration(proxyBeanMethods = true)代理对象调用方法。SpringBoot总会检查这个组件是否在容器中有。
        //保持组件单实例
        MyConfig myConfig = run.getBean(MyConfig.class);
        System.out.println(myConfig);
        Pet pet1 = myConfig.tomcatPet();
        Pet pet2 = myConfig.tomcatPet();
        System.out.println(pet1);
        System.out.println(pet1 == pet2);
        System.out.println(pet1 == tom1);

        // 组件依赖
        User user = run.getBean("user01", User.class);
        System.out.println(user.getPet() == tom1);

        System.out.println("\n********** @Import 使用 *********************************");

        String[] beanNamesForType = run.getBeanNamesForType(User.class);
        for (String bean : beanNamesForType) {
            System.out.println(bean);
        }

        String[] beanNamesForType2 = run.getBeanNamesForType(Pet.class);
        for (String bean : beanNamesForType2) {
            System.out.println(bean);
        }

        System.out.println("\n********** @Import 使用 ImportSelector、ImportBeanDefinitionRegistrar ***********");
        String[] beanNamesForType3 = run.getBeanNamesForType(Toy.class);
        for (String bean : beanNamesForType3) {
            System.out.println(bean);
        }

        System.out.println("\n********** @Conditional ***********");
        boolean userToy01 = run.containsBean("userToy01");
        System.out.println("容器中是否存在userToy01组件：" + userToy01);
    }
}
