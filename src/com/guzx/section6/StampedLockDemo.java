package com.guzx.section6;

import java.awt.*;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.StampedLock;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/15 10:09
 * @describe 读写锁的改良
 */
public class StampedLockDemo {

    static Thread[] threads = new Thread[3];
    static final StampedLock LOCK = new StampedLock();

    public static void main(String[] args) throws InterruptedException {
        new Thread() {
            @Override
            public void run() {
                long l = LOCK.writeLock();
                LockSupport.parkNanos(600000000000L);
                LOCK.unlock(l);
            }
        }.start();

        Thread.sleep(100);
        for (int i = 0; i < 3; ++i) {
            threads[i] = new Thread(new HoldReadLock());
            threads[i].start();
        }
        Thread.sleep(1000);
        for (int i = 0; i < 3; ++i) {
            threads[i].interrupt();
        }
    }

    private static class HoldReadLock implements Runnable {
        @Override
        public void run() {
            long l = LOCK.readLock();
            System.out.println(Thread.currentThread().getName() + " 获得读锁");
            LOCK.unlock(l);
        }
    }
}
