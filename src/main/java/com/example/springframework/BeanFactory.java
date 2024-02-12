package com.example.springframework;

import java.util.Map;
import java.util.Set;

public interface BeanFactory {
    Object getBean(String name);
    Map<String,Object> getInstance();
    void setBean(Set <Class<?>> clazz);
}
