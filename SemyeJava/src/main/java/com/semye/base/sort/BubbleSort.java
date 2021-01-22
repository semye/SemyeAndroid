package com.semye.base.sort;

import java.util.Arrays;

/**
 * Created by semye on 2020/12/7
 * 冒泡排序
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] array = {5, 999, 99, 48, 23, 111, 32, 54, 77, 323};
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(array));
    }
}
