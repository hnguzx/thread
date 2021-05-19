package com.guzx.study;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/5/15 18:49
 * @describe 用户线程与守护线程
 * 子线程的执行与父线程无关
 * 当没有在运行中的用户线程时，jvm会退出
 */
public class Thread_UserOrDaemon {

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        thread.setDaemon(true);
        thread.start();

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                for (;;){}
            }
        });
//        threadB.setDaemon(true);
        threadB.start();
        System.out.println("main over");
    }
}
