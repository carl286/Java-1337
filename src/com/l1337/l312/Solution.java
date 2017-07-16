package com.l1337.l312;


//	https://leetcode.com/problems/burst-balloons/
//https://leetcode.com/problems/burst-balloons/discuss/76228/share-some-analysis-and-explanations
public class Solution {

    public int maxCoins(int[] inums) {

        int length = inums.length;
        if (inums == null || length <= 0)
            return 0;
        //		reverse thinking. Like I said the coins you get for a balloon does not depend on the balloons already burst. Therefore instead of divide the problem by the first balloon to burst, we divide the problem by the last balloon to burst.
//		For the first we have nums[i-1]*nums[i]*nums[i+1] for the last we have nums[-1]*nums[i]*nums[n].
//		Here comes the final solutions. Note that we put 2 balloons with 1 as boundaries and also burst all the zero balloons in the first round since they won't give any coins. The algorithm runs in O(n^3) which can be easily seen from the 3 loops in dp solution.
//		dp[left][right] means the maximum value when we burst all balloons between nums[left] to nums[right](pay attention that balloons left and right are not included)
        int[] nums = new int[inums.length + 2];
        int n = 1;
        for (int x : inums) if (x > 0) nums[n++] = x;
        nums[0] = nums[n++] = 1;

        int[][] dp = new int[n][n];
        for (int k = 2; k < n; ++k)
            for (int l = 0; l < n - k; ++l) {
                int r = l + k;
                for (int m = l + 1; m < r; ++m)
                    dp[l][r] = Math.max(dp[l][r],
                            nums[l] * nums[m] * nums[r] + dp[l][m] + dp[m][r]);
            }

        return dp[0][n - 1];
    }

    //	http://algobox.org/burst-balloons/
//时间复杂度O(n ^ 3)参考peisi的答案：https://leetcode.com/discuss/72216/share-some-analysis-and-explanations
//	以最后被爆破的气球m为界限，把数组分为左右两个子区域 状态转移方程：
//	dp[l][r] = max(dp[l][r], nums[l] * nums[m] * nums[r] + dp[l][m] + dp[m][r])

//    https://leetcode.com/problems/burst-balloons/discuss/76228/share-some-analysis-and-explanations

    public static void main(String [] args) {
        Solution s = new Solution();
        int [] nums = {3,1,5,8};
//        int [] nums = {3,4,2};
        System.out.println(s.maxCoins(nums));
    }
}
