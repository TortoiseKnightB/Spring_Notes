package com.knight.ioc.factory;

import com.knight.ioc.bean.Car;
import org.springframework.beans.factory.FactoryBean;

public class CarFactoryBeanImp implements FactoryBean<Car> {

    @Override
    public Car getObject() {
        System.out.println("CarFactoryBeanImp...创建对象");
        Car car = new Car(66666.6, "Black");
        return car;
    }

    @Override
    public Class<?> getObjectType() {
        return Car.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
