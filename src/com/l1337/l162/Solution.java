package com.l1337.l162;


public class Solution {


//    https://leetcode.com/problems/find-peak-element/solution/
    public int findPeakElement(int[] nums) {
//        for (int i = 0; i < nums.length; ++i)
//        {
//            if ((((i != 0) && (nums[i] > nums[i-1])) || (i == 0)) &&
//            (((i + 1) != nums.length) && (nums[i] > nums[i+1])) || (i + 1 == nums.length))
//                return i;
//        }
//
//        return -1;
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] > nums[mid + 1])
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }

    public static void main(String [] args) {
        Solution s = new Solution();
        System.out.println("Hello World");
    }
}
