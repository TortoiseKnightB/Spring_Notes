package com.knight.boot.factory;

import com.knight.boot.bean.Car;

public class CarInsanceFactory {

    public Car getCar(Double price, String color) {
        Car car = new Car(price, color);
        return car;
    }
}
