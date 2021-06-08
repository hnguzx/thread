package com.guzx.section5;

import sun.nio.ch.ThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/7 14:24
 * @describe 并行搜索
 */
public class ParallelSearch {
    static int[] arr;
    static ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("search-thread");
            return thread;
        }
    });
    static final int thread_num = 2;
    static AtomicInteger result = new AtomicInteger(-1);

    public static int search(int searchValue, int beginPos, int endPos) {
        int i = 0;
        for (i = beginPos; i < endPos; i++) {
            if (result.get() >= 0) {
                return result.get();
            }
            if (arr[i] == searchValue) {
                if (!result.compareAndSet(-1, i)) {
                    return result.get();
                }
                return i;
            }
        }
        return -1;
    }

    public static int pSearch(int searchValue) throws ExecutionException, InterruptedException {
        int subArrSize = arr.length / thread_num + 1;
        List<Future<Integer>> futures = new ArrayList<Future<Integer>>();
        for (int i = 0; i < arr.length; i += subArrSize) {
            int end = i + subArrSize;
            if (end >= arr.length) {
                end = arr.length;
            }
            futures.add(executor.submit(new SearchTask(searchValue, i, end)));
        }
        for (Future<Integer> fut : futures) {
            if (fut.get() >= 0) {
                return fut.get();
            }
        }
        return -1;
    }

    public static class SearchTask implements Callable<Integer> {
        int begin, end, searchValue;

        public SearchTask(int searchValue, int begin, int end) {
            this.searchValue = searchValue;
            this.begin = begin;
            this.end = end;
        }

        @Override
        public Integer call() throws Exception {
            int result = search(searchValue, begin, end);
            return result;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        arr = new int[]{12, 34, 5, 6, 78, 90};
        int i = pSearch(78);
        System.out.println(i);
        executor.shutdown();
    }
}
