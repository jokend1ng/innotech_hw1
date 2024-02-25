package com.example;



public interface  Publisher<T> {

    <T> void publish(T t);
}
