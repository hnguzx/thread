package com.guzx.section4;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/1 18:10
 * @describe
 */
public class Thread_AtomicFieldUpdater {
    public static class Candidate {
        int id;
        volatile int score;

    }

    public final static AtomicIntegerFieldUpdater<Candidate> scoreUpdater = AtomicIntegerFieldUpdater.newUpdater(Candidate.class, "score");
    public static AtomicInteger allScore = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        final Candidate candidate = new Candidate();
        Thread[] threads = new Thread[10000];
        for (int i = 0; i < 10000; i++) {
            threads[i] = new Thread() {
                @Override
                public void run() {
                    if (Math.random() > 0.4) {
                        scoreUpdater.incrementAndGet(candidate);
                        allScore.incrementAndGet();
                    }
                }
            };
            threads[i].start();
            threads[i].join();
        }

        System.out.println("score = " + candidate.score);
        System.out.println("allScore = " + allScore);
    }
}
