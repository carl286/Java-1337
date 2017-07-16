package com.l1337.l1696;


import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
    public int maxResultTLE(int[] nums, int k) {
        //k >= 0
        int [] dp = new int [nums.length];
        dp[nums.length-1] = nums[nums.length-1];
        for(int i = nums.length - 2; i >= 0; --i)
        {
            //
            dp[i] = Integer.MIN_VALUE;
            for(int l = 1; l <= k && i + l < nums.length; ++l)
            {
                dp[i] = Math.max(dp[i], nums[i] + dp[i+l]);
            }
        }

        return dp[0];
    }

    public int maxResult(int[] nums, int k) {
        //k >= 0
        int [] dp = new int [nums.length];
        Deque<Integer> dq = new ArrayDeque<>();
        dp[nums.length-1] = nums[nums.length-1];
        dq.addLast(nums.length-1);

        for(int i = nums.length - 2; i >= 0; --i)
        {
            dp[i] = nums[i] + dp[dq.peekFirst()];

            while (!dq.isEmpty() && dp[dq.peekLast()] <= dp[i])
            {
                dq.pollLast();
            }
            dq.addLast(i);

            if (dq.peekFirst() >= i + k)
                dq.pollFirst();
        }

        return dp[0];
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
