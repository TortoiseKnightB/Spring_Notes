package com.knight.ioc.bean;

public class User {

    private String username;

    public User() {
        System.out.println("第一步...执行无参构造器创建Bean实例");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        System.out.println("第二步...调用setter方法为属性赋值");
    }

    public void initMethod() {
        System.out.println("第三步...调用初始化方法");
    }

    public void destroyMethod() {
        System.out.println("第五步...调用销毁方法");
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                '}';
    }
}
