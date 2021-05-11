package com.guzx.section2;

public class Section2_JY {
    public volatile static int i = 0;

    public static class AddThread extends Thread {
        @Override
        public void run() {
            for (i = 0; i < 1000; i++);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AddThread addThread = new AddThread();
        addThread.start();
        // 将实例线程加入到当前线程，阻塞当前线程，直到目标线程执行完毕
        addThread.join();
        System.out.println(i);
    }

}
