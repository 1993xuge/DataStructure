package com.xuge.queue;

import com.xuge.link.LinkedList;

public class LinkedQueue<E> implements Queue<E> {
    private Node head;
    private Node tail;
    private int size;

    public LinkedQueue() {
        head = tail = null;
        size = 0;
    }

    @Override
    public void enqueue(E e) {
        if (tail == null) {
            tail = new Node(e);
            head = tail;
        } else {
            tail.next = new Node(e);
            tail = tail.next;
        }
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Can not dequeue from an empty queue.");
        }

        Node node = head;
        head = head.next;
        node.next = null;
        if (head == null) {
            tail = null;
        }
        size--;
        return node.data;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Can not getFront from an empty queue.");
        }
        return head.data;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("LoopQueue:size = %d\n", size));
        res.append("front [");
        Node cur = head;
        while (cur!= null){
            res.append(cur.data +" -> ");
            cur = cur.next;
        }
        res.append("NULL] tail");
        return res.toString();
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
