package com.l1337.l518;


import java.util.Arrays;

public class Solution {

    public int change(int amount, int[] coins) {
        Arrays.sort(coins);

        //assume coins are unique
        //from where amout >= coins[i]
        int i = 0;
        while (i < coins.length && coins[i] <= amount) {
            ++i;
        }

//        if (i == 0)
//            return 0;

        int [][] dp = new int[i + 1][];
        for (int k = 0; k < dp.length; ++k) {
            dp[k] = new int [amount + 1];
//            dp[k][0] = 1;
        }

        dp[0][0] = 1;//don't use any coin, make it amount to 0;

        for (int k = 1; k < dp.length; ++k) {
            for (int j = 0; j < coins[k-1]; ++j)
                dp[k][j] = dp[k-1][j];
            for (int j = coins[k-1]; j <= amount; ++j) {
                for (int m = 0; m < k; ++m) {
                    dp[k][j] += dp[m + 1][j - coins[m]];
                }
            }
        }
        return dp[i][amount];
    }

//    https://leetcode.com/problems/coin-change-2/discuss/166864/Java-DP-beat-100-and-some-summaries
//    https://leetcode.com/problems/coin-change-2/discuss/99212/Knapsack-problem-Java-solution-with-thinking-process-O(nm)-Time-and-O(m)-Space
    //you can have a better transition function...
    public static void main(String [] args) {
        Solution s = new Solution();
        int amount = 11;
        int [] coins = new int []{1,2,5};
        System.out.println(s.change(amount, coins));
    }
}
