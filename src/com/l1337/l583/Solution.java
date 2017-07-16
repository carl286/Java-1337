package com.l1337.l583;


public class Solution {

//    https://leetcode.com/problems/delete-operation-for-two-strings/discuss/103214/Java-DP-Solution-(Longest-Common-Subsequence)
    public int minDistance(String word1, String word2) {
        if (word1.length() == 0)
            return word2.length();
        if (word2.length() == 0)
            return word1.length();

        int [][] dp = new int [1 + word2.length()][1 + word1.length()];
        //init
        for (int i = 0; i < dp[0].length; ++i)
            dp[0][i] = i;
        for (int i = 1; i < dp.length; ++i)
            dp[i][0] = i;

        for (int i = 1; i < dp.length; ++i)
            for (int j = 1; j < dp[i].length; ++j) {
            dp[i][j] = Math.min(((word2.charAt(i-1) == word1.charAt(j-1)) ? 0 : 2) + dp[i-1][j-1],
                    Math.min(dp[i-1][j], dp[i][j-1]) + 1);
            }

        return dp[word2.length()][word1.length()];
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
