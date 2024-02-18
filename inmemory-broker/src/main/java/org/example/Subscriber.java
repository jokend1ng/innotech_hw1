package org.example;


import java.util.concurrent.CopyOnWriteArrayList;

public class Subscriber implements Runnable {
    private final  MessageQueue queue;

    public Subscriber(MessageQueue queue) {
        this.queue = queue;
    }

    public void run() {
        while (true) {
        String message = queue.poll();
            if (message != null) {

                System.out.println(message);

            } else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Subscriber interrupted.");
                    break;
                }
            }
        }
    }
}
