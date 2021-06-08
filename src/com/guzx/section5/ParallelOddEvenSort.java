package com.guzx.section5;

import java.util.Arrays;
import java.util.concurrent.*;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/7 15:07
 * @describe 并行奇偶交换排序
 */
public class ParallelOddEvenSort {
    static int[] arr;
    static ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("search-thread");
            return thread;
        }
    });
    static int exchangeFlag = 1;

    static synchronized void setExchangeFlag(int v) {
        exchangeFlag = v;
    }

    static synchronized int getExchangeFlag() {
        return exchangeFlag;
    }

    public static void main(String[] args) throws InterruptedException {
        arr = new int[]{1, 3, 2, 5, 4, 7, 6, 9, 0, 8, 2};
        oddEvenSort(arr);
        System.out.println(Arrays.toString(arr));
        executor.shutdown();
    }

    public static class OddEvenSortTask implements Runnable {
        int i;
        CountDownLatch latch;

        public OddEvenSortTask(int i, CountDownLatch latch) {
            this.i = i;
            this.latch = latch;
        }

        @Override
        public void run() {
            if (arr[i] > arr[i + 1]) {
                int temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
                setExchangeFlag(1);
            }
            latch.countDown();
        }
    }

    public static void oddEvenSort(int[] arr) throws InterruptedException {
        int star = 0;
        while (getExchangeFlag() == 1 || star == 1) {
            setExchangeFlag(0);
            CountDownLatch countDownLatch = new CountDownLatch(arr.length / 2 - (arr.length % 2 == 0 ? star : 0));
            for (int i = star; i < arr.length - 1; i += 2) {
                executor.submit(new OddEvenSortTask(i, countDownLatch));
            }
            countDownLatch.await();
            if (star == 0) {
                star = 1;
            } else {
                star = 0;
            }
        }
    }
}
