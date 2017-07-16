package com.l1337.l643;


public class Solution {

//    https://leetcode.com/articles/maximum-average-subarray/
    public double findMaxAverage(int[] nums, int k) {
        long sum = 0;
        for (int i = 0; i < k; i++) sum += nums[i];
        long max = sum;

        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - nums[i - k];
            max = Math.max(max, sum);
        }

        return max / 1.0 / k;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.findMaxAverage(new int []{1,12,-5,-6,50,3}, 4));
    }
}
