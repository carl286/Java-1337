package com.l1337.l1316;


import java.util.HashSet;
import java.util.Set;

public class Solution {

//    https://leetcode.com/problems/distinct-echo-substrings/discuss/478854/C%2B%2B-DP-solution-O(N3)-with-explanation
    public int distinctEchoSubstrings(String text) {
        int n = text.length();
        if (n <= 1)
            return 0;
        int [][] dp = new int [n][n];
        Set<String> tmp = new HashSet<>();
        for(int i = n - 2; i >= 0; --i)
        {
            for(int j = i + 1; j < n; ++j)
            {
                dp[i][j] = text.charAt(i) == text.charAt(j) ? (1 + (j + 1 < n ? dp[i+1][j+1] : 0)) : 0;
                if (dp[i][j] >= j - i)
                {
                    tmp.add(text.substring(i,j));
                }
            }
        }

        return tmp.size();
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.distinctEchoSubstrings("aaaaa"));
    }
}
