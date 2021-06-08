package com.guzx.section5.jdkFuture;

import java.util.concurrent.Callable;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/7 10:10
 * @describe
 */
public class RealData implements Callable<String> {
    private String para;

    public RealData(String para) {
        this.para = para;
    }

    @Override
    public String call() throws Exception {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            stringBuffer.append(para);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return stringBuffer.toString();
    }
}
