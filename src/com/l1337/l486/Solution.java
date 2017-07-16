package com.l1337.l486;


public class Solution {

//    https://leetcode.com/problems/predict-the-winner/description/

//    https://leetcode.com/problems/predict-the-winner/discuss/146756/Java-beats-100-bottom-up-approach
//    https://leetcode.com/problems/predict-the-winner/discuss/96828/JAVA-9-lines-DP-solution-easy-to-understand-with-improvement-to-O(N)-space-complexity.
    public boolean PredictTheWinner(int[] nums) {
        int [][] dp = new int [nums.length][];
        int [][] sums = new int [nums.length][];
        for (int i = 0; i < dp.length; ++i) {
            dp[i] = new int [nums.length];
            sums[i] = new int [nums.length];

            sums[i][i] = nums[i];
            dp[i][i] = nums[i];
        }

        for (int i = 0; i < nums.length; ++i)
            for (int j = i + 1; j < nums.length; ++j)
                sums[i][j] = sums[i][j-1] + nums[j];

        for (int len = 2; len <= nums.length; ++len) {
            //calculate dp[i][i + len -1]
            for (int i = 0; i < nums.length - len + 1; ++i) {
                dp[i][i + len -1] = Math.max(nums[i] + (sums[i+1][i + len - 1] - dp[i+1][i + len - 1]),
                        nums[i + len - 1] + (sums[i][i + len -2] - dp[i][i + len -2]));
            }
        }
        return dp[0][nums.length - 1] >= (sums[0][nums.length - 1] - dp[0][nums.length - 1]);
    }
    public static void main(String [] args) {
        Solution s = new Solution();
//        System.out.println(s.PredictTheWinner(new int [] {1, 5, 2}));
        System.out.println(s.PredictTheWinner(new int [] {1, 5, 233, 7}));
    }
}
