package com.guzx.section5.future;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/7 9:48
 * @describe
 */
public class FutureData implements Data {

    protected RealData realData;
    protected Boolean isReady = false;

    public synchronized void setRealData(RealData realData) {
        if (isReady) {
            return;
        }
        this.realData = realData;
        isReady = true;
        notifyAll();
    }

    @Override
    public synchronized String getResult() {
        while (!isReady) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
        return realData.getResult();
    }
}
