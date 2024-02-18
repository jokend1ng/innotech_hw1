package com.example.innotech_hw1;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import org.example.MessageQueue;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class SubscriberHandlerBeanPostprocessor implements BeanPostProcessor {
    private MessageQueue queue;
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Method[] methods  = bean.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Subscriber.class)){

                return Proxy.newProxyInstance(method.getClass().getClassLoader(), method.getClass().getInterfaces(), (proxy, method1, args) -> {
                    Class<?> clazz = method1.getClass();
                    if (method1.isAnnotationPresent(Subscriber.class)){
                        new Thread(()-> {
                            while(true){
                               String message= queue.poll();
                                if(message!=null){
                                    try {
                                      Object[] parameters = new Object[]{message};
                                       method1.invoke(clazz, parameters);
                                    } catch (IllegalAccessException e) {
                                        throw new RuntimeException(e);
                                    } catch (InvocationTargetException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            }
                        });
                    }
                    return  method1.invoke(clazz,args);
                });
            }
        }
        return bean;
    }
}
