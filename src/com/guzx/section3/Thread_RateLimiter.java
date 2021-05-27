package com.guzx.section3;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/5/27 20:52
 * @describe 限流
 */
public class Thread_RateLimiter {

    // RateLimiter会按照一定的频率往桶里扔令牌，线程拿到令牌才能执行
    static RateLimiter limiter = RateLimiter.create(2);

    public static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis());
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
//            limiter.acquire();
            if (!limiter.tryAcquire()){
                continue;
            }
            new Thread(new Task()).start();
        }
    }
}
