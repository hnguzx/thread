package com.guzx.section5.disruptor;

import com.lmax.disruptor.WorkHandler;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/4 14:34
 * @describe
 */
public class Consumer implements WorkHandler<PCData> {
    @Override
    public void onEvent(PCData event) throws Exception {
        System.out.println(Thread.currentThread().getId() + " :Event: --" + event.getData() * event.getData() + "--");
    }
}
