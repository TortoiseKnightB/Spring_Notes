package com.knight.factory;

import com.knight.bean.Book;
import org.springframework.beans.factory.FactoryBean;

import java.util.UUID;

/**
 * 实现了FactoryBean接口的实现类都是Spring可以认识的工厂类
 * Spring会自动调用工厂方法创建实例对象
 */
public class MyFactoryBeanImp implements FactoryBean<Book> {

    /**
     * 返回创建的对象
     *
     * @return
     * @throws Exception
     */
    @Override
    public Book getObject() throws Exception {
        System.out.println("MyFactoryBeanImp...创建对象");
        Book book = new Book();
        book.setBookName(UUID.randomUUID().toString());
        return book;
    }

    /**
     * 返回创建的对象的类型
     * Spring自动调用，来确认创建的对象是什么类型
     *
     * @return
     */
    @Override
    public Class<?> getObjectType() {
        return Book.class;
    }

    /**
     * 是否单例
     *
     * @return
     */
    @Override
    public boolean isSingleton() {
        return true;
    }
}
