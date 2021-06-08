package com.guzx.section5.future;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/7 9:58
 * @describe
 */
public class Main {
    public static void main(String[] args) {
        Client client = new Client();
        FutureData data = (FutureData) client.request("name");
        System.out.println("请求完毕");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("使用真实数据");
        System.out.println("数据为：" + data.getResult());
    }
}
