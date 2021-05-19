package com.guzx.study;

import java.util.Base64;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/5/12 15:20
 * @describe 线程的等待与通知
 * 等待：wait将指定线程挂起，直到被notify或notifyAll继续运行或该线程被其它线程中断。释放当前变量的锁。
 * notify和notifyAll的区别：notifyAll会唤醒所有被挂起的线程
 * 通知：notify/notifyAll，唤醒因为wait方法被挂起的线程
 */
public class Thread_NotifyAndWait {

    static Object object = new Object();
    static Boolean isLock = true;

    public static void main(String[] args) {
        Thread thread = new Thread(new CustomerRunnable2());
        thread.start();
//        new Thread(new CustomerRunnable1()).start();
    }

    public static class CustomerRunnable1 implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (object) {
                System.out.println("object notify");
//                object.notify();
                object.notifyAll();
                isLock = false;
            }
        }
    }

    public static class CustomerRunnable2 implements Runnable {
        @Override
        public void run() {
            synchronized (object) {
                try {
                    System.out.println("object wait");
                    while (isLock) {
//                        object.wait();
                        object.wait(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("customerRunnable2 run success!");
        }
    }


}
