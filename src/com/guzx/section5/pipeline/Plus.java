package com.guzx.section5.pipeline;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/7 11:45
 * @describe
 */
public class Plus implements Runnable {

    public static BlockingQueue<Msg> queue = new LinkedBlockingQueue<Msg>();

    @Override
    public void run() {
        while (true) {
            try {
                Msg msg = queue.take();
                msg.j = msg.i + msg.j;
                Multiply.queue.add(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
