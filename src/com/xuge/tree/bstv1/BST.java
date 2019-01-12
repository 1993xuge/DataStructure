package com.xuge.tree.bstv1;

public class BST<E extends Comparable<E>> {

    private Node root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    // 向二分搜索树中 添加元素e
    public void add(E e) {
        if (root == null) {
            root = new Node(e);
            size++;
        } else {
            add(root, e);
        }
    }

    private void add(Node node, E e) {
        if (e.equals(node.data)) {
            return;
        } else if (e.compareTo(node.data) < 0 && node.left == null) {
            node.left = new Node(e);
            size++;
            return;
        } else if (e.compareTo(node.data) > 0 && node.right == null) {
            node.right = new Node(e);
            size++;
            return;
        }

        if (e.compareTo(node.data) < 0) {
            add(node.left, e);
        } else if (e.compareTo(node.data) > 0) {
            add(node.right, e);
        }
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private class Node {
        public E data;
        public Node left;
        public Node right;

        public Node(E data) {
            this.data = data;
            left = null;
            right = null;
        }
    }
}
