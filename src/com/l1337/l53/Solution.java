package com.l1337.l53;


import java.util.*;

public class Solution {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length <= 0)
            return 0;

        int ret = Integer.MIN_VALUE;
        int acc = 0;
        for (int i = 0; i < nums.length; ++i) {
            acc += nums[i];
            if (acc > ret) {
                ret = acc;
            }
            if (acc < 0)
                acc = 0;
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
    }
}
