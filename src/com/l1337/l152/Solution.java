package com.l1337.l152;


public class Solution {

//    https://leetcode.com/problems/maximum-product-subarray/discuss/183483/JavaC%2B%2BPython-it-can-be-more-simple
//    https://leetcode.com/problems/maximum-product-subarray/discuss/48252/Sharing-my-solution%3A-O(1)-space-O(n)-running-time
    public int maxProduct(int[] nums) {
        if (nums.length < 1)
            return 0;
        int ret = nums[0];
        int maxPositive = 0;
        int minNegative = 0;
        for (int i = 0; i < nums.length; ++i)
        {
            if (nums[i] == 0)
            {
                ret = Math.max(ret, 0);
                maxPositive = 0;
                minNegative = 0;
            }
            else if (nums[i] > 0)
            {
                minNegative *= nums[i];

                if (maxPositive > 0)
                    maxPositive *= nums[i];
                else
                    maxPositive = nums[i];

                ret = Math.max(ret, maxPositive);
            }
            else
            {
                int maxPosCandidate = minNegative * nums[i];
                if (maxPositive > 0)
                    minNegative = maxPositive * nums[i];
                else
                    minNegative = nums[i];
                maxPositive = maxPosCandidate;
                if (maxPositive > 0)
                    ret = Math.max(maxPositive, ret);
            }
        }
        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
