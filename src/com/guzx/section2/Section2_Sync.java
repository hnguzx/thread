package com.guzx.section2;

public class Section2_Sync implements Runnable {

    static Section2_Sync instance = new Section2_Sync();
    static volatile int i = 0;

    public static synchronized void increase() {
        i++;
    }

    @Override
    public void run() {
        synchronized (instance){
            for (int j = 0; j < 1000000; j++) {
                increase();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(instance);
        Thread thread2 = new Thread(instance);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(i);
    }
}
