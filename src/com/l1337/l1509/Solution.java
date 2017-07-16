package com.l1337.l1509;


import java.util.Arrays;

public class Solution {

    public int minDifference(int[] nums) {
        if (nums.length <= 3)
            return 0;
        Arrays.sort(nums);

        return Math.min(nums[nums.length-4] - nums[0], nums[nums.length-1] - nums[3]);
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
