package xuge.q303;

public class NumArray2 {
    // sum[i] 存储前i个元素的和，sum[0] = 0, sum[i]存储[0...i-1]个元素的和
    private int[] sum;

    public NumArray2(int[] nums) {

        sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
    }

    public int sumRange(int i, int j) {
        if (i > j) {
            throw new IllegalArgumentException("i>j");
        }
        return sum[j + 1] - sum[i];
    }
}
