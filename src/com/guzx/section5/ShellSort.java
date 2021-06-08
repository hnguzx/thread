package com.guzx.section5;

import java.util.Arrays;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/7 17:16
 * @describe 希尔排序
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 5, 4, 7, 6, 9, 0, 8, 2};
        shellSort2(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void shellSort(int[] arr) {
        // 增量（步长），即要将原数组分解成的小数组的长度
        int increment = arr.length / 2;
        // 用于保存后面数组的数的下标
        int index = 0;
        // 用于保存后面数组的值
        int value = 0;
        while (increment > 0) {
            // 遍历后面的数组
            for (int i = increment; i < arr.length; i++) {
                index = i;
                value = arr[i];
                // 前面数组的下边大于零且前面数组的值大于后面数组的值
                while (index - increment >= 0 && arr[index - increment] > value) {
                    // 将较大的值往后移
                    arr[index] = arr[index - increment];
                    // 下标往前移
                    index -= increment;
                }
                // 将较小的值往前移
                arr[index] = value;
            }
            // 增量减小
            increment = increment / 2;
        }
    }

    public static void shellSort2(int[] arr) {
        int h = arr.length / 3;
        while (h > 0) {
            for (int i = h; i < arr.length; i++) {
                if (arr[i] < arr[i - h]) {
                    // 后面的数
                    int temp = arr[i];
                    // 前面的下标
                    int j = i - h;
                    while (j >= 0 && arr[j] > temp) {
                        arr[j + h] = arr[j];
                        j -= h;
                    }
                    arr[j + h] = temp;
                }
            }
            h = h / 3;
        }
    }
}
