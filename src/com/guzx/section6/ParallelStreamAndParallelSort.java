package com.guzx.section6;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.Random;
import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/11 15:38
 * @describe 并行流和并行排序
 */
public class ParallelStreamAndParallelSort {
    public static void main(String[] args) {
//        System.out.println(System.currentTimeMillis());
        long count = IntStream.range(1, 1000000).parallel().filter(value -> {
            int temp = value;
            if (temp < 2) {
                return false;
            }
            for (int i = 2; Math.sqrt(temp) >= i; i++) {
                if (temp % i == 0) {
                    return false;
                }
            }
            return true;
        }).count();
//        System.out.println(System.currentTimeMillis());
//        System.out.println(count);

        // >= 2 && < 10
        double asDouble = IntStream.range(2, 10).average().getAsDouble();
        System.out.println(asDouble);

        int[] arr = new int[]{6, 3, 7, 2, 8};
        Arrays.parallelSort(arr);
        System.out.println(Arrays.toString(arr));

        Arrays.parallelSetAll(arr, operand -> (int) (Math.random()*10));
        Arrays.parallelSort(arr);
        System.out.println(Arrays.toString(arr));

    }
}
