package com.guzx.section6;

import java.util.Arrays;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/8 16:39
 * @describe 声明式调用
 */
public class DeclarativeFunction {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        // 命令式调用
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 3) {
                System.out.print(arr[i]);
            }
        }
        System.out.println();
        // 声明式调用
        Arrays.stream(arr).filter(x -> x > 3).forEach(System.out::print);
    }
}
