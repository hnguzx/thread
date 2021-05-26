package com.guzx.section2;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/5/15 17:47
 * @describe
 */
public class Thread_HashMapAndConcurrentHashMap {
    // 线程不安全
//    static Map<String ,String> map = new HashMap<>();
    // 线程安全
    static Map<String ,String> map = new ConcurrentHashMap<>();

    public static class TestHashMapThread implements Runnable{

        int start = 0;

        public TestHashMapThread(int start){
            this.start = start;
        }

        @Override
        public void run() {
            for (int i =start;i<100000;i+=2){
                map.put(Integer.toString(i),Integer.toBinaryString(i));
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t3 = new Thread(new TestHashMapThread(0));
        Thread t4 = new Thread(new TestHashMapThread(1));
        t3.start();
        t4.start();
        t3.join();
        t4.join();
        System.out.println(map.size());
    }
}
