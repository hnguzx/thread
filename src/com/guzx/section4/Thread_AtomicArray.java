package com.guzx.section4;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/1 17:31
 * @describe
 */
public class Thread_AtomicArray {
    static AtomicIntegerArray integerArray = new AtomicIntegerArray(10);

    public static class AddThread implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                integerArray.getAndIncrement(i % integerArray.length());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new AddThread());
        }

        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }

        for (int i = 0; i < 10; i++) {
            threads[i].join();
        }

        System.out.println(integerArray);

    }
}
