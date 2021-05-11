package com.guzx.section3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Section3_Semaphore implements Runnable {
    final Semaphore semaphore = new Semaphore(5,true);

    @Override
    public void run() {
        try {
            semaphore.acquire();
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getId() + " done");
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(20);
        final Section3_Semaphore semaphore = new Section3_Semaphore();
        for (int i = 0; i < 20; i++) {
            service.submit(semaphore);
        }
    }
}
