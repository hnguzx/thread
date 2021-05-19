package com.guzx.study;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/5/15 17:47
 * @describe 线程中断
 * interrupt：设置线程的中断标志
 * isInterrupted：判断线程是否被中断
 * interrupted：判断线程是否中断并清除中断标志
 */
public class Thread_Interrupt {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + " running");
                }
            }
        });

        thread.start();
        Thread.sleep(1000);
        System.out.println("main thread interrupt thread");
        thread.interrupt();
        thread.join();
        System.out.println("main is over");

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("threadB begin sleep 200s");
                try {
                    Thread.sleep(200000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("threadB awake");
            }
        });

        threadB.start();
        Thread.sleep(1000);
        threadB.interrupt();
        threadB.join();
        System.out.println("main is over");


        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                /*for (;;){
                }*/

                while (!Thread.currentThread().isInterrupted()) {
                }
                System.out.println("thread interrupted：" + Thread.currentThread().isInterrupted());
            }
        });

        threadC.start();
        threadC.interrupt();

        System.out.println(threadC.isInterrupted());
        System.out.println(Thread.interrupted());
        System.out.println(threadC.isInterrupted());
        System.out.println("main over");
    }
}
