package com.l1337.l1388;


public class Solution {

    public int maxSizeSlices(int[] slices) {
        int n = slices.length;
        int [][] dp = new int [1 + n][1 + n];
        for (int l = 3; l <= n; l += 3)
        {
            for (int i = 1; i + l - 1<= n; i += 3)
            {
                //start at i, end at i + l - 1, fill dp[i][i+l-1]
                for (int j = i + 1; j <= i + l - 2; ++j)
                {
                    for (int k = j + 1; k <= i + l - 1; ++k)
                    {

                    }
                }



            }

        }

        return 0;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
