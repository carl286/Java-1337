package com.l1337.l51;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    private static char QUEEN = 'Q';
    private static char DOT = '.';
    private void helper(List<List<String>> ret, int index, int n, int [] tmp) {
        if (index == n ) {
            char [][] temple = new char[n][n];
            for (int i = 0; i < n; ++i) {
                Arrays.fill(temple[i], DOT);
            }
            for (int i = 0; i < n; ++i) {
                temple[i][tmp[i]] = QUEEN;
            }

            List<String> local = new ArrayList<>();
            for (int i = 0; i < n; ++i) {
                local.add(new String(temple[i]));
            }
            ret.add(local);
            return;
        }

        //try each possible pos
        for (int k = 0; k < n; ++k) {
            int j = 0;
            while (j < index) {
                if (k == tmp[j] || Math.abs(tmp[j] - k) == index - j)
                    break;
                ++j;
            }

            if (j == index) {
                tmp[index] = k;
                helper(ret, index + 1, n, tmp);
            }
        }
    }
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ret = new ArrayList<>();
        helper(ret, 0, n, new int[n]);
        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
    }
}
