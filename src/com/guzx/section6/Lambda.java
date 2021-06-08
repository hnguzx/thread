package com.guzx.section6;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/8 16:51
 * @describe 匿名函数
 */
public class Lambda {
    public static void main(String[] args) {
        Collection<Integer> collection = new ArrayList<Integer>();
        collection.add(3);
        collection.add(4);
        collection.add(5);
        collection.add(6);

        collection.forEach((Integer value) -> System.out.println(value));

        int count = 3;
        Function<Integer, Integer> stringConverter = (from) -> from * count;
        System.out.println(stringConverter.apply(5));
    }
}
