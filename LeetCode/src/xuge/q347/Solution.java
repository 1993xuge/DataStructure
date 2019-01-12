package xuge.q347;

import java.util.*;

public class Solution {

    private class Freq implements Comparable<Freq> {
        int e;
        int freq;

        public Freq(int e, int freq) {
            this.e = e;
            this.freq = freq;
        }

        @Override
        public int compareTo(Freq o) {
            // 频次低，反而返回1
            if (this.freq < o.freq) {
                return 1;
            } else if (this.freq > o.freq) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
        }

        PriorityQueue<Freq> priorityQueue = new PriorityQueue<>();
        for (int key : map.keySet()) {
            if (priorityQueue.getSize() < k) {
                priorityQueue.enqueue(new Freq(key, map.get(key)));
            } else if (map.get(key) > priorityQueue.getFront().freq) {
                priorityQueue.dequeue();
                priorityQueue.enqueue(new Freq(key, map.get(key)));
            }
        }

        LinkedList<Integer> linkedList = new LinkedList<>();
        while (!priorityQueue.isEmpty()) {
            linkedList.add(priorityQueue.dequeue().e);
        }

        return linkedList;
    }

    interface Queue<E> {
        void enqueue(E e);

        E dequeue();

        E getFront();

        int getSize();

        boolean isEmpty();
    }

    class PriorityQueue<E extends Comparable<E>> implements Queue<E> {

        private MaxHeap<E> maxHeap;

        public PriorityQueue() {
            maxHeap = new MaxHeap<>();
        }

        @Override
        public void enqueue(E e) {
            maxHeap.add(e);
        }

        @Override
        public E dequeue() {
            return maxHeap.extractMax();
        }

        @Override
        public E getFront() {
            return maxHeap.findMax();
        }

        @Override
        public int getSize() {
            return maxHeap.size();
        }

        @Override
        public boolean isEmpty() {
            return maxHeap.isEmpty();
        }
    }

    class Array<E> {
        private static final int DEFAULT_CAPACITY = 10;
        private E[] data;
        private int size;

        public Array() {
            this(DEFAULT_CAPACITY);
        }

        public Array(int capacity) {
            data = (E[]) new Object[capacity];
            size = 0;
        }

        public Array(E[] data) {
            this.data = (E[]) new Object[data.length];
            for (int i = 0; i < data.length; i++) {
                this.data[i] = data[i];
            }
            size = data.length;
        }

        public int getSize() {
            return size;
        }

        public int getCapacity() {
            return data.length;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void addLast(E e) {
            add(size, e);
        }

        public void addFirst(E e) {
            add(0, e);
        }

        public void add(int index, E e) {
            if (index < 0 || index > size) {
                throw new IllegalArgumentException("Add Failed, Require index >=0 and index <= size");
            }

            if (size == data.length) {
                resize(2 * data.length);
            }

            for (int i = size - 1; i >= index; i--) {
                data[i + 1] = data[i];
            }
            data[index] = e;
            size++;
        }

        public E get(int index) {
            if (index < 0 || index > size) {
                throw new IllegalArgumentException("Get Failed, index is Illegal");
            }
            return data[index];
        }

        public E getLast() {
            return get(size - 1);
        }

        public E getFirst() {
            return get(0);
        }

        public void set(int index, E e) {
            if (index < 0 || index > size) {
                throw new IllegalArgumentException("Set Failed, index is Illegal");
            }
            data[index] = e;
        }

        public boolean contains(E e) {
            for (int i = 0; i < size; i++) {
                if (data[i].equals(e)) {
                    return true;
                }
            }

            return false;
        }

        public int find(E e) {
            for (int i = 0; i < size; i++) {
                if (data[i].equals(e)) {
                    return i;
                }
            }

            return -1;
        }

        public E remove(int index) {
            if (index < 0 || index >= size) {
                throw new IllegalArgumentException("Remove Failed, index is Illegal");
            }

            E value = data[index];
            for (int i = index; i < size - 1; i++) {
                data[i - 1] = data[i];
            }
            size--;
            data[size] = null;
            if (size == data.length / 2) {
                resize(data.length / 2);
            }
            return value;
        }

        public E removeFirst() {
            return remove(0);
        }

        public E removeLast() {
            return remove(size - 1);
        }

        public void removeElement(E e) {
            int index = find(e);
            if (index != -1) {
                remove(index);
            }
        }

        private void resize(int newCapacity) {
            E[] newData = (E[]) new Object[newCapacity];
            for (int i = 0; i < size; i++) {
                newData[i] = data[i];
            }
            data = newData;
        }

        public void swap(int i, int j) {

            if (i < 0 || i >= size || j < 0 || j >= size)
                throw new IllegalArgumentException("Index is illegal.");

            E t = data[i];
            data[i] = data[j];
            data[j] = t;
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            res.append(String.format("Array:size = %d, capacity = %d\n", size, data.length));
            res.append("[");
            for (int i = 0; i < size; i++) {
                res.append(data[i]);
                if (i != size - 1) {
                    res.append(", ");
                }
            }
            res.append("]");
            return res.toString();
        }
    }

    class MaxHeap<E extends Comparable<E>> {
        private Array<E> data;

        public MaxHeap() {
            data = new Array<>();
        }

        public MaxHeap(int capacity) {
            data = new Array<>(capacity);
        }

        public MaxHeap(E[] arr) {
            data = new Array<>(arr);
            for (int i = parent(arr.length - 1); i >= 0; i--) {
                siftDown(i);
            }
        }

        public void add(E e) {
            data.addLast(e);
            siftUp(data.getSize() - 1);
        }

        private void siftUp(int k) {
            while (k > 0 && data.get(k).compareTo(data.get(parent(k))) > 0) {
                data.swap(k, parent(k));
                k = parent(k);
            }
        }

        // 看堆中的最大元素
        public E findMax() {
            if (data.getSize() == 0)
                throw new IllegalArgumentException("Can not findMax when heap is empty.");
            return data.get(0);
        }

        // 取出堆中最大元素
        public E extractMax() {
            E e = findMax();

            data.swap(0, data.getSize() - 1);
            data.removeLast();

            siftDown(0);

            return e;
        }

        // 取出堆中的最大元素，并且替换成元素e
        public E replace(E e) {
            E ret = findMax();

            data.set(0, e);
            siftDown(0);

            return ret;
        }

        private void siftDown(int k) {
            while (leftChaild(k) < data.getSize()) {//k节点存在左孩子
                int j = leftChaild(k);
                if (rightChild(k) < data.getSize()
                        && data.get(leftChaild(k)).compareTo(data.get(rightChild(k))) < 0) {
                    // 存在右孩子，并且 左孩子小于右孩子
                    j = rightChild(k);
                }

                if (data.get(k).compareTo(data.get(j)) >= 0) {
                    break;
                }
                data.swap(j, k);
                k = j;
            }
        }

        // 返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
        private int parent(int index) {
            if (index == 0) {
                throw new IllegalArgumentException("index-0 doesn't have parent.");
            }
            return (index - 1) / 2;
        }

        // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
        private int leftChaild(int index) {
            return 2 * index + 1;
        }

        // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
        private int rightChild(int index) {
            return 2 * index + 2;
        }

        public int size() {
            return data.getSize();
        }

        public boolean isEmpty() {
            return data.isEmpty();
        }
    }


}
