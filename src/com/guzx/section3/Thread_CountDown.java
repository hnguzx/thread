package com.guzx.section3;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 倒计数器
 */
public class Thread_CountDown implements Runnable {
    static final CountDownLatch end = new CountDownLatch(10);
    static final Thread_CountDown demo = new Thread_CountDown();


    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println("准备好了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 等待数量减1
            end.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 创建一个固定大小的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.submit(demo);
        }
        // 等待所有的任务完成
        end.await();
        System.out.println("完成");
        executorService.shutdown();

    }
}
