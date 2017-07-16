package com.l1337.l294;


import java.util.HashSet;
import java.util.Set;

public class Solution {

//    https://www.1point3acres.com/bbs/thread-137953-1-1.html
//    https://www.programcreek.com/2014/05/leetcode-flip-game-ii-java/
//    https://www.lintcode.com/problem/flip-game-ii/
    public boolean canWin(String s) {
        // write your code here
        int n = s.length();
        boolean [][] dp = new boolean[n][n];
        Set<Integer> possibleStarts = new HashSet<>();
        boolean last = s.charAt(0) == '+';
        for(int i = 1; i < n; ++i)
        {
            boolean current = s.charAt(i) == '+';
            if (last && current)
            {
                possibleStarts.add(i-1);
            }
            last = current;
        }

        //single point, dp[i][i] is default to false;...
        for(int l = 2; l <= n; ++l)
        {
            //covers ranges [i, i+l-1]
            for(int i = 0; i + l - 1< n; ++i)
            {
                for(int j = i; j < i + l -1 && !dp[i][i+l-1]; ++j)
                    dp[i][i+l-1] = possibleStarts.contains(j) && !(i + 1 >= j ? false : dp[i][j-1]) && !(j+2 >= i + l -1 ? false : dp[j+2][i+l-1]);
            }
        }

        return dp[0][n-1];
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        String input = "+++++";
        System.out.println(s.canWin(input));
    }
}
