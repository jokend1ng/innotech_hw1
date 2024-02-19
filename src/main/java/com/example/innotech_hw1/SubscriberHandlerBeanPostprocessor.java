package com.example.innotech_hw1;



import org.example.MessageQueue;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class SubscriberHandlerBeanPostprocessor implements BeanPostProcessor {
    private final MessageQueue queue;
    private final Map<Object, Method> subscribers = new HashMap<>();
    public SubscriberHandlerBeanPostprocessor(MessageQueue queue) {
        this.queue = queue;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Method[] methods = bean.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Subscriber.class)) {
                subscribers.put(bean, method);
            }

        }
        return bean;
    }
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                 new Thread(() -> {
                    while (true) {
                        String message = queue.poll();
                        if (message != null) {
                            subscribers.forEach((originalbean, method) ->
                            {
                                try {
                                    method.setAccessible(true);
                                    method.invoke(originalbean, message);
                                } catch (IllegalAccessException e) {
                                    throw new RuntimeException(e);
                                } catch (InvocationTargetException e) {
                                    throw new RuntimeException(e);
                                }
                            });
                        }

                        }
                    }).start();
            return bean;
    }
}
