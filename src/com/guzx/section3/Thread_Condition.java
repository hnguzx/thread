package com.guzx.section3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Thread_Condition implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();
    // 与重入锁绑定
    public static Condition condition = lock.newCondition();

    @Override
    public void run() {
        try {
            lock.lock();
            condition.await();
            System.out.println("线程相当于挂起了！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread_Condition t1 = new Thread_Condition();
        Thread thread = new Thread(t1);
        thread.start();
        Thread.sleep(3000);
        lock.lock();
        condition.signal();
        lock.unlock();
    }
}
