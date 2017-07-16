package com.l1337.l375;


public class Solution {

    public int getMoneyAmount(int n) {
        int[][] dp = new int[n+1][n+1];
        return solve(dp, 1, n);
    }
    int solve(int[][] dp, int L, int R) {
        if (L >= R) return 0;
        if (dp[L][R] != 0) return dp[L][R];
        dp[L][R] = 0x7FFFFFFF;
        for (int i = L; i <= R; i++) {
            dp[L][R] = Math.min(dp[L][R], i + Math.max(solve(dp,L,i-1),solve(dp,i+1,R)));
        }
        return dp[L][R];
    }

//    https://artofproblemsolving.com/community/c296841h1273742
    public int getMoneyAmountFeb24_21(int n) {
        int dp [][] = new int[n+1][n+1];
        for(int l = 1; l < n; ++l)
        {
            //update dp[i][i+l]
            for (int i = 1; i + l <= n; ++i)
            {
                dp[i][i+l] = Integer.MAX_VALUE;
                for(int k = i; k <= i + l; ++k)
                {
                    dp[i][i+l] = Math.min(dp[i][i+l], k + Math.max((i <= k - 1 ? dp[i][k-1] : 0), (k + 1 <= i + l ? dp[k+1][i+l] : 0)));
                }
            }
        }


        return dp[1][n];
    }



    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.getMoneyAmountFeb24_21(10));
    }
}


//具体是这样的，在1-n个数里面，我们任意猜一个数(设为i)，
// 保证获胜所花的钱应该为 i + max(w(1 ,i-1), w(i+1 ,n))
// 这里w(x,y))表示猜范围在(x,y)的数保证能赢应花的钱，则我们依次遍历 1-n作为猜的数，求出其中的最小值即为答案

//这题要求我们在猜测数字y未知的情况下（1~n任意一个数），要我们在最坏情况下我们支付最少的钱。也就是说要考虑所有y的情况。
//        我们假定选择了一个错误的数x，（1<=x<=n && x!=y ）那么就知道接下来应该从[1,x-1 ] 或者[x+1,n]中进行查找。 假如我们已经解决了[1,x-1] 和 [x+1,n]计算问题，我们将其表示为solve(L,x-1) 和solve(x+1,n)，
// 那么我们应该选择max(solve(L,x-1),solve(x+1,n)) 这样就是求最坏情况下的损失。总的损失就是 f(x) = x + max(solve(L,x-1),solve(x+1,n))
//        那么将x从1~n进行遍历，取使得 f(x) 达到最小，来确定最坏情况下最小的损失，也就是我们初始应该选择哪个数。