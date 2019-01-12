package com.xuge.stack;

public class Test {
    public static void main(String[] args) {
        testLinkStack();
    }

    private static void testArrayStack(){
        ArrayStack<Integer> arrayStack = new ArrayStack<>();

        for (int i = 0; i < 5; i++) {
            arrayStack.push(i);
        }

        System.out.println(arrayStack);
        arrayStack.pop();
        System.out.println(arrayStack);
    }

    private static void testLinkStack(){
        Stack<Integer> arrayStack = new LinkedStack<>();

        for (int i = 0; i < 5; i++) {
            arrayStack.push(i);
        }

        System.out.println(arrayStack);
        arrayStack.pop();
        System.out.println(arrayStack);
    }
}
