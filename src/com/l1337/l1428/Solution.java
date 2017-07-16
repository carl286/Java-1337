package com.l1337.l1428;


public class Solution {

    /*
    Leftmost Column with at Least a One

    Given a binary matrix mat[][] containing 0’s and 1’s. Each row of the matrix is sorted in the non-decreasing order,
    the task is to find the left-most column of the matrix with at least one 1 in it.
Note: If no such column exists return -1.
Examples:

Input:
mat[2][2] = { {0, 0},
              {1, 1} }
Output: 1
Explanation:
The 1st column of the
matrix contains atleast a 1.

Input:
mat[2][2] = {{0, 0},
             {0, 0}}
Output: -1
Explanation:
There is no such column which
contains atleast one 1.


     */












    //think about the category of this problem
    int search(int mat[][])
    {
        if (mat.length <= 0 || mat[0].length <= 0)
            return -1;
        int m = mat.length;
        int n = mat[0].length;
        int toCheck = n - 1;
        for (int i = 0; i < mat.length && toCheck >= 0; ++i)
        {
            while (toCheck >= 0 && mat[i][toCheck] == 1)
                --toCheck;
        }
        return toCheck == n - 1 ? -1 : toCheck + 2;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.search(new int [][]{{0,0}, {0,0}}));
        System.out.println(s.search(new int [][]{{0,0}, {1,1}}));
        System.out.println(s.search(new int [][]{{0,0, 0}, {0, 0,1}, {0, 1, 1}}));
    }
}
