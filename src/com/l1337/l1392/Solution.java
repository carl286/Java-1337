package com.l1337.l1392;


public class Solution {

//    https://leetcode.com/problems/longest-happy-prefix/discuss/547446/C%2B%2BJava-with-picture-incremental-hash-and-KMP
//    https://leetcode.com/problems/longest-happy-prefix/discuss/547237/JavaPython-Rolling-Hash
    public String longestPrefix(String s) {
        int dp[] = new int[s.length()], j = 0;
        for (int i = 1; i < s.length(); ++i) {
            if (s.charAt(i) == s.charAt(j))
                dp[i] = ++j;
            else if (j > 0) {
                j = dp[j - 1];
                --i;
            }
        }
        return s.substring(0, j);
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.longestPrefix("aaa"));
    }
}
