package com.knight.boot.bean;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        ImportBeanDefinitionRegistrar.super.registerBeanDefinitions(importingClassMetadata, registry);
        // 检查容器中是否存在 tom 和 Toy
        boolean definition1 = registry.containsBeanDefinition("tom");
        boolean definition2 = registry.containsBeanDefinition("com.knight.boot.bean.Toy");
        if (definition1 && definition2) {
            // 如果都存在，则添加 tomToY
            RootBeanDefinition beanDefinition = new RootBeanDefinition(Toy.class);
            registry.registerBeanDefinition("tomToy", beanDefinition);
        }
    }

}
