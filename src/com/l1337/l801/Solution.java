package com.l1337.l801;


public class Solution {

    public int minSwap(int[] A, int[] B) {
        if (A.length <= 1)
            return 0;

        int [][] dp = new int [2][A.length];
        dp[1][0] = 1;

        //dp[0]... not swapped
        //dp[1]...swapped
        for(int i = 1; i < A.length; ++i)
        {
            //update non-swap case
            dp[0][i] = Integer.MAX_VALUE;
            dp[1][i] = Integer.MAX_VALUE;
            if (A[i] > A[i-1] && B[i] > B[i-1])
            {
                dp[0][i] = Math.min(dp[0][i], dp[0][i-1]);
                dp[1][i] = Math.min(dp[1][i], dp[1][i-1] == Integer.MAX_VALUE? Integer.MAX_VALUE : (1 + dp[1][i-1]));
            }

            if (A[i] > B[i-1] && B[i] > A[i-1])
            {
                dp[0][i] = Math.min(dp[0][i], dp[1][i-1]);
                dp[1][i] = Math.min(dp[1][i], dp[0][i-1] == Integer.MAX_VALUE? Integer.MAX_VALUE : (1 + dp[0][i-1]));
            }
        }

        return Math.min(dp[0][A.length-1], dp[1][A.length-1]);
    }

    public int minSwapV2(int[] A, int[] B) {
        int a = 0, b = 1;

        //dp[0]... not swapped
        //dp[1]...swapped
        for(int i = 1; i < A.length; ++i)
        {
            //update non-swap case
            int next_a = Integer.MAX_VALUE;
            int next_b = Integer.MAX_VALUE;
            if (A[i] > A[i-1] && B[i] > B[i-1])
            {
                next_a = Math.min(next_a, a);
                next_b = Math.min(next_b, b == Integer.MAX_VALUE? Integer.MAX_VALUE : (1 + b));
            }

            if (A[i] > B[i-1] && B[i] > A[i-1])
            {
                next_a = Math.min(next_a, b);
                next_b = Math.min(next_b, a == Integer.MAX_VALUE? Integer.MAX_VALUE : (1 + a));
            }
            a = next_a;
            b = next_b;
        }

        return Math.min(a, b);
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        int [] A = new int [] {1,3,5,4};
        int [] B = new int [] {1,2,3,7};
        System.out.println(s.minSwap(A,B));
    }
}
