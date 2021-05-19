package com.guzx.study;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/5/15 16:44
 * @describe 等待线程执行中止
 * 如果一个线程调用另一个线程的join方法时，该线程会被阻塞，当被第三方线程interrupt时，会直接抛异常
 * 阻塞与挂起的区别：
 * 被动与主动，释放CPU/不释放CPU，任务调度时会有影响/无影响
 */
public class Thread_Join {


    public static void main(String[] args) {

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread A run Over");
            }
        });

        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    threadA.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread B run Over");
                threadC.interrupt();
            }
        });


        threadA.start();
        threadC.start();
        threadB.start();

        System.out.println("线程都已启动");

        try {
            threadA.join();
            threadB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("线程都已执行完毕");
    }


}
