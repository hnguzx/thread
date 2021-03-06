package com.guzx.section2;

public class Thread_Join extends Thread {
    int i;
    Thread previousThread; //上一个线程

    public Thread_Join(Thread previousThread, int i) {
        this.previousThread = previousThread;
        this.i = i;
    }

    @Override
    public void run() {
        try {
            //调用上一个线程的join方法
            previousThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("num:" + i);
    }

    public static void main(String[] args) {
        Thread previousThread = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            Thread_Join joinDemo = new Thread_Join(previousThread, i);
            joinDemo.start();
            previousThread = joinDemo;
        }
    }
}

