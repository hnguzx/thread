package com.guzx.section6;

import java.util.function.Consumer;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/8 14:49
 * @describe
 */
public class Demo {

    public static void main(String[] args) {
        // 函数式接口只有一个抽象方法
        /*Consumer consumer = (o) -> {
            System.out.println(o+"123");
        };*/
        Consumer consumer = System.out::println;
        consumer.accept("hello");
    }
}
