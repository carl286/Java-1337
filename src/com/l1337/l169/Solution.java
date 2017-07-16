package com.l1337.l169;


public class Solution {

    public int majorityElement(int[] nums) {
        int ret = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; ++i)
        {
            if (nums[i] == ret)
                ++count;
            else if (count == 0)
            {
                ++count;
                ret = nums[i];
            }
            else
                --count;
        }

        return ret;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
