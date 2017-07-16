package com.l1337.l718;


import java.util.Arrays;

public class Solution {

    public int findLength(int[] A, int[] B) {
//        int ret = 0;
//        Arrays.sort(A);
//        Arrays.sort(B);
//
//        int i = 0, j = 0;
//        while (i < A.length && j < B.length) {
//            if (A[i] < B[j]) {
//                ++i;
//            } else if (A[i] == B[j]) {
//                ++i;
//                ++j;
//                ++ret;
//            } else {
//                ++j;
//            }
//        }
//
//        return ret;
        //len a and len b >= 1
        int [][] dp = new int [A.length][B.length];
        int ret = 0;
        for (int i = 0; i < dp.length; ++i) {
            for (int j = 0; j < dp[i].length; ++j) {
                if (A[i] == B[j]) {
                    dp[i][j] = 1 + ((i > 0 && j > 0) ? dp[i-1][j-1] : 0);
                    ret = Math.max(dp[i][j], ret);
                }
            }
        }


        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.findLength(new int [] {1,2,3,2,1}, new int [] {3,2,1,4,7}));
    }
}
