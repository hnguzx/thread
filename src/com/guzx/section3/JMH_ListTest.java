package com.guzx.section3;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/5/31 19:02
 * @describe
 */
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class JMH_ListTest {
    CopyOnWriteArrayList smallCopyOnWriteArrayList = new CopyOnWriteArrayList();
    ConcurrentLinkedQueue smallConcurrentLinkedQueue = new ConcurrentLinkedQueue();

    CopyOnWriteArrayList bigCopyOnWriteArrayList = new CopyOnWriteArrayList();
    ConcurrentLinkedQueue bigConcurrentLinkedQueue = new ConcurrentLinkedQueue();

    @Setup
    public void setup(){
        for (int i=0;i<10;i++){
            smallConcurrentLinkedQueue.add(new Object());
            smallCopyOnWriteArrayList.add(new Object());
        }

        for (int i=0;i<1000;i++){
            bigConcurrentLinkedQueue.add(new Object());
            bigCopyOnWriteArrayList.add(new Object());
        }
    }

    @Benchmark
    public void copyOnWriteGet(){
        smallCopyOnWriteArrayList.get(0);
    }

    @Benchmark
    public void copyOnWriteSize(){
        smallCopyOnWriteArrayList.size();
    }

    @Benchmark
    public void concurrentLinkGet(){
        smallConcurrentLinkedQueue.peek();
    }

    @Benchmark
    public void concurrentLinkSize(){
        smallConcurrentLinkedQueue.size();
    }

    @Benchmark
    public void smallConcurrentLinkedQueueWrite(){
        smallConcurrentLinkedQueue.add(new Object());
        smallConcurrentLinkedQueue.remove(0);
    }

    @Benchmark
    public void smallCopyOnWriteArrayListWrite(){
        smallCopyOnWriteArrayList.add(new Object());
        smallCopyOnWriteArrayList.remove(0);
    }

    @Benchmark
    public void bigConcurrentLinkedQueueWrite(){
        bigConcurrentLinkedQueue.add(new Object());
        bigConcurrentLinkedQueue.remove(0);
    }

    @Benchmark
    public void bigCopyOnWriteArrayListWrite(){
        bigCopyOnWriteArrayList.add(new Object());
        bigCopyOnWriteArrayList.remove(0);
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(JMH.class.getSimpleName())
                .forks(4).build();
        new Runner(options).run();
    }
}
