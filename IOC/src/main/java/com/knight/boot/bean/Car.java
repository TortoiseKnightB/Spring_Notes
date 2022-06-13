package com.knight.boot.bean;


public class Car {

    private Double price;

    private String color;

    public Car() {
    }

    public Car(Double price, String color) {
        this.price = price;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "price=" + price +
                ", color='" + color + '\'' +
                '}';
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
