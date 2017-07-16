package com.l1337.l712;


public class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        int dp [][] = new int [1 + s1.length()][1 + s2.length()];
        for (int i = 0; i < s2.length(); ++i)
            dp[0][i+1] = dp[0][i] + s2.charAt(i);

        for (int i = 1; i < dp.length; ++i) {
            dp[i][0] = dp[i-1][0] + s1.charAt(i-1);
            for (int j = 0; j < s2.length(); ++j) {
                if (s1.charAt(i-1) == s2.charAt(j))
                    dp[i][j+1] = dp[i-1][j];
                else
                    dp[i][j+1] = Math.min(s1.charAt(i-1) + dp[i-1][j+1], s2.charAt(j) + dp[i][j]);
            }
        }

        return dp[s1.length()][s2.length()];
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.minimumDeleteSum("delete", "leet"));
    }
}
