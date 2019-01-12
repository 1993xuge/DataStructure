package com.xuge.unionfind;

/**
 * Quick Union （基于size的优化，树的高度有所降低）
 *
 * 使用一个数组parent[] 来构建一颗指向父节点的树。
 * parent[i]表示第一个元素所指向的父节点
 *
 * 声明sz数组，sz[i]表示以i为根的集合中元素个数
 *
 * find(p)            O(h)
 * unionElements(p,q) O(h)
 * 其中h为树的高度
 *
 */
public class UnionFind3 implements UF {
    // 我们的第二版Union-Find, 使用一个数组构建一棵指向父节点的树
    // parent[i]表示第一个元素所指向的父节点
    private int[] parent;

    private int[] sz;     // sz[i]表示以i为根的集合中元素个数

    public UnionFind3(int size) {
        this.parent = new int[size];
        this.sz = new int[size];
        for (int i = 0; i < size; i++) {
            // 初始化, 每一个parent[i]指向自己, 表示每一个元素自己自成一个集合
            parent[i] = i;
            sz[i] = 1;
        }
    }

    // 查看元素p和元素q是否所属一个集合
    // O(h)复杂度, h为树的高度
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    // 合并元素p和元素q所属的集合
    // O(h)复杂度, h为树的高度
    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot)
            return;

        // 根据两个元素所在树的元素个数不同判断合并方向
        // 将元素个数少的集合合并到元素个数多的集合上
        if (sz[pRoot] < sz[qRoot]) {
            parent[pRoot] = qRoot;
            sz[qRoot] += sz[qRoot];
        }else{ // sz[qRoot] <= sz[pRoot]
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    // 查找过程, 查找元素p所对应的集合编号
    // O(h)复杂度, h为树的高度
    private int find(int p) {
        if (p < 0 || p >= parent.length)
            throw new IllegalArgumentException("p is out of bound.");

        // 不断去查询自己的父亲节点, 直到到达根节点
        // 根节点的特点: parent[p] == p
        while (p != parent[p]) {
            p = parent[p];
        }

        return p;
    }
}
