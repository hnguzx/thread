package com.guzx.section2;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/5/15 17:47
 * @describe
 */
public class Thread_Interrupt {

    public static void main(String[] args) {
        RunnableThread thread = new RunnableThread();

        Thread t1 = new Thread(thread);
        t1.start();
//        t1.interrupt();
    }


    public static class RunnableThread implements Runnable {

        @Override
        public void run() {
            while (true) {
                // 需要对中断标志进行处理，interrupt才能有效
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Thread Interrupted");
                    break;
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
                Thread.yield();
            }

        }
    }
}
