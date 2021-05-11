package com.guzx.section3;

import java.util.concurrent.locks.ReentrantLock;

public class Section3_TimeLock implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
//            if (lock.tryLock(5, TimeUnit.SECONDS)) {
            if (lock.tryLock()) {
                System.out.println("获得锁的线程是："+Thread.currentThread().getName());
                Thread.sleep(6000);

            } else {
                System.out.println("get lock failed");
                System.out.println("未获取到锁的线程是："+Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread())
                lock.unlock();
        }
    }

    public static void main(String[] args) {
        Section3_TimeLock timeLock = new Section3_TimeLock();
        Thread t1 = new Thread(timeLock);
        Thread t2 = new Thread(timeLock);
        t1.start();
        t2.start();

    }
}
