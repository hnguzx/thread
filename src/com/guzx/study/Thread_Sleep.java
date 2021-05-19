package com.guzx.study;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/5/15 17:18
 * @describe 不参与CPU的调度，让出CPU的执行权，但是还是会持有资源的锁。
 * 当前线程进入阻塞状态
 */
public class Thread_Sleep {

    private static final Lock LOCK = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                LOCK.lock();
                try {
                    System.out.println("线程A sleep");
                    Thread.sleep(1000);
                    System.out.println("线程A Awake");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    LOCK.unlock();
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                LOCK.lock();
                try {
                    System.out.println("线程B sleep");
                    Thread.sleep(1000);
                    System.out.println("线程B Awake");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    LOCK.unlock();
                }
            }
        });

        threadA.start();
        Thread.sleep(500);
        System.out.println("这是中间的输出");
        threadA.interrupt();
        threadB.start();

    }
}
