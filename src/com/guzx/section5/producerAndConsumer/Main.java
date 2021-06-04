package com.guzx.section5.producerAndConsumer;

import java.util.concurrent.*;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/4 14:40
 * @describe
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<PCData> pcData = new LinkedBlockingQueue<>(10);
        Producer producer1 = new Producer(pcData);
        Producer producer2 = new Producer(pcData);
        Producer producer3 = new Producer(pcData);

        Consumer consumer1 = new Consumer(pcData);
        Consumer consumer2 = new Consumer(pcData);
        Consumer consumer3 = new Consumer(pcData);

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(0, 100, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        threadPool.execute(producer1);
        threadPool.execute(producer2);
        threadPool.execute(producer3);
        threadPool.execute(consumer1);
        threadPool.execute(consumer2);
        threadPool.execute(consumer3);
        Thread.sleep(10 * 1000);
        producer1.stop();
        producer2.stop();
        producer3.stop();
        Thread.sleep(3000);
        threadPool.shutdown();
    }
}
