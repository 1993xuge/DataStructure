package xuge.q307;

public class NumArray2 {
    // sum[i] 存储前i个元素的和，sum[0] = 0, sum[i]存储[0...i-1]个元素的和
    private int[] sum;

    private int[] data;

    public NumArray2(int[] nums) {
        data = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            data[i] = nums[i];
        }
        sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
    }

    public void update(int index, int val) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("index is error");
        }
        data[index] = val;
        for (int i = index + 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + data[i - 1];
        }
    }

    public int sumRange(int i, int j) {
        if (i > j) {
            throw new IllegalArgumentException("i>j");
        }
        return sum[j + 1] - sum[i];
    }
}
