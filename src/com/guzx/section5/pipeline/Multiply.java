package com.guzx.section5.pipeline;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/7 11:49
 * @describe
 */
public class Multiply implements Runnable {
    public static BlockingQueue<Msg> queue = new LinkedBlockingQueue<Msg>();

    @Override
    public void run() {
        while (true){
            try {
                Msg msg = queue.take();
                msg.i = msg.i*msg.j;
                Div.queue.add(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
