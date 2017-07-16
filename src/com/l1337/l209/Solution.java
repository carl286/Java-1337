package com.l1337.l209;


public class Solution {

    public int minSubArrayLen(int s, int[] nums) {

        for (int l = 1; l <= nums.length; ++l)
        {
            int sum = 0;
            for (int i = 0; i < l - 1; ++i)
                sum += nums[i];

            for (int k = l - 1; k < nums.length; ++k)
            {
                sum += nums[k];
                if (sum >= s)
                    return l;
                sum -= nums[k - l + 1];
            }
        }

        return 0;
    }
    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
