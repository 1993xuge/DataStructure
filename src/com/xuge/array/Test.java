package com.xuge.array;

public class Test {
    public static void main(String[] args) {
        Array<String> array = new Array(20);
        for (int i = 0; i < 10; i++) {
            array.addLast("i" + i);
        }
        System.out.println(array);
    }
}
