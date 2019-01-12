package com.xuge.insert;

import static com.xuge.utils.Utils.swap;

public class InsertionSort {
    // 我们的算法类不允许产生任何实例
    private InsertionSort() {
    }

    public static void sort(Comparable[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            // 寻找元素arr[i]合适的插入位置，
            // arr[i]是待插入的元素，其前面的[0, i-1]个元素已经是有序的
            // j的取值范围是[1,i]
//            for (int j = i; j > 0; j--) {
//                if (arr[j].compareTo(arr[j - 1]) < 0) {
//                    swap(arr, j, j - 1);
//                } else {
//                    break;
//                }
//            }

            // 写法2
//            for( int j = i; j > 0 && arr[j].compareTo(arr[j-1]) < 0 ; j--)
//                swap(arr, j, j-1);

            // 写法3
            Comparable e = arr[i];
            int j = i;
            for (; j > 0 && arr[j - 1].compareTo(e) > 0; j--) {
                // 把所有大于e的元素，都往后挪一位
                arr[j] = arr[j - 1];
            }
            // 把e放到正确位置。
            arr[j] = e;

            // 前两种写法，每次都需要交换，导致性能降低
        }
    }
}
