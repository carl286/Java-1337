package com.l1337.l363;


import java.util.TreeSet;

public class Solution {

    public int maxSumSubmatrix(int[][] matrix, int k) {
        int ret = Integer.MIN_VALUE;
        //assume r & c > 0
        int r = matrix.length, c = matrix[0].length;
        if (r < c) {
            for (int i = 0; i < r; ++i) {
                int tmp [] = new int [c];
                for (int j = i; j < r; ++j) {
                    for (int m = 0; m < c; ++m) {
                        tmp[m] += matrix[j][m];
                    }
                    ret = Math.max(ret, maxSumSubArray(tmp, k));
                }
            }
        } else {
            for (int i = 0; i < c; ++i) {
                int tmp [] = new int [r];
                for (int j = i; j < c; ++j) {
                    for (int m = 0; m < r; ++m) {
                        tmp[m] += matrix[m][j];
                    }
                    ret = Math.max(ret, maxSumSubArray(tmp, k));
                }
            }

        }
        return ret;
    }

    public int maxSumSubArray(int[] matrix, int k) {
        int ret = Integer.MIN_VALUE;
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        int acc = 0;
        for (int i = 0; i < matrix.length; ++i) {
            acc += matrix[i];
            //May be we can add 0 to the set by ourselves so that we can avoid below check.
//            if (acc <= k)
//                ret = Math.max(acc, ret);
            //search
            Integer find = set.ceiling(acc - k);
            if (find != null)
                ret = Math.max(ret, acc - find);
            set.add(acc);
        }

        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
//        int [][] matrix = {
//                {1,  0, 1},
//                {0, -2, 3},
//                };
//        int k = 2;

//        int [][] matrix = {{2,2,-1}};
//        int k = 3;
//        System.out.println(s.maxSumSubmatrix(matrix,k));
        int [] matrix = {1,-2,4};
        int k = 2;
        System.out.println(s.maxSumSubArray(matrix,k));
    }
}


//http://www.geeksforgeeks.org/dynamic-programming-set-27-max-sum-rectangle-in-a-2d-matrix/
//https://www.quora.com/Given-an-array-of-integers-A-and-an-integer-k-find-a-subarray-that-contains-the-largest-sum-subject-to-a-constraint-that-the-sum-is-less-than-k
//https://leetcode.com/discuss/109705/java-binary-search-solution-time-complexity-min-max-log-max