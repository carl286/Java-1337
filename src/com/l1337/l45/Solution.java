package com.l1337.l45;


import java.util.Arrays;

public class Solution {
    // https://leetcode.com/submissions/detail/63465172/
    public int jump(int[] nums) {
        if (nums.length <= 1)
            return 0;
        int [] dp = new int [nums.length];
        dp[0] = 1;
        for (int i = 0; i <= dp.length; ++i) {
            if (dp[i] <= 0) {
                break;
            }
            for (int k = 1; k <= nums[i]; ++k) {
                if (k + i < dp.length) {
                    if (dp[k + i] == 0) {
                        dp[k + i] = 1 + dp[i];
                    } else {
                        dp[k+i] = Math.min(dp[k+i], 1 + dp[i]);
                    }
                } else {
                    break;
                }
            }
        }

        return dp[dp.length - 1];
    }
































    public int jumpApril10_21(int[] nums) {
        if (nums.length <= 1)
            return 0;
        int [] dp = new int [nums.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = 0; i < nums.length; ++i)
        {
//            if (dp[i] != Integer.MAX_VALUE)
            {
                for(int k = i + 1; i + nums[i] >= k && k < dp.length; ++k)
                {
                    dp[k] = Math.min(dp[k], 1 + dp[i]);
                }
            }
        }

        return dp[nums.length-1];
    }


    public static void main(String [] args) {
        Solution s = new Solution();
//        int [] hums = {0,1,0,2,1,0,1,3,2,1,2,1};
        int [] nums = new int [] {2,3,1,1,4};
        System.out.println(s.jumpApril10_21(nums));
    }
}
