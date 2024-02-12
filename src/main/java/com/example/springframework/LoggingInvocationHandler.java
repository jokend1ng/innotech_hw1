package com.example.springframework;

import com.example.springframework.annotation.stereotype.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LoggingInvocationHandler implements InvocationHandler {
    private final Object target;
    private final Logger logger;

    public LoggingInvocationHandler(Object target) {
        this.target = target;
        logger = LoggerFactory.getLogger(target.getClass());
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Log.class)) {
            logger.info("Начало выполнения метода  [%s]".formatted(method.getName()));
            Object invoke = method.invoke(target, args);
            logger.info("Метож [%s] выполнен с результатом [%s]".formatted(method.getName(), invoke));
            return invoke;
        }
        return method.invoke(target, args);
    }
}
