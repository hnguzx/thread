package com.guzx.section3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Section3_Condition implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();
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
        Section3_Condition t1 = new Section3_Condition();
        Thread thread1 = new Thread(t1);
        thread1.start();
        Thread.sleep(3000);
        lock.lock();
        condition.signal();
        lock.unlock();
    }
}
