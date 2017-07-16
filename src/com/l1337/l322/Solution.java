package com.l1337.l322;


import java.util.Arrays;

public class Solution {

    //    https://leetcode.com/problems/coin-change/discuss/77368/*Java*-Both-iterative-and-recursive-solutions-with-explanations
//    https://leetcode.com/problems/coin-change/discuss/77373/6-7-lines-2-ways
//    https://leetcode.com/problems/coin-change/discuss/121814/C++-Dynamic-Programming-(Knapsack-Approach)
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (amount < 0 || coins.length <= 0)
            return -1;
        Arrays.sort(coins);
        int [] dp = new int [1 + amount];
        for (int i = 1; i < dp.length; ++i)
            dp[i] = Integer.MAX_VALUE;

        for (int coin : coins) {
            if (coin <= amount)
                dp[coin] = 1;
        }

        for (int i = 1; i < dp.length; ++i) {
            for (int k = 0; k < coins.length && coins[k] <= i && dp[i] != 1; ++k) {
                if (dp[i - coins[k]] != Integer.MAX_VALUE)
                    dp[i] = Math.min(dp[i], dp[i - coins[k]] + 1);
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        int amount = 2;
        int[] coins = {1};
        System.out.println(s.coinChange(coins, amount));
    }
}
