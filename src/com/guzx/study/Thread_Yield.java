package com.guzx.study;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/5/15 17:31
 * @describe 与sleep方法类似，会让出CPU的执行权，然后重新获取执行权。
 * 与线程的优先级有关，如果优先级较高，则会马上又获得执行权
 */
public class Thread_Yield implements Runnable {

    public static void main(String[] args) {
        new Thread_Yield();
        new Thread_Yield();
        new Thread_Yield();
    }

    public Thread_Yield() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            if (i % 5 == 0) {
                System.out.println(Thread.currentThread().getName() + " yield");
                Thread.yield();
            }
        }
        System.out.println(Thread.currentThread().getName() + " over");
    }
}
