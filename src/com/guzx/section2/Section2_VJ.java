package com.guzx.section2;

public class Section2_VJ implements Runnable {
    static volatile int k = 0;

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            k++;
        }
    }

    volatile private static boolean ready;
    private static int number;

    public static class NoVisibility extends Thread{
        @Override
        public void run() {
            while (!ready);
            System.out.println(number);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        /*Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new Section2_VJ());
            threads[i].start();
        }

        for (int j = 0; j < 10; j++) {
            threads[j].join();
        }

        System.out.println(k);*/

        new NoVisibility().start();
        Thread.sleep(1000);
        number = 90;
        ready = true;
        Thread.sleep(5000);
    }
}
