package com.l1337.l647;


public class Solution {

//    https://leetcode.com/problems/palindromic-substrings/discuss/105687/Python-Straightforward-with-Explanation-(Bonus-O(N)-solution)
//    https://leetcode.com/problems/palindromic-substrings/discuss/105689/Java-solution-8-lines-extendPalindrome
    public int countSubstrings(String s) {
        int length = s.length();

        if (length <= 1)
            return length;

        boolean [][] dp = new boolean[length][length];
        int ret = length;
        for(int i = length - 2; i >= 0; --i) {
            dp[i][i] = true;
            for (int j = i + 1; j < length; ++j) {
                //fill dp[i][j]
                dp[i][j] = (s.charAt(i) == s.charAt(j)) &&
                        (j == i + 1 || dp[i+1][j-1]);
                if (dp[i][j])
                    ++ret;
            }

        }
        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.countSubstrings("aaa"));
    }
}
