package com.l1337.l279;


import java.util.Arrays;

public class Solution {

//    https://leetcode.com/problems/perfect-squares/
//    http://bookshadow.com/weblog/2015/09/09/leetcode-perfect-squares/
//    http://www.cnblogs.com/grandyang/p/4800552.html
    public int numSquares(int n) {
        int [] t = new int[1+n];
        int [] sq = new int [1 + (int)Math.sqrt(n)];
        Arrays.fill(t, Integer.MAX_VALUE);
        for (int i = 0; i < sq.length; ++i) {
            sq[i] = i*i;
            t[sq[i]] = 1;
        }
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j < sq.length && (i+sq[j]) <= n; ++j) {
                t[i+sq[j]] = Math.min(t[i+sq[j]], 1 + t[i]);
            }

            for (int k = 0; k < t.length; ++k) {
                System.out.print(t[k] + "\t");
            }
            System.out.println();
        }
        return t[n];
    }
    public static void main(String [] args) {
        Solution s = new Solution();
//        for (int i = 0; i <= 20; ++i) {
//            System.out.println(i + "\t" + s.numSquares(i));
//        }
            System.out.println("\t" + s.numSquares(6));
    }
}
