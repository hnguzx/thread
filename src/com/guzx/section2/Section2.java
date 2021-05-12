package com.guzx.section2;

public class Section2 implements Runnable {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("新线程的方法执行了！");
                int i = 0;
                while (true) {
                    System.out.println(i++);
                }
            }
        };
        thread.start();
//        thread.run();
        Section2 thread2 = new Section2();
        thread2.run();

        Thread thread3 = new Thread(new Section2());
        thread3.start();
        // 不建议使用，可能会破坏数据的一致性
        thread.stop();

        Thread thread4 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("这个线程被中断了！");
                        interrupted();
                        break;
                    }
                    yield();
                }
            }
        };
        thread4.start();
        Thread.sleep(2000);
        thread4.interrupt();
        // 线程中断后相当于当前线程已经结束，需要运行的话应该重新new一个新的线程
//        thread4.start();
//        Thread.sleep(2000);
//        thread4.interrupt();

        Thread thread5 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("线程已经停了！");
                        break;
                    }
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        System.out.println("sleep的时候被中断了");
                        Thread.currentThread().interrupt();
                    }
                    Thread.yield();


                }
            }
        };

        thread5.start();
        Thread.sleep(2000);
        thread5.interrupt();
    }

    @Override
    public void run() {
        System.out.println("我是主线程");
    }
}
