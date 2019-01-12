package com.xuge.map;

import com.xuge.tree.avl.AVLTree;
import com.xuge.utils.FileOperation;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        LinkedListMap<String, Integer> linkedListMap = new LinkedListMap<>();
        System.out.println("Link : " + testMap(linkedListMap));

        System.out.println("===========================");

        BSTMap<String, Integer> bstMap = new BSTMap<>();
        System.out.println("BST : " +  testMap(bstMap));

        System.out.println("===========================");

        AVLMap<String, Integer> avlMap = new AVLMap<>();
        System.out.println("AVL : " +  testMap(avlMap));
    }

    private static double testMap(Map<String, Integer> map) {
        long startTime = System.nanoTime();
        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }

        System.out.println();
        return (System.nanoTime() - startTime) / 1_000_000_000.0;
    }


}
