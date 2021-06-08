package com.guzx.section5;

import java.util.Arrays;
import java.util.concurrent.*;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/8 10:20
 * @describe 并行希尔排序
 */
public class ParallelShellSort {

    static int[] arr;
    static ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("search-thread");
            return thread;
        }
    });

    public static void main(String[] args) throws InterruptedException {
        arr = new int[]{1, 3, 2, 5, 4, 7, 6, 9, 0, 8, 2, 4};
        pSort(arr);
        System.out.println(Arrays.toString(arr));
        executor.shutdown();
    }

    public static class ShellSortTask implements Runnable {
        // 后面数组的下标
        int i = 0;
        // 步长
        int h = 0;
        CountDownLatch latch;

        public ShellSortTask(int i, int h, CountDownLatch latch) {
            this.i = i;
            this.h = h;
            this.latch = latch;
        }

        @Override
        public void run() {
            if (arr[i] < arr[i - h]) {
                int temp = arr[i];
                int j = i - h;
                while (j >= 0 && arr[j] > temp) {
                    arr[j + h] = arr[j];
                    j -= h;
                }
                arr[j + h] = temp;
            }
            latch.countDown();
        }
    }

    public static void pSort(int[] arr) throws InterruptedException {
        int h = arr.length / 2;

        CountDownLatch latch = null;
        while (h > 0) {
            if (h >= 4) {
                latch = new CountDownLatch(arr.length - h);
            }
            for (int i = h; i < arr.length; i++) {
                if (h >= 4) {
                    executor.execute(new ShellSortTask(i, h, latch));
                } else {
                    if (arr[i] < arr[i - h]) {
                        // 后面的数
                        int temp = arr[i];
                        // 前面的下标
                        int j = i - h;
                        while (j >= 0 && arr[j] > temp) {
                            arr[j + h] = arr[j];
                            j -= h;
                        }
                        arr[j + h] = temp;
                    }
                }
            }
            if (latch != null) {
                latch.await();
            }
            h = h / 2;
        }
    }
}
