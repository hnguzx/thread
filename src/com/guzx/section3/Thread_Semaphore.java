package com.guzx.section3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号量：允许多个线程同时访问
 */
public class Thread_Semaphore implements Runnable {
    final Semaphore semaphore = new Semaphore(5, true);

    @Override
    public void run() {
        try {
            semaphore.acquire();
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getId() + " done");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 获取到信号量后一定要记得释放
            semaphore.release();
        }
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(20);
        final Thread_Semaphore semaphore = new Thread_Semaphore();
        for (int i = 0; i < 20; i++) {
            service.submit(semaphore);
        }
    }
}
