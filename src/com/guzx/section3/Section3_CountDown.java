package com.guzx.section3;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Section3_CountDown implements Runnable {
    static final CountDownLatch end = new CountDownLatch(10);
    static final Section3_CountDown demo = new Section3_CountDown();


    @Override
    public void run() {
        try {
            Thread.sleep(new Random().nextInt() * 1000);
            System.out.println("准备好了");
            end.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 创建一个固定大小的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.submit(demo);
        }
        end.await();
        System.out.println("完成");
        executorService.shutdown();

    }
}
