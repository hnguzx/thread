package com.guzx.section6;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/15 16:22
 * @describe
 */
public class AtomicEnhance {
    private static final int MAX_THREADS = 3;
    private static final int TASK_COUNT = 3;
    private static final int TARGET_COUNT = 1000000000;

    private static AtomicLong acount = new AtomicLong(0L);
    private static LongAdder lacount = new LongAdder();
    private long lcount = 0;

    static CountDownLatch countDownLatchSync = new CountDownLatch(TASK_COUNT);
    static CountDownLatch countDownLatchAtomic = new CountDownLatch(TASK_COUNT);
    static CountDownLatch countDownLatchAdder = new CountDownLatch(TASK_COUNT);

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREADS);
        AtomicEnhance enhance = new AtomicEnhance();
        long starttime = System.currentTimeMillis();

//        SyncThread thread1 = new SyncThread(enhance, starttime);
//        AtomicThread thread2 = new AtomicThread(starttime);
        AdderThread thread3 = new AdderThread(starttime);
        for (int i = 0; i < TASK_COUNT; i++) {
//            executorService.submit(thread1);
//            executorService.submit(thread2);
            executorService.submit(thread3);
        }
//        countDownLatchSync.await();
//        countDownLatchAtomic.await();
        countDownLatchAdder.await();
        executorService.shutdown();
    }

    // 使用锁
    protected synchronized long inc() {
        return ++lcount;
    }

    protected synchronized long getLcount() {
        return lcount;
    }

    public static class SyncThread implements Runnable {
        protected String name;
        protected long starttime;
        AtomicEnhance enhance;

        public SyncThread(AtomicEnhance enhance, long starttime) {
            this.enhance = enhance;
            this.starttime = starttime;
        }

        @Override
        public void run() {
            long v = enhance.getLcount();
            while (v < TARGET_COUNT) {
                v = enhance.inc();
            }
            long endtime = System.currentTimeMillis();
            System.out.println("SyncThread spend:" + (endtime - starttime) + " ms" + " v=" + v);
            countDownLatchSync.countDown();
        }
    }

    public static class AtomicThread implements Runnable {
        protected long starttime;

        public AtomicThread(long starttime) {
            this.starttime = starttime;
        }

        @Override
        public void run() {
            long v = acount.get();
            while (v < TARGET_COUNT) {
                v = acount.incrementAndGet();
            }
            long endtime = System.currentTimeMillis();
            System.out.println("AtomicThread spend:" + (endtime - starttime) + " ms" + " v=" + v);
            countDownLatchAtomic.countDown();
        }
    }

    public static class AdderThread implements Runnable {
        protected long starttime;

        public AdderThread(long starttime) {
            this.starttime = starttime;
        }

        @Override
        public void run() {
            long v = lacount.sum();
            while (v < TARGET_COUNT) {
                lacount.increment();
                v = lacount.sum();
            }
            long endtime = System.currentTimeMillis();
            System.out.println("AdderThread spend:" + (endtime - starttime) + " ms" + " v=" + v);
            countDownLatchAdder.countDown();
        }
    }
}
