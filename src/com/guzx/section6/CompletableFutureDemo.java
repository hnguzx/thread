package com.guzx.section6;

import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/11 17:06
 * @describe
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<Integer> completableFuture = new CompletableFuture<Integer>();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r);
            }
        });
        threadPoolExecutor.execute(new AskThread(completableFuture));
        Thread.sleep(3000);
        completableFuture.complete(60);
        threadPoolExecutor.shutdown();

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> calc(10));
//        System.out.println(future.get());

        // 异常处理
        CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return calc(10);
            }
        }).thenApply(new Function<Integer, String>() {
            @Override
            public String apply(Integer o) {
                return o.toString();
            }
        }).thenAccept(new Consumer<String>() {
            @Override
            public void accept(String o) {
                System.out.println(o);
            }
        }).exceptionally(ex -> {
            ex.printStackTrace();
            return null;
        });
        Thread.sleep(1000);
        System.out.println();

    }

    public static class AskThread implements Runnable {
        CompletableFuture<Integer> future = null;

        public AskThread(CompletableFuture<Integer> future) {
            this.future = future;
        }

        @Override
        public void run() {
            int result = 0;
            try {
                result = future.get() * future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            System.out.println(result);
        }
    }

    public static Integer calc(Integer para) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return para * para;
//        return para /0;
    }
}
