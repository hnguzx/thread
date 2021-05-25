package com.guzx.section2;

public class Thread_Daemon {


    public static class DaemonT extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("I'm alive");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new DaemonT();
        thread.setDaemon(true);
        thread.start();
//        thread.setDaemon(true);
        Thread.sleep(1000);
    }


}
