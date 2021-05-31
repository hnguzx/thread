package com.guzx.section3;

import java.util.*;
import java.util.concurrent.*;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/5/31 16:33
 * @describe 线程安全的集合类
 */
public class Thread_Collections {

    public static void main(String[] args) {
        Map<String, String> map = Collections.synchronizedMap(new HashMap<String, String>());
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<String, String>(10);

        List<Object> list = Collections.synchronizedList(new LinkedList<>());
        ArrayList<String> arrayList = new ArrayList<>();
        Vector<String> vector = new Vector<>();

        ConcurrentLinkedQueue<String> linkedQueue = new ConcurrentLinkedQueue<String>();

        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<String>();

        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<String>(10);

        ConcurrentSkipListMap<Integer, Integer> skipListMap = new ConcurrentSkipListMap<Integer, Integer>();
        for (int i = 0; i < 30; i++) {
            skipListMap.put(i, i);
        }

        for (Map.Entry<Integer, Integer> entry : skipListMap.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
}
