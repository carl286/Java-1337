package com.l1337.l746;


public class Solution {

    public int minCostClimbingStairs(int[] cost) {
        if (cost.length <= 1)
            return 0;

        int [] dp = new int [1 + cost.length];
        dp[0] = 0;
        dp[1] = 0;

        for (int i = 2; i < dp.length; ++i) {
            //fill dp[i], for cost[i], except for the last item
            dp[i] = Math.min(dp[i-1] + cost[i-1], dp[i-2] + cost[i-2]);
        }

        return dp[cost.length];
    }

    public static void main(String [] args) {
        Solution s = new Solution();

        System.out.println(s.minCostClimbingStairs(new int [] {10, 15, 20}));
//        System.out.println(s.minCostClimbingStairs(new int [] {1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
    }
}
