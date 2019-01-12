package com.xuge.insert;

import com.xuge.selection.SelectionSort;
import com.xuge.utils.SortTestHelper;

public class Test {
    public static final void main(String[] args) {
        testXingneng();
    }

    private static void testXingneng() {
        int N = 20000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 100000);
        SortTestHelper.testSort(InsertionSort.class.getName(), arr);
        SortTestHelper.testSort(SelectionSort.class.getName(), arr);

    }
}
