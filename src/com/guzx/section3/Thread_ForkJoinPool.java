package com.guzx.section3;

import javax.xml.stream.events.EndDocument;
import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/5/28 15:03
 * @describe 分而治之
 */
public class Thread_ForkJoinPool extends RecursiveTask<Long> {

    private static final int THRESHOLD = 10000;
    private long start, end;

    public Thread_ForkJoinPool(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum = 0;
        boolean canCompute = (end - start) < THRESHOLD;
        if (canCompute) {
            for (long i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            long step = (start + end) / 100;
            ArrayList<Thread_ForkJoinPool> subTasks = new ArrayList<>();
            long pos = start;
            for (int i = 0; i < 100; i++) {
                long lastOne = pos + step;
                if (lastOne > end) {
                    lastOne = end;
                }
                Thread_ForkJoinPool subTask = new Thread_ForkJoinPool(pos, lastOne);
                pos += step + 1;
                subTasks.add(subTask);
                subTask.fork();

            }
            for (Thread_ForkJoinPool thread_forkJoinPool : subTasks) {
                sum += thread_forkJoinPool.join();
            }
        }
        return sum;
    }


    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Thread_ForkJoinPool thread_forkJoinPool = new Thread_ForkJoinPool(0, 200000L);
    }
}
