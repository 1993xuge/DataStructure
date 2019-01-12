package xuge.q349;

import java.util.*;

public class Solution {
    public static void main(String[] args) {

    }

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> treeSet = new HashSet<>();
        for (int num : nums1) {
            treeSet.add(num);
        }

        List<Integer> list = new ArrayList<>();
        for (int num : nums2) {
            if (treeSet.contains(num)) {
                list.add(num);
                treeSet.remove(num);
            }
        }

        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }

        return result;
    }
}
