package com.guzx.section5.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/4 14:40
 * @describe
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
//        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(0, 100, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("customerThread");
                thread.setPriority(2);
                System.out.println("create thread " + thread.getName());
                return thread;
            }
        };
        PCDataFactory pcDataFactory = new PCDataFactory();
        int bufferSize = 1024;

        Disruptor<PCData> disruptor = new Disruptor<PCData>(pcDataFactory,
                bufferSize,
                threadFactory,
                ProducerType.SINGLE,
                new BusySpinWaitStrategy());
        // 不重复消费
        disruptor.handleEventsWithWorkerPool(
                new Consumer(),
                new Consumer(),
                new Consumer(),
                new Consumer()
        );
        // 重复消费
        /*disruptor.handleEventsWith(
                new Consumer2(),
                new Consumer2(),
                new Consumer2(),
                new Consumer2()
        );*/


        disruptor.start();

        RingBuffer<PCData> ringBuffer = disruptor.getRingBuffer();
        Producer producer = new Producer(ringBuffer);
        // 分配指定字节大小的缓冲区
        ByteBuffer allocate = ByteBuffer.allocate(8);
        for (long i = 0; i < 100; i++) {

            allocate.putLong(0, i);
            producer.pushData(allocate);
            Thread.sleep(100);
            System.out.println("add data: " + i);
        }
        disruptor.shutdown();
    }
}
