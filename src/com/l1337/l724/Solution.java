package com.l1337.l724;


public class Solution {

    public int pivotIndex(int[] nums) {
        if (nums.length == 0)
            return -1;
        if (nums.length == 1)
            return 0;

        int [] totals = new int [nums.length];
        totals[0] = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            totals[i] = totals[i-1] + nums[i];
        }

        for (int i = 0; i < nums.length; ++i) {
            //left, index from 0 till i -1
            //right, index from i + 1 to nums.length -1
            if (totals[i] == totals[nums.length-1] - totals[i] + nums[i])
                return i;
        }

        return -1;
    }























    public int pivotIndex2(int[] nums) {
        long total = 0;
        for (int i : nums)
            total += i;

        long left = 0;
        for (int i = 0; i < nums.length; ++i)
        {
            if (total - nums[i] - left == left )
                return i;
            left += nums[i];
        }

        return -1;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
