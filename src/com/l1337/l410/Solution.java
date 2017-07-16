package com.l1337.l410;


public class Solution {

    public int splitArray(int[] nums, int m) {
        if (nums.length <= 0)
            return 0;

        //v1,
//        int [] sum = new int [nums.length];
//        int [] dp = new int [nums.length];
//        dp[0] = sum[0] = nums[0];
//        for (int i = 1; i < nums.length; ++i)
//            dp[i] = sum[i] = sum[i-1] + nums[i];
//
//        for (int partition = 2; partition <= m; ++partition) {
//            int [] next = new int [nums.length];
//            for (int i = partition - 1; i < nums.length; ++i) {
//                //figure next[i];
//                next[i] = Integer.MAX_VALUE;
//                for (int k = partition - 2; k < i; ++k) {
//                    next[i] = Math.min(next[i], Math.max(dp[k], sum[i] - sum[k]));
//                }
//            }
//            dp = next;
//        }

        int [] dp = new int [nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; ++i)
            dp[i] = nums[i] += nums[i-1];

        for (int partition = 2; partition <= m; ++partition) {
            for (int i = nums.length - 1; i >= partition - 1; --i) {
                dp[i] = Integer.MAX_VALUE;
                for (int k = partition - 2; k < i; ++k) {
                    dp[i] = Math.min(dp[i], Math.max(dp[k], nums[i] - nums[k]));
                }
            }
        }
        return dp[dp.length-1];
    }

    public static void main(String [] args) {
        Solution s = new Solution();
//        int [] nums = {7,2,5,10,8};
        int [] nums = {10, 2, 3};
        int m = 2;
        System.out.println(s.splitArray(nums, m));
    }
}
