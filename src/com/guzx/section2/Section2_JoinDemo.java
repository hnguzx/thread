package com.guzx.section2;

public class Section2_JoinDemo extends Thread {
    int i;
    Thread previousThread; //上一个线程

    public Section2_JoinDemo(Thread previousThread, int i) {
        this.previousThread = previousThread;
        this.i = i;
    }

    @Override
    public void run() {
        try {
            //调用上一个线程的join方法，大家可以自己演示的时候可以把这行代码注释掉
            previousThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("num:" + i);
    }

    public static void main(String[] args) {
        Thread previousThread = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            Section2_JoinDemo joinDemo = new Section2_JoinDemo(previousThread, i);
            joinDemo.start();
            previousThread = joinDemo;
        }
    }
}

