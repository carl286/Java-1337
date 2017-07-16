package com.l1337.l1130;


public class Solution {

    public int mctFromLeafValues(int[] arr) {
        //below is an accepted solution, how can we improve it to O(n)
//        https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/discuss/340489/Summary-and-reasoning-of-different-solutions
//        https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/discuss/339959/One-Pass-O(N)-Time-and-Space

        /*
        Since given array is the inorder traversal of tree leaves. Their order won't change. You can only decide which 2 consecutive nodes you wanna combine to make a new node by their product.
         */
        int [][] dp = new int [arr.length][arr.length];
        int [][] max = new int [arr.length][arr.length];
        for (int i = dp.length - 1; i >= 0; --i)
        {
            for (int j = i; j < arr.length; ++j)
            {
                if (i == j)
                {
                    max[i][j] = arr[i];
                    dp[i][j] = arr[i];
                }
                else
                {
                    max[i][j] = Math.max(arr[j], max[i][j-1]);
                    dp[i][j] = Integer.MAX_VALUE;
                    for (int k = i; k < j; ++k)
                    {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j] + max[i][k]*max[k+1][j]);
                    }
                }
            }
        }

        int total = 0;
        for (int i = 0; i < arr.length; ++i)
            total += arr[i];
        return dp[0][arr.length-1] - total;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.mctFromLeafValues(new int []{6,2, 4}));
    }
}
