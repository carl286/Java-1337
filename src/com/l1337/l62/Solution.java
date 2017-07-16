package com.l1337.l62;


public class Solution {

    public int uniquePaths(int m, int n) {
        int min = Math.min(m, n);
        int max = Math.max(m, n);
        if (min <= 0)
            return 0;

        int tmp [] = new int [min+1];
        for (int i = 1; i <= min; ++i)
            tmp[i] = 1;
        for (int i = 2; i <= max; ++i) {
            for (int j = 1; j <= min; ++j) {
                tmp[j] += tmp[j-1];
            }
        }

        return tmp[min];
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.uniquePaths(7,3));
    }
}
