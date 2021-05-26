package com.guzx.section2;

public class Thread_ThreadGroup implements Runnable {
    public static void main(String[] args) {
        ThreadGroup threadGroup = new ThreadGroup("PrintGroup");
        // 所属线程组，实现Runnable的类，线程名称
        Thread thread1 = new Thread(threadGroup, new Thread_ThreadGroup(), "T1");
        Thread thread2 = new Thread(threadGroup, new Thread_ThreadGroup(), "T2");
        thread2.setPriority(6);

        thread1.start();
        thread2.start();
        System.out.println(threadGroup.activeCount());
        // 显示每个线程的信息：线程名，线程优先级，线程组
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
