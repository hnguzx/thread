package com.guzx.section3;

import java.util.concurrent.locks.ReentrantLock;

public class Section3_FairLock implements Runnable {
//    public static ReentrantLock fairLock = new ReentrantLock(true);
    public static ReentrantLock fairLock = new ReentrantLock(false);

    @Override
    public void run() {
        while (true) {
            try {
                fairLock.lock();
                System.out.println(Thread.currentThread().getName() + " 获得锁");
            } finally {
                fairLock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        Section3_FairLock lock = new Section3_FairLock();
        Thread t1 = new Thread(lock, "Thread1");
        Thread t2 = new Thread(lock, "Thread2");

        t1.start();
        t2.start();
    }
}
