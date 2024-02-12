package com.example;

import com.example.springframework.ApplicationContext;
import com.example.springframework.BeanFactory;


public class Main {
    public static void main(String[] args) {
        var application = new ApplicationContext();
        application.scanControllers("com.example");
        application.scanComponent("com.example");
        application.scanService("com.example");
        BeanFactory bean= application.getBeansFactory();
        application.getAutowired();
        application.getBeansFactory().getInstance().values().forEach(System.out::println);



    }
}
