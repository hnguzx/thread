package com.guzx.section3;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/5/31 18:07
 * @describe 性能测试
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.SECONDS)
public class JMH {

    /*@Benchmark
    public void testJmh() {
    }*/

    /*@Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void testThroughput() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);
    }*/

    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void testSampleTime() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(JMH.class.getSimpleName())
                .forks(1).build();
        new Runner(options).run();
    }
}
