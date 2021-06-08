package com.guzx.section5;

import java.util.Arrays;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/7 15:32
 * @describe 奇偶交换排序
 */
public class OddEvenSort {

    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 5, 4, 7, 6, 9, 0, 8, 2};
        oddEvenSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void oddEvenSort(int[] arr) {
        int exchangeFlag = 1, star = 0;
        while (exchangeFlag == 1 || star == 1) {
            System.out.println("进来一次");
            exchangeFlag = 0;
            for (int i = star; i < arr.length - 1; i += 2) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    exchangeFlag = 1;
                }
            }
            if (star == 0) {
                star = 1;
            } else {
                star = 0;
            }
        }
    }
}
