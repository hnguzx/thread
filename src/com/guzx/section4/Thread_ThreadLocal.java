package com.guzx.section4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/1 10:39
 * @describe
 */
public class Thread_ThreadLocal {
//    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>();

    public static class ParseDate implements Runnable {
        int i = 0;
//        private ReentrantLock lock = new ReentrantLock();

        public ParseDate(int i) {
            this.i = i;
        }

        @Override
        public void run() {
//            lock.lock();
            try {
                if (threadLocal.get()==null){
                    threadLocal.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
                }
//                Date date = DATE_FORMAT.parse("2021-06-01 10:43:" + i % 60);
                Date date = threadLocal.get().parse("2021-06-01 10:43:" + i % 60);
                System.out.println(i + ":" + date);
            } catch (ParseException e) {
                e.printStackTrace();
            } finally {
//                lock.unlock();
                threadLocal.remove();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new ParseDate(i));
        }
        executorService.shutdown();
    }
}
