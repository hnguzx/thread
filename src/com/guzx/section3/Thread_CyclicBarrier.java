package com.guzx.section3;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Thread_CyclicBarrier {
    public static class Soldier implements Runnable {

        private String soldier;
        private final CyclicBarrier cyclicBarrier;

        public Soldier(String soldier, CyclicBarrier cyclicBarrier) {
            this.soldier = soldier;
            this.cyclicBarrier = cyclicBarrier;
        }


        @Override
        public void run() {
            try {
                // 等待所有士兵到齐，即所有线程都已经调用star方法
                System.out.println("士兵"+Thread.currentThread().getName()+"到达第一道栅栏");
                cyclicBarrier.await();
                System.out.println("第一道栅栏已过");
                if (Thread.currentThread().getName().equals("5")) {
//                    notWork();
                } else {
                    doWork();
                }
                // 等待所有士兵任务完成，即前面的工作完成，并不要是同一个任务，甚至可以没有任务
                // 即这个线程已经运行到这里即可
                System.out.println("士兵"+Thread.currentThread().getName()+"到达第二道栅栏");
                cyclicBarrier.await();
                System.out.println("第二道栅栏已过");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        void doWork() {
            try {
                Thread.sleep(Math.abs(new Random().nextInt() % 10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(soldier + "任务完成");
        }

        void notWork() {
            try {
                Thread.sleep(Math.abs(new Random().nextInt() % 10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(soldier + "不做任务");
        }

        public static class BarrierRun implements Runnable {

            boolean flag;
            int N;

            public BarrierRun(boolean flag, int n) {
                this.flag = flag;
                this.N = n;
            }

            @Override
            public void run() {
                if (flag) {
                    System.out.println("任务完成");
                } else {
                    System.out.println("集合完毕");
                    flag = true;
                }
            }
        }


    }

    public static void main(String[] args) {
        final int N = 10;
        Thread[] allSoldier = new Thread[N];
        boolean flag = false;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(N, new Soldier.BarrierRun(flag, N));
        System.out.println("集合队伍");
        for (int i = 0; i < N; ++i) {
            System.out.println("士兵" + i + "报道！");
            // 需要N个与CyclicBarrier绑定的对象都执行了同一方法
            allSoldier[i] = new Thread(new Soldier("士兵" + i, cyclicBarrier), Integer.toString(i));
            allSoldier[i].start();
        }
    }
}
