package com.xuge.tree.avl;

import com.xuge.map.BSTMap;
import com.xuge.utils.FileOperation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test {
    public static void main(String[] args) {

        testBalance();
//        testXingneng();
    }

    private static void testXingneng() {
        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

             Collections.sort(words);

            // Test BST
            testBST(words);

            testAVL(words);
        }

        System.out.println();
    }

    private static void testBST(List<String> words) {
        long startTime = System.nanoTime();

        BSTMap<String, Integer> bst = new BSTMap<>();
        for (String word : words) {
            if (bst.contains(word))
                bst.set(word, bst.get(word) + 1);
            else
                bst.add(word, 1);
        }

        for (String word : words)
            bst.contains(word);

        long endTime = System.nanoTime();

        double time = (endTime - startTime) / 1000000000.0;
        System.out.println("BST: " + time + " s");
    }

    private static void testAVL(List<String> words) {
        // Test AVL Tree
        long startTime = System.nanoTime();

        AVLTree<String, Integer> avl = new AVLTree<>();
        for (String word : words) {
            if (avl.contains(word))
                avl.set(word, avl.get(word) + 1);
            else
                avl.add(word, 1);
        }

        for (String word : words)
            avl.contains(word);

        long endTime = System.nanoTime();

        double time = (endTime - startTime) / 1000000000.0;
        System.out.println("AVL: " + time + " s");
    }

    private static void testBalance() {
        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            AVLTree<String, Integer> map = new AVLTree<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
            System.out.println("isBalanced = " + map.isBalanced());

            for(String word: words){
                map.remove(word);
                if(!map.isBST() || !map.isBalanced())
                    throw new RuntimeException();
            }

        }

        System.out.println("End");
    }
}
