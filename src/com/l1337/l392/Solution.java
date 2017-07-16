package com.l1337.l392;


import java.util.Arrays;

public class Solution {
    public boolean isSubsequence(String s, String t) {

        //v1
//        if (s.length() == 0) return true;
//        boolean T [] = new boolean[s.length()];
//
//        for (int i = 0; i < t.length(); ++i) {
//            boolean diag = true;
//            for (int j = 0; j < s.length(); ++j) {
//                boolean left = T[j];
//                T[j] = (t.charAt(i) == s.charAt(j) && diag) || left;
//                diag = left;
//            }
//        }
//        return T[s.length() - 1];

        //v2
//        if (s.length() > t.length())
//            return false;
//        boolean [] dp = new boolean [1 + s.length()];
//        dp[0] = true;
//        for (int i = 0; i < t.length(); ++i) {
//            for (int j = dp.length - 1; j > 0; --j) {
//                //s[j-1], t[i]
//                dp[j] = dp[j] || ((s.charAt(j-1) == t.charAt(i)) && dp[j-1]);
//            }
//        }
//        return dp[s.length()];
        int i = 0, j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j))
                ++i;
            ++j;
        }
        return i == s.length();
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        String ss = "t", tt = "xt";
//        String ss = "axc", tt = "ahbgdc";
//        String ss = "ax", tt = "a";
        System.out.println(s.isSubsequence(ss,tt));
    }
}
