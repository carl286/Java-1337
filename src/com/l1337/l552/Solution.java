package com.l1337.l552;


public class Solution {

//    https://leetcode.com/problems/student-attendance-record-ii/discuss/137060/Java-Fibonacci-Solution-O(n)-time-O(1)-space.
//    https://leetcode.com/problems/student-attendance-record-ii/discuss/101643/Share-my-O(n)-C++-DP-solution-with-thinking-process-and-explanation
//    https://leetcode.com/problems/student-attendance-record-ii/discuss/101633/Improving-the-runtime-from-O(n)-to-O(log-n)
    public long checkRecord(int n) {
        switch (n) {
            case 1:
                return 3;
            case 2:
                return 6;
            default:break;
        }

//        int [2][3][n]
        long dp[][][] = new long [2][][];
        for (int i = 0; i < dp.length; ++i) {
            dp[i] = new long [3][];
            for (int j = 0; j < dp[i].length; ++j)
                dp[i][j] = new long [n + 1];
        }

        //init
        dp[0][0][1] = 1; //P
        dp[0][1][1] = 1; //L
//        dp[0][2][1] = 0;// not possible
        dp[1][0][1] = 1; //A
//        dp[1][1][1] = 0; // not possible
//        dp[1][2][1] = 0;// not possible

        dp[0][0][2] = 2; //PP, LP
        dp[0][1][2] = 1; //PL
        dp[0][2][2] = 1;//LL
        dp[1][0][2] = 3; //AP, PA, LA
        dp[1][1][2] = 1; //AL
//        dp[1][2][2] = 0;// not possible

        for (int i = 3; i <= n; ++i) {
            dp[0][0][i] = ((dp[0][0][i-1] + dp[0][1][i - 1]) % 1000000007 + dp[0][2][i - 1]) % 1000000007;
            dp[0][1][i] = dp[0][0][i-1]; //PPP...PL
            dp[0][2][i] = dp[0][1][i-1];//PPP...PLL, they should all be 1....
            dp[1][0][i] = ((((dp[0][0][i] + dp[1][0][i-1]) % 1000000007) + dp[1][1][i-1]) % 1000000007 + dp[1][2][i-1]) % 1000000007; //AP, PA
            dp[1][1][i] = dp[1][0][i-1]; //AL
            dp[1][2][i] = dp[1][1][i-1];// not possible
        }

        return dp[0][0][n] % 1000000007 + dp[0][1][n] % 1000000007+ dp[0][2][n]% 1000000007 + dp[1][0][n] % 1000000007+ dp[1][1][n] % 1000000007+ dp[1][2][n]% 1000000007;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.checkRecord(100));
    }
}
