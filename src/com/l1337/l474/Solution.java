package com.l1337.l474;


public class Solution {

    private int[] count(String str) {
        int[] res = new int[2];
        for (int i=0;i<str.length();i++)
            res[str.charAt(i) - '0']++;
        return res;
    }

//    https://leetcode.com/problems/ones-and-zeroes/discuss/95811/Java-Iterative-DP-Solution-O(mn)-Space
//    https://leetcode.com/problems/ones-and-zeroes/discuss/95808/0-1-knapsack-in-python
//    https://leetcode.com/problems/ones-and-zeroes/discuss/121876/C++-DP-Knapsack-Approach
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m+1][n+1];
        for (String s : strs) {
            int[] count = count(s);
            for (int i=m;i>=count[0];i--)
                for (int j=n;j>=count[1];j--)
                    dp[i][j] = Math.max(1 + dp[i-count[0]][j-count[1]], dp[i][j]);
        }
        return dp[m][n];
    }

    public static void main(String [] args) {
        Solution s = new Solution();
//        String[] strs = {"10", "0", "1"};
//        int m = 1, n = 1;
        String[] strs = {"10", "0001", "111001", "1", "0"};
        int m = 5, n = 3;
        System.out.println(s.findMaxForm(strs, m, n));
    }
}
