package com.leetcode;

public class NumMatrix {
//    https://leetcode.com/problems/range-sum-query-2d-immutable/

    int [][] array;
    public NumMatrix(int[][] matrix) {
        //Test first.....
        if (matrix == null || matrix.length <= 0 || matrix[0].length <= 0) {
            array = null;
            return;
        }
        array = new int [matrix.length + 1][];
        for (int i = 0; i < array.length; i++)
            array[i] = new int [matrix[0].length + 1];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                array[i + 1][j + 1] = matrix[i][j] + array[i + 1][j] + array[i][j + 1] - array[i][j];
                System.out.print(array[i + 1][j + 1] + "\t");
            }
            System.out.println();
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return array == null ? 0 : array[row2+1][col2+1] - array[row2+1][col1] - array[row1][col2+1] + array[row1][col1];
    }

    public static void main(String [] args) {
        int [][] matrix = {{3,0,1,4,2},{5,6,3,2,1},{1,2,0,1,5},{4,1,0,1,7},{1,0,3,0,5}};
        NumMatrix nm = new NumMatrix(matrix);
        System.out.println(nm.sumRegion(2,1,4,3));
    }
}
