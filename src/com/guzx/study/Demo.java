package com.guzx.study;

import java.nio.ByteBuffer;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/4 18:03
 * @describe
 */
public class Demo {
    public static void main(String[] args) {
        String s = Integer.toBinaryString(10);
        System.out.println(s);
        String s2 = Integer.toBinaryString(8);
        System.out.println(s2);
        String s3 = Integer.toBinaryString(7);
        System.out.println(s3);

        int i = 10 & (8 - 1);
        int j = 8 & 7;
        System.out.println(i);
        System.out.println(j);
    }
}
