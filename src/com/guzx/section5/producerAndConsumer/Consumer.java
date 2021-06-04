package com.guzx.section5.producerAndConsumer;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/4 14:34
 * @describe
 */
public class Consumer implements Runnable {

    private BlockingQueue<PCData> queue;
    private static final int SLEEPTIME = 100;

    public Consumer(BlockingQueue<PCData> queue){
        this.queue =queue;
    }

    @Override
    public void run() {
        System.out.println("start consumer id: "+Thread.currentThread().getId());
        Random random = new Random();
        try {
            while (true){
                PCData data = queue.take();
                if (data != null){
                    int re = data.getIntData() * data.getIntData();
                    System.out.println(MessageFormat.format("{0}*{1}={2}",data.getIntData(),data.getIntData(),re));
                    Thread.sleep(random.nextInt(SLEEPTIME));
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
