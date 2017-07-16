package com.googlePrep;

public class CoionCollection {

    /*
    3.) 2D array with numbers(coins), can only choose one number from each row (has to choose, cannot skip)
Keep moving until to the last row of the grid to collect coins. But if you choose x-th column from previous row with
a differnt y-th column from the current row, there's a cost of changing fee: Abs(x - y)
What's the maximun final coins you could collect after paying changing fees(if applicable)
     */

    public int collectMaxCoins(int [][] grid)
    {
        int m = grid.length, n = grid[0].length;
        int [][] dp = new int [m][n];
        System.arraycopy(grid[m-1], 0, dp[m-1], 0, n);

        for(int i = m - 2; i >= 0; --i)
        {
            for(int j = 0; j < n; ++j)
            {
                dp[i][j] = Integer.MIN_VALUE;
                for(int k = 0; k < n; ++k)
                    dp[i][j] = Math.max(dp[i][j], Math.abs(j-k) + dp[i+1][k] + grid[i][j]);
            }
        }

        int ret = dp[0][0];
        for(int i = 1; i < n; ++i)
            ret = Math.max(ret, dp[0][i]);
        return ret;
    }
    public static void main(String [] args)
    {
        CoionCollection s = new CoionCollection();
        int [][] grid = new int [][]{
                {1,2,3,4,5},
                {6,7,8,9,10},
                {15,14,13,12,11},
//                {17,18,19,20,16}
        };
        System.out.println(s.collectMaxCoins(grid));
    }
}
