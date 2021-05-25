package com.guzx.section2;

public class Thread_JoinAndYield {
    public volatile static int i = 0;

    public static class AddThread extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " start");
            for (i = 0; i < 1000; i++) {
                // 要有多个线程才有意义
                Thread.yield();
            }
            System.out.println(Thread.currentThread().getName() + " over");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AddThread addThread = new AddThread();
        addThread.start();

        AddThread addThread2 = new AddThread();
        addThread2.start();
        // 将实例线程加入到当前线程，阻塞当前线程，直到目标线程执行完毕
        addThread.join();
        System.out.println(i);
    }

}
