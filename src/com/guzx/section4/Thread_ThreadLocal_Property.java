package com.guzx.section4;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/1 14:04
 * @describe
 */
public class Thread_ThreadLocal_Property {
    public static final int gen_count = 10000000;
    public static final int thread_count = 4;
    static ExecutorService executorService = Executors.newFixedThreadPool(thread_count);
    public static Random random = new Random(123);

    public static ThreadLocal<Random> threadLocal = new ThreadLocal<Random>() {
        @Override
        protected Random initialValue() {
            return new Random(123);
        }
    };

    public static class RandomTask implements Callable<Long> {
        private int mode = 0;

        public RandomTask(int mode) {
            this.mode = mode;
        }

        public Random getRandom() {
            if (mode == 0) {
                return random;
            } else if (mode == 1) {
                return threadLocal.get();
            } else {
                return null;
            }
        }

        @Override
        public Long call() throws Exception {
            long b = System.currentTimeMillis();
            for (int i = 0; i < gen_count; i++) {
                getRandom().nextInt();
            }
            long e = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + "speed" + (e - b) + " ms");
            return e - b;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Future<Long>[] future = new Future[thread_count];
        for (int i = 0; i < thread_count; i++) {
            future[i] = executorService.submit(new RandomTask(0));
        }
        Long totalTime = 0L;
        for (int j = 0; j < thread_count; j++) {
            totalTime += future[j].get();
        }
        System.out.println("多线程访问一个实例：" + totalTime + "ms");

        for (int i = 0; i < thread_count; i++) {
            future[i] = executorService.submit(new RandomTask(1));
        }
        totalTime = 0L;
        for (int j = 0; j < thread_count; j++) {
            totalTime += future[j].get();
        }
        System.out.println("使用ThreadLocal包装实例：" + totalTime + "ms");
        executorService.shutdown();
    }
}
