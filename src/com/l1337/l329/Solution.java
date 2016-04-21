package com.l1337.l329;

//https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
//329. Longest Increasing Path in a Matrix

import java.util.Map;

public class Solution {
    private boolean isValid(int i, int j, int [][] matrix) {
        return i >= 0 && j >= 0 && i < matrix.length && j < matrix[0].length;
    }
    private void visit(int i,int j,int [][]matrix, int [][]steps) {
        //when you come here, steps[i][j] should be 0.
        steps[i][j] = 1;
        int [][] dicretions = {{-1,0}, {+1,0}, {0,-1}, {0,+1}};
        for (int k = 0; k < dicretions.length; ++k) {
            int next_i = i + dicretions[k][0];
            int next_j = j + dicretions[k][1];
            if (isValid(next_i, next_j, matrix) && matrix[i][j] < matrix[next_i][next_j]) {
                if (steps[next_i][next_j] == 0)
                    visit(next_i, next_j, matrix, steps);
                steps[i][j] = Math.max(steps[i][j], 1 + steps[next_i][next_j]);
            }
        }
    }
    public int longestIncreasingPath(int[][] matrix) {
        int [][] steps = new int [matrix.length][];
        for (int i = 0; i < steps.length; ++i)
            steps[i] = new int [matrix[0].length];

        int ret = 0;
        for (int i = 0; i < matrix.length; ++i)
            for (int j = 0; j < matrix[0].length; ++j) {
                if (steps[i][j] == 0) {
                    visit(i,j,matrix,steps);
                }
                ret = Math.max(ret, steps[i][j]);
            }
        return ret;
    }
    public static void main(String [] args) {
        int [][] nums = {{9,9,4}, {6,6,8}, {2,1,1}};
        Solution s = new Solution();
        System.out.println(s.longestIncreasingPath(nums));
    }
}
