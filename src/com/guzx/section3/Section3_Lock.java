package com.guzx.section3;

import java.util.concurrent.locks.ReentrantLock;

public class Section3_Lock implements Runnable {
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();
    int lock;

    public Section3_Lock(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            if (lock == 1) {
//                1）如果当前线程未被中断，则获取锁。
//                2）如果该锁没有被另一个线程保持，则获取该锁并立即返回，将锁的保持计数设置为 1。
//                3）如果当前线程已经保持此锁，则将保持计数加 1，并且该方法立即返回。
//                4）如果锁被另一个线程保持，则出于线程调度目的，禁用当前线程，并且在发生以下两种情况之一以前，该线程将一直处于休眠状态：
//                    1）锁由当前线程获得；
//                    2）其他某个线程中断当前线程。
//                5）如果当前线程获得该锁，则将锁保持计数设置为 1。
//                如果当前线程：
//                    1）在进入此方法时已经设置了该线程的中断状态；
//                    2）在等待获取锁的同时被中断。
//                则抛出 InterruptedException，并且清除当前线程的已中断状态。
//                6）在此实现中，因为此方法是一个显式中断点，所以要优先考虑响应中断，而不是响应锁的普通获取或重入获取。
//                指定者： 接口 Lock 中的 lockInterruptibly
//                抛出：   InterruptedException   如果当前线程已中断。

                lock1.lockInterruptibly();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {

                }
                lock2.lockInterruptibly();
            } else {
                lock2.lockInterruptibly();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {

                }
                lock1.lockInterruptibly();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 当前线程是否保持此锁定
            if (lock1.isHeldByCurrentThread()) {
                lock1.unlock();
            }
            if (lock2.isHeldByCurrentThread()) {
                lock2.unlock();
            }
            System.out.println("线程退出：" + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Section3_Lock lock1 = new Section3_Lock(1);
        Section3_Lock lock2 = new Section3_Lock(2);
        Thread t1 = new Thread(lock1);
        Thread t2 = new Thread(lock2);
        t1.start();
        t2.start();
        Thread.sleep(5000);
        t2.interrupt();


    }
}
