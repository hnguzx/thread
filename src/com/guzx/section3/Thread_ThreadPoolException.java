package com.guzx.section3;

import java.util.concurrent.*;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/5/28 14:36
 * @describe
 */
public class Thread_ThreadPoolException implements Runnable {
    int a, b;

    public Thread_ThreadPoolException(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        double c = a / b;
        System.out.println(c);
    }

    public static void main(String[] args) {
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5, 10L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        TraceThreadPoolExecutor traceThreadPoolExecutor = new TraceThreadPoolExecutor(5, 5, 0L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        for (int i = 0; i < 5; i++) {
//            threadPoolExecutor.execute(new Thread_ThreadPoolException(100, i));
//            Future<?> future = threadPoolExecutor.submit(new Thread_ThreadPoolException(100, i));
            Future<?> future = traceThreadPoolExecutor.submit(new Thread_ThreadPoolException(100, i));
            try {
                future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
//        threadPoolExecutor.shutdown();
        traceThreadPoolExecutor.shutdown();
    }
}
