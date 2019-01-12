package com.xuge.selection;

import static com.xuge.utils.Utils.swap;

public class SelectionSort {
    public static void sort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            // 寻找[i, n) 区间里的最小值
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
    }

    public static <E extends Comparable<E>> void sort(E[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            // 寻找[i, n) 区间里的最小值
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (arr[j].compareTo(arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
    }
}
