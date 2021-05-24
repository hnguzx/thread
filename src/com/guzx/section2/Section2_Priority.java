package com.guzx.section2;

public class Section2_Priority {
    public static class HeightPriority extends Thread {
        static int count = 0;

        @Override
        public void run() {
            while (true) {
                synchronized (Section2_Priority.class) {
                    count++;
                    if (count > 1000000) {
                        System.out.println("HeightPriority is complete");
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
                    if (count > 1000000) {
                        System.out.println("LowPriority is complete");
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread height = new HeightPriority();
        Thread low = new LowPriority();
        height.setPriority(Thread.MAX_PRIORITY);
        low.setPriority(Thread.MIN_PRIORITY);
        low.start();
        height.start();
    }

}
