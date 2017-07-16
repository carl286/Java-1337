package com.l1337.l516;


public class Solution {

    public int longestPalindromeSubseq(String s) {
        if (s.length() <= 1)
            return s.length();

//        int ret = 1;
//        boolean dp [] = new boolean[s.length()];
//        dp[s.length() - 1] = true;
//        for (int i = s.length() - 2; i >= 0; --i) {
//            //from i to s.length() - 1
//            dp[i] = true;
//            for (int j = s.length() - 1; j > i; --j) {
//                if (j == i + 1)
//                    dp[j] = s.charAt(i) == s.charAt(j);
//                else
//                    dp[j] = (s.charAt(i) == s.charAt(j)) && dp[j-1];
//
//                if (dp[j] && (j - i + 1) > ret)
//                    ret = (j - i + 1);
//            }
//        }
//
//        return ret;

        int ret = 1;
        int [][] dp = new int [s.length()][];
        for (int i = 0; i < dp.length; ++i) {
            dp[i] = new int [s.length()];
        }

        for (int i = dp.length - 1; i >= 0; --i) {
            dp[i][i] = 1;
            for (int j = i + 1; j < dp.length; ++j) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j == i + 1)
                        dp[i][j] = 2;
                    else
                        dp[i][j] = 2 + dp[i+1][j-1];
                } else {
                    dp[i][j] = dp[i][j-1];
                    for (int k = i + 1; k < j; ++k) {
                        if (s.charAt(k) == s.charAt(j)) {
                            int candidate = 2;
//                            if (k-1 >=i)
//                                candidate += dp[i][k-1];
                            if (k+1 <= j-1)
                                candidate += dp[k+1][j-1];
                            dp[i][j] = Math.max(dp[i][j], candidate);
                            //think why you can break here...
                            break;
                        }
                    }
                }
            }
        }
        return dp[0][s.length()-1];


        // it can be so much simpler...
//        https://leetcode.com/problems/longest-palindromic-subsequence/discuss/99101/Straight-forward-Java-DP-solution
        //I don't think below code handles index out of bounds correctly..
//        int[][] dp = new int[s.length()][s.length()];
//
//        for (int i = s.length() - 1; i >= 0; i--) {
//            dp[i][i] = 1;
//            for (int j = i+1; j < s.length(); j++) {
//                if (s.charAt(i) == s.charAt(j)) {
//                    dp[i][j] = dp[i+1][j-1] + 2;
//                } else {
//                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
//                }
//            }
//        }
//        return dp[0][s.length()-1];
//        https://leetcode.com/problems/longest-palindromic-subsequence/discuss/99111/Evolve-from-brute-force-to-dp
    }

//    https://leetcode.com/problems/longest-palindromic-subsequence/discuss/99111/Evolve-from-brute-force-to-dp




    public int longestPalindromeSubseqII(String s) {

        return -1;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
//        System.out.println(s.longestPalindromeSubseq("bbbab"));
        System.out.println(s.longestPalindromeSubseq("hiddqscdxrhiddqscdxrhiddqscdxrhiddqscdxrhiddqscdxrhiddqscdxrhiddqscdxrhiddqscdxrhiddqscdxrhiddqscdxr"));
    }
}
