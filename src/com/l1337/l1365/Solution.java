package com.l1337.l1365;


public class Solution {

    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] ret = new int [nums.length];
        int dp [] = new int [101];
        for(int i = 0; i < nums.length; ++i)
        {
            ++dp[nums[i]];
        }

        for(int i = 1; i < dp.length; ++i)
        {
            dp[i] += dp[i-1];
        }

        for(int i = 0; i < ret.length; ++i)
            ret[i] = nums[i] == 0 ? 0 : (dp[nums[i]-1]);

        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        int [] nums = new int [] {8,1,2,2,3};
        int [] ret = s.smallerNumbersThanCurrent(nums);
        for(int i = 0; i < ret.length; ++i)
        System.out.println(ret[i]);
    }
}
