package com.guzx.section5.producerAndConsumer;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/4 14:20
 * @describe
 */
public class PCData {
    private final int intData;

    public PCData(int intData) {
        this.intData = intData;
    }

    public PCData(String intData) {
        this.intData = Integer.parseInt(intData);
    }

    public int getIntData() {
        return this.intData;
    }

    @Override
    public String toString() {
        return "data:" + this.intData;
    }
}
