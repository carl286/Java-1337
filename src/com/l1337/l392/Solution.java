package com.l1337.l392;


import java.util.Arrays;

public class Solution {
    public boolean isSubsequence(String s, String t) {
        if (s.length() > t.length())
            return false;
        boolean [] dp = new boolean [1 + s.length()];
        dp[0] = true;
        for (int i = 0; i < t.length(); ++i) {
            for (int j = dp.length - 1; j > 0; --j) {
                //s[j-1], t[i]
                dp[j] = dp[j] || ((s.charAt(j-1) == t.charAt(i)) && dp[j-1]);
            }
        }
        return dp[s.length()];
    }

    public static void main(String [] args) {
        Solution s = new Solution();
//        String ss = "abc", tt = "ahbgdc";
        String ss = "axc", tt = "ahbgdc";
        System.out.println(s.isSubsequence(ss,tt));
    }
}
