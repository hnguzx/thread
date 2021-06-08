package com.guzx.section5.jdkFuture;

import com.google.common.util.concurrent.*;

import java.util.concurrent.*;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/7 10:26
 * @describe
 */
public class GuavaMain {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("gg-thread");
                return thread;
            }
        });
        // future线程池
        ListeningExecutorService service = MoreExecutors.listeningDecorator(executor);
        ListenableFuture<String> task = service.submit(new RealData("lianghong"));

        /*future.addListener(() -> {
            System.out.println("异步处理成功");
            try {
                System.out.println(task.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }, MoreExecutors.directExecutor());*/

        Futures.addCallback(task, new FutureCallback<String>() {
            @Override
            public void onSuccess(String s) {
                System.out.println("调用成功：" + s);
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("调用失败,e=" + throwable);
            }
        }, MoreExecutors.directExecutor());
        System.out.println("main task done");
        Thread.sleep(3000);
        executor.shutdown();
    }
}
