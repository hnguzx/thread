package com.guzx.section3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Section3_ThreadPool {
    public static class MyTask implements Runnable {

        @Override
        public void run() {
            System.out.println("current time " + System.currentTimeMillis() + "Thread ID:" + Thread.currentThread().getId());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        MyTask task = new MyTask();
//        ExecutorService service = Executors.newFixedThreadPool(5);
//        ExecutorService service = Executors.newSingleThreadExecutor();
//        ExecutorService service = Executors.newCachedThreadPool();
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        for (int i = 0; i < 10; i++) {
//            service.submit(task);
//            service.schedule(task, 10, TimeUnit.SECONDS);
            service.scheduleAtFixedRate(task, 2, 5, TimeUnit.SECONDS);
        }
        service.shutdown();
    }
}
