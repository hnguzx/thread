package com.guzx.section5.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/4 14:19
 * @describe
 */
public class PCDataFactory implements EventFactory<PCData> {
    @Override
    public PCData newInstance() {
        return new PCData();
    }
}
