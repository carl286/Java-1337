package com.l1337.l664;


public class Solution {

//    https://leetcode.com/problems/strange-printer/discuss/106810/Java-O(n3)-DP-MagicDictionary-with-Explanation-and-Simple-Optimization
//    https://leetcode.com/problems/strange-printer/discuss/171837/Bottom-up-DP-solution-in-C++-29ms
    public int strangePrinter(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
            if (i < n - 1) {
                dp[i][i + 1] = s.charAt(i) == s.charAt(i + 1) ? 1 : 2;
            }
        }

        for (int len = 2; len < n; len++) {
            for (int start = 0; start + len < n; start++) {
                dp[start][start + len] = len + 1;
                for (int k = 0; k < len; k++) {
                    int temp = dp[start][start + k] + dp[start + k + 1][start + len];
                    dp[start][start + len] = Math.min(
                            dp[start][start + len],
                            s.charAt(start + k) == s.charAt(start + len) ? temp - 1 : temp
                    );
                }
            }
        }

        return dp[0][n - 1];
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
