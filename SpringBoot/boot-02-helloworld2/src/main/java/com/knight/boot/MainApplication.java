package com.knight.boot;

import com.knight.boot.bean.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);
        Person person = run.getBean(Person.class);
        System.out.println(person);
        System.out.println(person.getUserName());
    }
}
