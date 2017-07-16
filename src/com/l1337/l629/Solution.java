package com.l1337.l629;

public class Solution {

//    https://leetcode.com/problems/k-inverse-pairs-array/discuss/104815/Java-DP-O(nk)-solution
    public int kInversePairs(int n, int k) {
        int mod = 1000000007;
        int max_possible = n * (n - 1) / 2;
        if (k == 0 || k == max_possible)
            return 1;
        if (k > max_possible)
            return 0;

        int [] dp = new int [k+1];
//k >=1
        dp[0] = 1;
        int i = 2;
        while (i <= n) {
            int last_max_possible = (i-1) * (i-2)/2;
            int cur_max_possible = i * (i - 1) / 2;

            int [] next = new int [k+1];
            next[0] = 1;
            //fill dp[cur_max_possible] till dp[0]
            //always think about when index might be out of bounds
            for (int j = Math.min(k, cur_max_possible); j >= 1; --j) {
                for (int m = Math.min(j, last_max_possible); m >= (j - (i - 1)) && m >= 0; --m) {
                    next[j] = (next[j] + dp[m]) % mod;
                }
            }

            ++i;
            dp = next;
//            System.out.print(i-1 + ":\t");
//            for (int m = 0; m < dp.length; ++m)
//                System.out.print(dp[m] + "(" + m + ")"+ "\t");
//            System.out.println();
        }

        return dp[k];


        /*
        int mod = 1000000007;
        if (k > n*(n-1)/2 || k < 0) return 0;
        if (k == 0 || k == n*(n-1)/2) return 1;
        long[][] dp = new long[n+1][k+1];
        dp[2][0] = 1;
        dp[2][1] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= Math.min(k, i*(i-1)/2); j++) {
                dp[i][j] = dp[i][j-1] + dp[i-1][j];
                if (j >= i) dp[i][j] -= dp[i-1][j-i];
                dp[i][j] = (dp[i][j]+mod) % mod;
            }
        }
        return (int) dp[n][k];
         */
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.kInversePairs(1000,1000));
    }
}
