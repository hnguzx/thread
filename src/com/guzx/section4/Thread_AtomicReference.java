package com.guzx.section4;

import com.google.common.util.concurrent.AtomicDouble;
import com.google.common.util.concurrent.AtomicDoubleArray;

import java.util.concurrent.atomic.*;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/1 16:55
 * @describe
 */
public class Thread_AtomicReference {
    //    static AtomicReference<Integer> money = new AtomicReference<Integer>();
    static AtomicStampedReference<Integer> money = new AtomicStampedReference<Integer>(19, 0);

    public static void main(String[] args) {
//        money.set(19);

        Integer stamp = money.getStamp();
        // 充值
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    while (true) {
//                        Integer m = money.get();
                        Integer m = money.getReference();
                        if (m < 20) {
                            System.out.println("余额小于20");
//                            if (money.compareAndSet(m, m + 20)) {
                            if (money.compareAndSet(m, m + 20, stamp, stamp + 1)) {
//                                System.out.println("余额小于二十，充值成功，余额：" + money.get());
                                System.out.println("余额小于二十，充值成功，余额：" + money.getReference());
                            }
                        } else {
                            break;
                        }
                    }
                }
            }
        }.start();

        // 消费
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    while (true) {
                        Integer t = money.getStamp();
                        Integer m = money.getReference();
//                        Integer m = money.get();
                        if (m > 10) {
                            System.out.println("余额大于10");
//                            if (money.compareAndSet(m, m - 10)) {
                            if (money.compareAndSet(m, m - 10, t, t + 1)) {
                                System.out.println("成功消费10，余额：" + money.getReference());
//                                System.out.println("成功消费10，余额：" + money.get());
                            }
                        } else {
                            System.out.println("余额不足");
                            break;
                        }
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
