package com.guzx.section5.jdkFuture;

import java.util.concurrent.*;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/7 10:13
 * @describe
 */
public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> data = new FutureTask<>(new RealData("guzx"));
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("gg-thread");
                return thread;
            }
        });
        executor.submit(data);
        System.out.println("请求完毕");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            data.cancel(true);
        }
        System.out.println("真实数据：" + data.get());
        executor.shutdown();
    }
}
