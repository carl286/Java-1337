package com.l1337.l566;


public class Solution {

//    https://leetcode.com/problems/reshape-the-matrix/discuss/102491/Java-Concise-O(nm)-time
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        //assume nums not null
        if (nums == null || nums.length <= 0)
            return nums;

        int org_n_row = nums.length, org_n_col = nums[0].length;
        if (r <= 0 || c <= 0 || r * c !=  org_n_row * org_n_col || r == org_n_row)
            return nums;

        int [][] ret = new int [r][];
        for (int i = 0; i < ret.length; ++i)
            ret[i] = new int [c];

        int index = 0;
        for (int i = 0; i < org_n_row; ++i) {
            for (int j = 0; j < org_n_col; ++j) {
                ret[index / c][index % c] = nums[i][j];
                ++index;
            }
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
