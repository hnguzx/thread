package com.guzx.section5;

import java.util.Arrays;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/7 16:49
 * @describe 冒泡排序
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 5, 4, 7, 6, 9, 0, 8, 2};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void bubbleSort(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
