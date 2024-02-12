package com.example.springframework;


import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BeanFactoryImpl implements BeanFactory {
    private static final Map<String, Object> INSTANCE = new HashMap<>();

    @Override
    public Map<String, Object> getInstance() {
        return INSTANCE;
    }

    @Override
    public Object getBean(String name) {
        return INSTANCE.get(name);
    }

    @Override
    public Object setBean(Set<Class<?>> clazz) {
        String name = null;
        Object controller = null;
        for (Class<?> claz : clazz)
        if (claz.isInterface()) {
            ;
            name = claz.getSimpleName();


        }else {
            try {
                controller = claz.getDeclaredConstructor().newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }

        if (name == null) {

        } else {
            getInstance().put(name, controller);
        }
        return controller;
    }

}


