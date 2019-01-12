package xuge.q303;

/**
 * 使用线段树来解决问题
 */
public class NumArray {

    private interface Merger<E> {
        E merge(E l, E r);
    }

    private class SegmentTree<E> {
        private E[] data;
        private E[] tree;
        private Merger<E> merger;

        public SegmentTree(E[] data, Merger<E> merger) {
            this.data = (E[]) new Object[data.length];
            this.merger = merger;
            for (int i = 0; i < data.length; i++) {
                this.data[i] = data[i];
            }

            tree = (E[]) new Object[4 * data.length];

            buildSegmentTree(0, 0, data.length - 1);
        }

        private void buildSegmentTree(int treeIndex, int l, int r) {
            if (l == r) {
                tree[treeIndex] = data[l];
                return;
            }

            int mid = l + (r - l) / 2;
            int leftTreeIndex = leftChild(treeIndex);
            int rightTreeIndex = rightChild(treeIndex);

            buildSegmentTree(leftTreeIndex, l, mid);
            buildSegmentTree(rightTreeIndex, mid + 1, r);

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

        private int leftChild(int index) {
            return 2 * index + 1;
        }

        private int rightChild(int index) {
            return 2 * index + 2;
        }
    }

    private SegmentTree<Integer> segmentTree;

    public NumArray(int[] nums) {
        if (nums.length > 0) {
            Integer[] data = new Integer[nums.length];
            for (int i = 0; i < nums.length; i++) {
                data[i] = nums[i];
            }
            segmentTree = new SegmentTree<>(data, (l, r) -> l + r);
        }
    }

    public int sumRange(int i, int j) {
        if (segmentTree == null) {
            throw new IllegalStateException("segmentTree == null");
        }
        return segmentTree.query(i, j);
    }
}
