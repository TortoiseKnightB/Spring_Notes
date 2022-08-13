package com.knight.boot.bean;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        // importingClassMetadata 中包含了当前类的注解信息
        return new String[]{"com/knight/boot/bean/Toy"};
    }
}
