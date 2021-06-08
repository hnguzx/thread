package com.guzx.section5.future;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/7 9:55
 * @describe
 */
public class Client {
    public Data request(final String queryStr) {
        final FutureData futureData = new FutureData();
        new Thread() {
            @Override
            public void run() {
                RealData realData = new RealData(queryStr);
                futureData.setRealData(realData);
            }
        }.start();
        return futureData;
    }
}
