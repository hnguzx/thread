package com.guzx.section2;

import java.util.Vector;

public class Thread_ArrayListOrVector {
    // 线程不安全
//    static ArrayList<Integer> arrayList = new ArrayList<>();
    // 线程安全
    static Vector<Integer> arrayList = new Vector<>();

    public static class AddThread implements Runnable {
        @Override
        public void run() {
//            synchronized (this){
                for (int i = 0; i < 100000; i++) {
                    arrayList.add(i);
                }
//            }
        }
    }



    public static void main(String[] args) throws InterruptedException {
//        AddThread addThread = new AddThread();

//        Thread t1 = new Thread(addThread);
//        Thread t2 = new Thread(addThread);
        Thread t1 = new Thread(new AddThread());
        Thread t2 = new Thread(new AddThread());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(arrayList.size());
    }
}
