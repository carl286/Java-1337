package com.l1337.l52;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    // https://leetcode.com/problems/n-queens-ii/discuss/20158/Someone-could-explan-this-code-(python-)-'s-bit-manipulating

    /*
    class Solution {
    public:
        * backtrace program using bit-wise operation to speed up calculation.
         * 'limit' is all '1's.
         * 'h'  is the bits all the queens vertically projected on a row. If h==limit, then it's done, answer++.
         * 'r'   is the bits all the queens anti-diagonally projected on a row.
         * 'l'   is the bits all the queens diagonally projected on a row.
         * h|r|l  is all the occupied bits. Then pos = limit & (~(h|r|l)) is all the free positions.
         * p = pos & (-pos)  gives the right most '1'. pos -= p means we will place a queen on this bit
         *                             represented by p.
         * 'h+p'  means one more queue vertically projected on next row.
         * '(r+p)<<1'  means one more queue anti-diagonally projected on next row. Because we are
         *                   moving to next row and the projection is skew from right to left, we have to
         *                   shift left one position after moved to next row.
         * '(l+p)>>1'  means one more queue diagonally projected on next row. Because we are
         *                  moving to next row and the projection is skew from left to right, we have to
         *                  shift right one position after moved to next row.
         *
    int totalNQueens(int n) {
        ans = 0;
        limit = (1<<n) - 1;
        dfs(0, 0, 0);
        return ans;
    }
    void dfs(int h, int r, int l) {
        if (h == limit) {
            ans++;
            return;
        }
        int pos = limit & (~(h|r|l));
        while (pos) {
            int p = pos & (-pos);
            pos -= p;
            dfs(h+p, (r+p)<<1, (l+p)>>1);
        }
    }
    int ans, limit;
};
     */
    private static char QUEEN = 'Q';
    private static char DOT = '.';
    private void helper(int [] ret, int index, int n, int [] tmp) {
        if (index == n ) {
            ret[0]++;
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

    public int totalNQueens(int n) {
        int [] tmp = new int [1];
        helper(tmp, 0, n, new int [n]);

        return tmp[0];
    }
    public static void main(String [] args) {
        Solution s = new Solution();
    }
}
