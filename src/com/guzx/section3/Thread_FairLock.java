package com.guzx.section3;

import java.util.concurrent.locks.ReentrantLock;

public class Thread_FairLock implements Runnable {
    public static ReentrantLock fairLock = new ReentrantLock(true);
//    public static ReentrantLock fairLock = new ReentrantLock(false);

    @Override
    public void run() {
        while (true) {
            try {
                fairLock.lockInterruptibly();
                System.out.println(Thread.currentThread().getName() + " 获得锁");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (fairLock.isHeldByCurrentThread()){
                    fairLock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread_FairLock lock = new Thread_FairLock();
        Thread t1 = new Thread(lock, "Thread1");
        Thread t2 = new Thread(lock, "Thread2");

        t1.start();
        t2.start();
    }
}
