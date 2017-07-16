package com.l1337.l644;


public class Solution {

    /*
    Given an array consisting of n integers, find the contiguous subarray whose length is greater than or equal to k that has the maximum average value. And you need to output the maximum average value.

Example 1:

Input: [1,12,-5,-6,50,3], k = 4
Output: 12.75
Explanation:
when length is 5, maximum average value is 10.8,
when length is 6, maximum average value is 9.16667.
Thus return 12.75.


Note:

1 <= k <= n <= 10,000.
Elements of the given array will be in range [-10,000, 10,000].
The answer with the calculation error less than 10-5 will be accepted.
     */

//    http://www.cnblogs.com/grandyang/p/8021421.html
    public double findMaxAverage(int[] nums, int k) {
        double ret;
        if (k == 1)
            ret = nums[0];
        else
            ret = Double.MIN_VALUE;

        /*
         * 更新结果res的步骤不能写成res = min(res, t / (i + 1)) 这种形式，会TLE，必须要在if中判断 t > res * (i + 1) 才能accept，写成t / (i + 1) > res 也不行
         */
        for (int i = 1; i < nums.length; ++i) {
            nums[i] += nums[i-1];
            if (i + 1 >= k)
                ret = Math.max(ret, ((double) nums[i]) / (i+1));
        }

        for (int i = 0; i + k < nums.length; ++i) {
            for (int j = i + k; j < nums.length; ++j)
                ret = Math.max(ret, ((double) (nums[j] - nums[i]) / (j - i)));
        }
        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println(s.findMaxAverage(new int [] {1,12,-5,-6,50,3}, 4 ));
    }
}
