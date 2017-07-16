package com.l1337.l918;


public class Solution {

//    https://leetcode.com/problems/maximum-sum-circular-subarray/discuss/178422/One-Pass
//    https://leetcode.com/problems/maximum-sum-circular-subarray/discuss/633401/Kadane-Algorithm-Trick-beats-100-Java-Explained
    public int maxSubarraySumCircular(int[] A) {
        int ret = Integer.MIN_VALUE;
        int acc = 0, total = 0;
        int acc2 = 0, minSubSum = Integer.MAX_VALUE;
        for(int i = 0; i < A.length; ++i)
        {
            total += A[i];
            acc += A[i];
            if (acc > ret)
                ret = acc;
            if (acc < 0)
            {
                acc = 0;
            }
            acc2 += A[i];
            minSubSum = Math.min(acc2, minSubSum);
            if (acc2 > 0)
                acc2 = 0;
        }
        if (total != minSubSum)
        ret = Math.max(ret, total - minSubSum);

        return ret;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.maxSubarraySumCircular(new int []{-2,-3,-1}));
    }
}
