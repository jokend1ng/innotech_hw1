package com.example.innotech_hw1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        queue.offer("Hello");
        queue.offer("Hell");
        queue.offer("Hel");
        queue.offer("He");
        for (String s : queue) {
            System.out.println(s);
        }
    }
}
