package com.guzx.section5.pipeline;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/7 11:50
 * @describe
 */
public class Div implements Runnable {
    public static BlockingQueue<Msg> queue = new LinkedBlockingQueue<Msg>();

    @Override
    public void run() {
        while (true) {
            try {
                Msg msg = queue.take();
                msg.i = msg.i / 2;
                System.out.println(msg.orgStr + "=" + msg.i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
