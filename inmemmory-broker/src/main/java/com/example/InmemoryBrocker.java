package com.example;



public interface InmemoryBrocker {
    void publish(String message);

    String take();
}
