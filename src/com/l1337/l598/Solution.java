package com.l1337.l598;


public class Solution {
    public int maxCount(int m, int n, int[][] ops) {
        if (ops.length <= 0)
            return 0;

        for (int i = 0; i < ops.length; ++i) {
            m = Math.min(m, ops[i][0]);
            n = Math.min(n, ops[i][1]);
        }

        return m * n;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
