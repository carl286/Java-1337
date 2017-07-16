package com.l1337.l764;


import java.util.Arrays;

public class Solution {

    public int orderOfLargestPlusSign(int N, int[][] mines) {
        int total = N*N;
        int zeros = mines.length;
        if (total <= zeros)
            return 0;
        if (total - zeros < 5)
            return 1;

        int ret = 1;
        int [][] grid = new int [N][N];
        for(int i = 0; i < grid.length; ++i)
        {
            Arrays.fill(grid[i], 1);
        }

        for(int i = 0; i < mines.length; ++i)
            grid[mines[i][0]][mines[i][1]] = 0;

        int [][] left = new int [N][N];
        int [][] right = new int [N][N];
        int [][] up = new int [N][N];
        int [][] down = new int [N][N];
        for(int i = 0; i < grid.length; ++i)
        {
            for(int j = 0; j < grid.length; ++j)
            {
                if (grid[i][j] != 0)
                {
                    left[i][j] = 1 + (j == 0 ? 0 : left[i][j-1]);
                }
            }
        }

        for(int i = 0; i < grid.length; ++i)
        {
            for(int j = 0; j < grid.length; ++j)
            {
                if (grid[j][i] != 0)
                {
                    up[j][i] = 1 + (j == 0 ? 0 : up[j-1][i]);
                }
            }
        }

        for(int i = 0; i < N; ++i)
        {
            for(int j = N - 1; j >= 0; --j)
            {
                if (grid[i][j] != 0)
                {
                    right[i][j] = 1 + (j == N - 1 ? 0 : right[i][j+1]);
                }
            }
        }

        for(int i = 0; i < N; ++i)
        {
            for(int j = N - 1; j >= 0; --j)
            {
                if (grid[j][i] != 0)
                {
                    down[j][i] = 1 + (j == N - 1 ? 0 : down[j+1][i]);
                }
            }
        }

        for(int i = 0; i < N; ++i)
        {
            for(int j = 0; j < N; ++j)
            {
                ret = Math.max(ret, Math.min(left[i][j], Math.min(right[i][j], Math.min(up[i][j], down[i][j]))));
                // System.out.println("i: " + i + "\tj:" + j + ":\t" + ret);
            }
        }
        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        int N = 5;
        int [][] mines = new int [][] {{4,2}};
        System.out.println(s.orderOfLargestPlusSign(N, mines));
    }
}
