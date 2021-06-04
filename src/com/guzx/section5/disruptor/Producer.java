package com.guzx.section5.disruptor;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;


/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/4 14:19
 * @describe
 */
public class Producer {
    private final RingBuffer<PCData> ringBuffer;

    public Producer(RingBuffer<PCData> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void pushData(ByteBuffer byteBuffer) {
        long next = ringBuffer.next();
        try {
            PCData event = ringBuffer.get(next);
            event.setData(byteBuffer.getLong(0));
        } finally {
            ringBuffer.publish(next);
        }
    }
}
