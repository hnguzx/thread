package com.guzx.section5.disruptor;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/4 14:34
 * @describe
 */
public class Consumer2 implements EventHandler<PCData> {

    @Override
    public void onEvent(PCData event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println(Thread.currentThread().getId() + " :Event: --" + event.getData() * event.getData() + "--");
    }
}
