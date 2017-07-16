package com.l1337.l72;

import java.util.Stack;

public class Solution {

    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int [][] data = new int [1 + m][1 + n];
        for (int i = 1; i <= n; ++i)
            data[0][i] = i;
        for (int i = 1; i <= m; ++i)
            data[i][0] = i;

        for (int i = 1; i <= m; ++i)
            for (int j = 1; j <= n; ++j) {
                data[i][j] = Math.min((word1.charAt(i-1) == word2.charAt(j-1) ? 0 : 1) + data[i-1][j-1],
                        Math.min(data[i][j-1], data[i-1][j]) + 1);
            }
        return data[m][n];
    }

    public int trailingZeroes(int n) {
        if (n < 5)
            return 0;
        int ret = 0;
        while (n > 0)
        {
            int tmp = n / 5;
            ret += tmp;
            n = tmp;
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
    }
}
