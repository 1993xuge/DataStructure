package com.xuge.link;

public class LinkedList<E> {
    // 虚拟头结点，data = null
    private Node head;
    private int size;

    public LinkedList() {
        head = new Node();
        size = 0;
    }

    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed, Illegal index.");
        }

        // 从虚拟的头结点 开始，而非从0号元素开始
        Node prev = head;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        /*Node node = new Node(e);
        node.next = prev.next;
        prev.next = node;*/
        prev.next = new Node(e, prev.next);
        size++;
    }

    public void addFirst(E e) {
        /*Node node = new Node(e);
        node.next = head.next;
        head.next = node;*//*
        head.next = new Node(e, head.next);
        size++;*/
        add(0, e);
    }

    public void addLast(E e) {
        add(size, e);
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed, Illegal index.");
        }
        // 从0号元素 开始遍历
        Node currentNode = head.next;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode.data;
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    public void set(int index, E data) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed, Illegal index.");
        }

        // 从0号元素 开始遍历
        Node currentNode = head.next;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        currentNode.data = data;
    }

    /**
     * 查找链表是否有元素e
     */
    public boolean contain(E e) {
        Node curNode = head.next;
        while (curNode != null) {
            if (curNode.data.equals(e)) {
                return true;
            }
            curNode = curNode.next;
        }
        return false;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed, Illegal index.");
        }

        Node prev = head;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        Node node = prev.next;
        prev.next = node.next;
        node.next = null;
        size--;

        return node.data;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    // 从链表中删除元素e
    public void removeElement(E e) {

        Node prev = head;
        while (prev.next != null) {
            if (prev.next.data.equals(e))
                break;
            prev = prev.next;
        }

        if (prev.next != null) {
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size--;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Node cur = head.next;
        while (cur != null) {
            stringBuilder.append(cur + " -> ");
            cur = cur.next;
        }
        stringBuilder.append("NULL");
        return stringBuilder.toString();
    }

    private class Node {
        public E data;
        public Node next;

        public Node() {
            this(null, null);
        }

        public Node(E data) {
            this(data, null);
        }

        public Node(E data, Node next) {
            this.data = data;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }
}
