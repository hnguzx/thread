package com.guzx.section6;

import java.util.Arrays;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/8 16:45
 * @describe 对象不变
 */
public class ObjConstant {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        Arrays.stream(arr).map(x -> x * x).forEach(System.out::println);
        System.out.println();
        System.out.println(Arrays.toString(arr));
    }
}
