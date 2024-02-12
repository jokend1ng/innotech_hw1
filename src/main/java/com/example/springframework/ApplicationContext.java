package com.example.springframework;

import com.example.springframework.annotation.bean.factory.Autowred;
import com.example.springframework.annotation.stereotype.*;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;;
import java.lang.reflect.Proxy;
import java.util.Set;


public class ApplicationContext {
    private BeanFactory beansFactory = new BeanFactoryImpl();
    private Object object;

    public BeanFactory getBeansFactory() {
        return beansFactory;
    }

    public void getAutowired() {
        for (Object object : getBeansFactory().getInstance().values()) {
            for (Field field : object.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(Autowred.class)) {
                    try {
                        Object objectInstance = getBeansFactory().getBean(field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1));
                        field.set(object, objectInstance);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public void scanControllers(String s) {

        getBeansFactory().setBean(scanPackage(s, Controller.class));

    }


    public void scanComponent(String s) {
        getBeansFactory().setBean(scanPackage(s, Component.class));

    }

    public void scanService(String s) {
        getBeansFactory().setBean(scanPackage(s, Service.class));

    }

    public Set<Class<?>> scanPackage(String s, Class<? extends Annotation> clas) {
        Reflections reflections = new Reflections(s);
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(clas);
        getBeansFactory().setBean(classes);
        return classes;
    }
    public Object getLogging(Object object) {
        return Proxy.newProxyInstance(
                object.getClass().getClassLoader(),
                object.getClass().getInterfaces(),
                new LoggingInvocationHandler(object)
        );
    }

}