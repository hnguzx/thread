package com.guzx.section3;

import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.*;

/**
 * @author Administrator
 */
public class Thread_ThreadPool {
    public static class MyTask implements Runnable {

        @Override
        public void run() {
            try {
                System.out.println("current time " + new Date() + "Thread ID:" + Thread.currentThread().getId());
                Thread.sleep(6000);
                System.out.println("任务执行完毕" + new Date());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 自定义线程池拒绝策略
     */
    public static class CustomerRejectedExecutionHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("discard " + r.toString());
        }
    }

    /**
     * 自定义线程任务队列
     */
    public static class CustomerQueue extends AbstractQueue implements BlockingQueue {
        @Override
        public Iterator iterator() {
            return null;
        }

        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean offer(Object o) {
            return false;
        }

        @Override
        public void put(Object o) throws InterruptedException {

        }

        @Override
        public boolean offer(Object o, long timeout, TimeUnit unit) throws InterruptedException {
            return false;
        }

        @Override
        public Object take() throws InterruptedException {
            return null;
        }

        @Override
        public Object poll(long timeout, TimeUnit unit) throws InterruptedException {
            return null;
        }

        @Override
        public int remainingCapacity() {
            return 0;
        }

        @Override
        public int drainTo(Collection c) {
            return 0;
        }

        @Override
        public int drainTo(Collection c, int maxElements) {
            return 0;
        }

        @Override
        public Object poll() {
            return null;
        }

        @Override
        public Object peek() {
            return null;
        }
    }

    /**
     * 自定义线程创建工厂
     */
    public static class CustomerThreadFactory implements ThreadFactory {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("customerThread");
            thread.setPriority(2);
            System.out.println("create thread " + thread.getName());
            return thread;
        }
    }

    public static void main(String[] args) {
        MyTask task = new MyTask();

        // 内置的创建线程池方法
        /*ExecutorService service = Executors.newFixedThreadPool(5);
        ExecutorService service = Executors.newSingleThreadExecutor();
        ExecutorService service = Executors.newCachedThreadPool();
        ScheduledExecutorService service = Executors.newScheduledThreadPool(5);
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();*/

        // 内置的任务队列
        /*SynchronousQueue<Runnable> synchronousQueue = new SynchronousQueue<>();
        ArrayBlockingQueue<Runnable> arrayBlockingQueue = new ArrayBlockingQueue<>(100);
        LinkedBlockingQueue<Runnable> linkedBlockingQueue = new LinkedBlockingQueue<>();
        PriorityBlockingQueue priorityBlockingQueue = new PriorityBlockingQueue();*/

        // 内置的拒绝策略
        /*ThreadPoolExecutor.AbortPolicy abortPolicy = new ThreadPoolExecutor.AbortPolicy();
        ThreadPoolExecutor.CallerRunsPolicy callerRunsPolicy = new ThreadPoolExecutor.CallerRunsPolicy();
        ThreadPoolExecutor.DiscardOldestPolicy discardOldestPolicy = new ThreadPoolExecutor.DiscardOldestPolicy();
        ThreadPoolExecutor.DiscardPolicy discardPolicy = new ThreadPoolExecutor.DiscardPolicy();*/

        CustomerQueue customerQueue = new CustomerQueue();
        CustomerThreadFactory customerThreadFactory = new CustomerThreadFactory();
        CustomerRejectedExecutionHandler customerRejectedExecutionHandler = new CustomerRejectedExecutionHandler();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 10, 60L, TimeUnit.SECONDS, customerQueue, customerThreadFactory, customerRejectedExecutionHandler);

        ThreadPoolExecutor expandThreadPoolExecutor = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<>()) {
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("线程准备执行");
                super.beforeExecute(t, r);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("线程执行完毕");
                super.afterExecute(r, t);
            }

            @Override
            public void terminated() {
                System.out.println("线程池被销毁");
                super.terminated();
            }
        };
        for (int i = 0; i < 10; i++) {
//            threadPoolExecutor.submit(task);
            expandThreadPoolExecutor.submit(task);
//            Future future = service.submit(thread);
            // 一次性的定时任务
//            service.schedule(task, 2, TimeUnit.SECONDS);
         /*如果延迟时间大于任务执行时间，则后续执行时间为初始时间+延迟时间
         如果延迟时间小于任务执行时间，则后续执行时间为初始时间+任务执行时间*/
            System.out.println("两秒后执行任务,后面每五秒执行一次" + new Date());
//        service.scheduleAtFixedRate(task, 2, 5, TimeUnit.SECONDS);
            /*后续执行时间为初始时间+任务执行时间+延迟时间*/
//            service.scheduleWithFixedDelay(task, 2, 5, TimeUnit.SECONDS);
        }
        expandThreadPoolExecutor.shutdown();
        threadPoolExecutor.shutdown();
//        service.shutdown();
    }
}
