package com.knight.ioc.factory;

import com.knight.ioc.bean.Car;

public class CarStaticFactory {

    public static Car getCar(Double price, String color) {
        Car car = new Car(price, color);
        return car;
    }
}
