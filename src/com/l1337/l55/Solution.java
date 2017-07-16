package com.l1337.l55;


public class Solution {
    // https://leetcode.com/submissions/detail/63463126/
    // A very excellent discussion on DP
    // https://leetcode.com/problems/jump-game/solution/
    public boolean canJump(int[] nums) {

        if (nums.length <= 1)
            return true;
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

        return dp[dp.length-1] > 0;
    }































    public boolean canJumpApril10_21(int[] nums) {
        if (nums.length <= 1)
            return true;

        int i = 0, furthest = 0;
        while (i <= furthest && furthest < nums.length - 1)
        {
            furthest = Math.max(furthest, i + nums[i]);
            ++i;
        }
        return furthest >= nums.length - 1;
    }





    public static void main(String [] args) {
        Solution s = new Solution();
    }
}
