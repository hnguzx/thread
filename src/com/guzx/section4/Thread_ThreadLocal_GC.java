package com.guzx.section4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/1 11:04
 * @describe
 */
public class Thread_ThreadLocal_GC {
    static volatile ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>() {

        @Override
        protected void finalize() throws Throwable {
            System.out.println(this.toString() + " is gc");
        }
    };

    static volatile CountDownLatch countDownLatch = new CountDownLatch(10000);

    public static class ParseDate implements Runnable {
        int i = 0;

        public ParseDate(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            try {
                if (threadLocal.get() == null) {
                    threadLocal.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") {
                        @Override
                        protected void finalize() throws Throwable {
                            System.out.println(this.toString() + " is gc");
                        }
                    });
                    System.out.println(Thread.currentThread().getId() + ":create SimpleDateFormat");
                }
                Date date = threadLocal.get().parse("2021-06-01 10:43:" + i % 60);
                System.out.println(i + ":" + date);
            } catch (ParseException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10000; i++) {
            executorService.execute(new ParseDate(i));
        }
        countDownLatch.await();
        System.out.println("mission complete");
        threadLocal = null;
        System.gc();
        System.out.println("First GC complete");
        threadLocal = new ThreadLocal<SimpleDateFormat>();
        countDownLatch = new CountDownLatch(10000);
        for (int i = 0; i < 10000; i++) {
            executorService.execute(new ParseDate(i));
        }
        countDownLatch.await();
        Thread.sleep(1000);
        System.gc();
        System.out.println("Second GC complete");
        executorService.shutdown();
    }
}
