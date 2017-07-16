package com.l1337.l561;


import java.util.Arrays;

public class Solution {

//    https://leetcode.com/problems/array-partition-i/discuss/169654/Python-O(n)-method-greater-good-for-huge-datasets
    public int arrayPairSum(int[] nums) {
        int ret = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i += 2)
            ret += nums[i];
        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
