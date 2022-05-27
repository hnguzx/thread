package com.guzx.section4;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/1 15:22
 * @describe
 */
public class Thread_AtomicInteger {
                static AtomicInteger i = new AtomicInteger();
//    static Integer i = new Integer(0);

    public static class AddThread implements Runnable {
        @Override
        public void run() {
            for (int k = 0; k < 100000; k++) {
                i.incrementAndGet();
//                increment();
            }
        }

//        public static synchronized void increment() {
//            i++;
//        }
    }


    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for (int k = 0; k < 10; k++) {
            threads[k] = new Thread(new AddThread());
        }

        System.out.println("begin:"+System.currentTimeMillis());
        for (int k = 0; k < 10; k++) {
            threads[k].start();
        }

        for (int k = 0; k < 10; k++) {
            threads[k].join();
        }
        System.out.println("end:"+System.currentTimeMillis());
        System.out.println(i);
    }


}
