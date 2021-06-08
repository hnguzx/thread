package com.guzx.section5.future;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/7 9:48
 * @describe
 */
public class RealData implements Data {

    protected final String result;

    public RealData(String result) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            stringBuffer.append(result);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
        this.result = stringBuffer.toString();
    }

    @Override
    public String getResult() {
        return this.result;
    }
}
