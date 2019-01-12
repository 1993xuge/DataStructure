package com.xuge.tree.segmenttree;

/**
 * 线段是是一颗平衡二叉树。
 * 二叉树每一层最多有 2^(h-1)个元素，h层的二叉树最多有 2^h -1 个元素
 * <p>
 * 如果区间有n个元素，数组表示需要有多少个节点？
 * <p>
 * 最好情况：n = 2^k，只需要2n个空间。
 * 最坏情况：n = 2^k + 1，需要4n个空间。
 */
public class SegmentTree<E> {
    private E[] tree;
    private E[] data;
    private Merger<E> merger;

    public SegmentTree(E[] data, Merger<E> merger) {
        this.merger = merger;
        this.data = (E[]) new Object[data.length];
        for (int i = 0; i < data.length; i++) {
            this.data[i] = data[i];
        }

        tree = (E[]) new Object[4 * data.length];

        buildSegmentTree(0, 0, data.length - 1);
    }

    // 在treeIndex的位置创建表示区间[l...r]的线段树
    private void buildSegmentTree(int treeIndex, int l, int r) {
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        int mid = l + (r - l) / 2;

        // 左子树
        buildSegmentTree(leftTreeIndex, l, mid);

        // 右子树
        buildSegmentTree(rightTreeIndex, mid + 1, r);

        // 存储具体的业务值
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    // 返回区间[queryL, queryR]的值
    public E query(int queryL, int queryR) {
        if (queryL < 0 || queryL >= data.length
                || queryR < 0 || queryR >= data.length
                || queryL > queryR) {
            throw new IllegalArgumentException("Index is illegal.");
        }
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    // 在以treeIndex为根的线段树中[l...r]的范围里，搜索区间[queryL...queryR]的值
    private E query(int queryIndex, int l, int r, int queryL, int queryR) {
        if (l == queryL && r == queryR) {
            return tree[queryIndex];
        }

        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(queryIndex);
        int rightTreeIndex = rightChild(queryIndex);

        if (queryL > mid) {
            // 查询区间与左边的子区间无关，所以只在右子区间中查询
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        } else if (queryR <= mid) {
            // 查询区间与右边的子区间无关，所以只在左子区间中查询
            return query(leftTreeIndex, l, mid, queryL, queryR);
        } else {
            // 查询区间同时落在左右子区间中
            // 左子区间：[l, mid]     查询区间：[queryL, mid]
            // 右子区间：[mid+1, r]   查询区间：[mid+1, queryR]
            E leftValue = query(leftTreeIndex, l, mid, queryL, mid);
            E rightValue = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
            return merger.merge(leftValue, rightValue);
        }
    }

    // 将index位置的值，更新为e
    public void set(int index, E e) {

        if (index < 0 || index >= data.length)
            throw new IllegalArgumentException("Index is illegal");

        data[index] = e;
        set(0, 0, data.length - 1, index, e);
    }

    // 在以treeIndex为根的线段树中更新index的值为e
    private void set(int treeIndex, int l, int r, int index, E e) {
        if (l == r) {
            tree[treeIndex] = data[index];
            return;
        }

        int mid = l + (r - l) / 2;
        // treeIndex的节点分为[l...mid]和[mid+1...r]两部分
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if (index > mid) {
            set(rightTreeIndex, mid + 1, r, index, e);
        } else if (index <= mid) {
            set(leftTreeIndex, l, mid, index, e);
        }

        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    public int getSize() {
        return data.length;
    }

    public E get(int index) {
        if (index < 0 || index >= data.length)
            throw new IllegalArgumentException("Index is illegal.");
        return data[index];
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index) {
        return 2 * index + 1;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    private int rightChild(int index) {
        return 2 * index + 2;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append('[');
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null)
                res.append(tree[i]);
            else
                res.append("null");

            if (i != tree.length - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }
}
