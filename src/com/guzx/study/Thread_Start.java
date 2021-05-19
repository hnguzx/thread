package com.guzx.study;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/5/12 10:20
 * @describe 需要解决问题：
 * 1：什么是线程
 * 什么是进程
 * 系统进行资源分配和调度的基本单位，即如果想要使用计算机，则最低需要一个进程
 * 进程包括堆，方法区，线程。堆和方法区是线程间共享的
 * 堆：主要保存new出来的对象实例
 * 方法区：主要保存静态变量，静态方法，常量，JVM线程加载的类
 * 什么是线程
 * 是进程的基本单位，CPU分配的基本单位，是轻量级进程，是程序的最小执行单位
 * 线程包含程序计数器，栈
 * 程序计数器：记录当前要执行的指令地址
 * 栈：局部变量
 * <p>
 * 2：线程的创建与启动
 * 继承Thread，实现Runnable，实现FutureTask
 * 区别：
 * 只能单继承，
 * 可以多实现，
 * 可以获取任务执行的结果，可以取消任务的执行，可以了解任务的执行情况
 */
public class Thread_Start {

    static int i = 0;

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        System.out.println(i++);
        Thread thread = new CustomerThread();
        thread.start();

        Runnable runnable = new CustomerRunnable();
        new Thread(runnable).start();
        new Thread(runnable).start();

        // 创建异步线程任务
        FutureTask<String> futureTask = new FutureTask<>(new CustomerFuture());
        // 线程启动
        new Thread(futureTask).start();
        // 获取线程执行结果
        try {
            String result = futureTask.get();
            System.out.println("异步线程执行结果：" + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        System.out.println("主线程运行结束了");
    }

    public static class CustomerThread extends Thread {

        @Override
        public void run() {
            System.out.println(i++);
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static class CustomerRunnable implements Runnable {

        int a = 0;

        @Override
        public void run() {
            System.out.println(i++);
            a++;
            System.out.println("a:" + a);
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static class CustomerFuture implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.println(i++);
            System.out.println(Thread.currentThread().getName());
            return "run success";
        }
    }
}
