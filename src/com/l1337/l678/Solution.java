package com.l1337.l678;


import java.util.Arrays;

public class Solution {

    private boolean checkValidStringRecursion(String s, int level, int acc) {
        if (level >= s.length()) {
            return acc == 0;
        }

        switch (s.charAt(level)) {
            case '(':
                return checkValidStringRecursion(s, level + 1, acc + 1);
            case ')':
                return acc >= 1 && checkValidStringRecursion(s, level + 1, acc - 1);
            default:
                return checkValidStringRecursion(s, level + 1, acc) ||
                        checkValidStringRecursion(s, level + 1, acc + 1) ||
                        (acc >= 1 && checkValidStringRecursion(s, level + 1, acc - 1));
        }
    }

//    https://leetcode.com/problems/valid-parenthesis-string/discuss/107577/Short-Java-O(n)-time-O(1)-space-one-pass
//    https://leetcode.com/problems/valid-parenthesis-string/discuss/107572/Java-using-2-stacks.-O(n)-space-and-time-complexity.
    public boolean checkValidString(String s) {

//        return checkValidStringRecursion(s,0,0);
//        if (s.length() <= 0)
//            return true;
        boolean dp [] = new boolean[s.length() + 1];

        //for length of 0, it's possible of having 0 of '('
        dp[0] = true;

        for (int l = 1; l <= s.length(); ++l) {
            boolean tmp[] = new boolean[l+1];

            switch (s.charAt(l-1)) {
                case '(':
                    tmp[0] = false;
                    for (int k = 0; k < tmp.length - 1; ++k)
                        tmp[k+1] = dp[k];
                    break;
                case ')':
                    tmp[tmp.length - 1] = false;
                    for (int k = tmp.length - 1; k > 0; --k)
                        tmp[k-1] = dp[k];
                    break;
                default:
                    for (int k = 0; k < tmp.length; ++k)
                        tmp[k] = (k == 0 ? false : dp[k-1]) || dp[k] || (k + 1 < tmp.length ? dp[k+1] : false);
                    break;
            }

            boolean containsTrue = false;
            for (int k = 0; k < tmp.length; ++k) {
                dp[k] = tmp[k];
                if (dp[k])
                    containsTrue = true;
            }

            if (!containsTrue)
                return false;
        }

        return dp[0];
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.checkValidString("("));
    }
}
