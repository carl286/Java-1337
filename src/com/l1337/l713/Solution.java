package com.l1337.l713;


public class Solution {

    private int helper(int [] nums, int start, int acc, int k, boolean started) {
        if (acc >= k)
            return 0;

        int ret = 0;
        if (acc < k && started)
            ++ret;

        for (int i = start; i < nums.length; ++i) {
            //pick up start
            ret += helper(nums, i + 1, acc * nums[i], k, true);
        }
        return ret;
    }
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1)
            return 0;

//        return helper(nums, 0, 1, k, false);
        int ret = 0;
        int acc = 1;
        int l = 0, cur = 0;
        while (cur < nums.length) {
            acc *= nums[cur];

            ++l;

            while (acc >= k) {
                acc /= nums[cur - l + 1];
                --l;
            }
            ret += l;
            ++cur;
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        int [] nums = {10, 5, 2, 6};
//        int [] nums = {1,2};
//        int [] nums = {10, 5, 2};
        int k = 100;
        System.out.println(s.numSubarrayProductLessThanK(nums, k));
    }
}
