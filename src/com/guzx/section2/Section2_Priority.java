package com.guzx.section2;

public class Section2_Priority {
    public static class HightPriority extends Thread {
        static int count = 0;

        @Override
        public void run() {
            while (true) {
                synchronized (Section2_Priority.class) {
                    count++;
                    if (count > 10000) {
                        System.out.println("HightPriority is complete");
                        break;
                    }
                }
            }
        }
    }

    public static class LowPriority extends Thread {
        static int count = 0;

        @Override
        public void run() {
            while (true) {
                synchronized (Section2_Priority.class) {
                    count++;
                    if (count > 10000) {
                        System.out.println("LowPriority is complete");
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread hight = new HightPriority();
        Thread low = new LowPriority();
        hight.setPriority(Thread.MAX_PRIORITY);
        low.setPriority(Thread.MIN_PRIORITY);
        low.start();
        hight.start();
    }

}
