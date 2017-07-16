package com.googlePrep;

public class CountOneSquare {

    /*
    4. 一道DP。一个2D array，数这个矩阵里有多少个不含有0的square
     */
    public int countOneSquare(int [][] A)
    {
        int res = 0;
        for (int i = 0; i < A.length; ++i) {
            for (int j = 0; j < A[0].length; ++j) {
                if (A[i][j] > 0 && i > 0 && j > 0) {
                    A[i][j] = Math.min(A[i - 1][j - 1], Math.min(A[i - 1][j], A[i][j - 1])) + 1;
                }
                res += A[i][j];
            }
        }
        return res;
    }
    public static void main(String [] args) {
        CountOneSquare s = new CountOneSquare();
        int [][] grid = new int [][]
        {
            {0,0,0},
            {0,0,0},
            {1,0,0},
            {0,0,0}
        };
        System.out.println(s.countOneSquare(grid));
    }
}
