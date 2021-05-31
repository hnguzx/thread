package com.guzx.section3;

import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/5/31 10:13
 * @describe
 */
public class Thread_Guava_ThreadPool {
    public static void main(String[] args) {
        Executor executor = MoreExecutors.directExecutor();
        executor.execute(() -> {
            System.out.println("I'm running in " + Thread.currentThread().getName());
        });

        ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        ExecutorService exitingExecutorService = MoreExecutors.getExitingExecutorService(pool);
        exitingExecutorService.execute(() -> {
            System.out.println("I'm running in " + Thread.currentThread().getName());
        });
    }
}
