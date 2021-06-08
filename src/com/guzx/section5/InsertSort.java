package com.guzx.section5;

import java.util.Arrays;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/7 16:48
 * @describe 插入排序
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 5, 4, 7, 6, 9, 0, 8, 2};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void insertSort(int[] arr) {
        int i, j, key;
        for (i = 1; i < arr.length; i++) {
            key = arr[i];
            j = i - 1;
            while (j >= 0 && key < arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}
