package com.guzx.section2;

public class Section2_ThreadGroup implements Runnable {
    public static void main(String[] args) {
        ThreadGroup threadGroup = new ThreadGroup("PrintGroup");
        // 所属线程组，实现Runnable的类，线程名称
        Thread thread1 = new Thread(threadGroup, new Section2_ThreadGroup(), "T1");
        Thread thread2 = new Thread(threadGroup, new Section2_ThreadGroup(), "T2");

        thread1.start();
        thread2.start();
        System.out.println(threadGroup.activeCount());
        threadGroup.list();
    }

    @Override
    public void run() {
        String groupName = Thread.currentThread().getThreadGroup().getName() + "-" + Thread.currentThread().getName();
        while (true) {
            System.out.println("i'm " + groupName);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
