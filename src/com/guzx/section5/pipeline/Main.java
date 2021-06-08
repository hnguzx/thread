package com.guzx.section5.pipeline;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/7 11:53
 * @describe (a + b)*a/2
 */
public class Main {
    public static void main(String[] args) {
        new Thread(new Plus()).start();
        new Thread(new Multiply()).start();
        new Thread(new Div()).start();

        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                Msg msg = new Msg();
                msg.i = i;
                msg.j = j;
                msg.orgStr = "((" + i + "+" + j + ")*" + i + ")/2";
                Plus.queue.add(msg);
            }
        }
    }
}
